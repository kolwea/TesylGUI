/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Starfield;

import Tools.Vector;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * @author Kolbe
 */
public class Background_Starfield extends Background {

    private ArrayList<Star> stars;
    private final double minStarSize = 5.0,
            maxStarSize = 18.0,
            margin = 8.0,
            connectProb = 70,
            connectRadius = 1,
            pointProb = 40,
            scatterMin = -20.0,
            scatterMax = 20,
            changeRate = 0.03;
    private Timeline timeline;
    private KeyFrame keyframe;

    public Background_Starfield(Pane parent) {
        super(parent);
        pane = new Pane();
        pane.setMinSize(1500, 1000);
        this.setup();

    }

    @Override
    public void updateBackground() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pane getPane() {
        return this.pane;
    }

    //Timeline Control//////////////////////////////////////////////////////////
    private void setupTimeline() {
        keyframe = new KeyFrame(Duration.millis(10), (ActionEvent event) -> {
            update();
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void update() {
        for (int i = 0; i < stars.size(); i++) {
            stars.get(i).update();
        }
    }

    //Field Setup///////////////////////////////////////////////////////////////
    private void setup() {
//        setupField();
        setupStars();
        scatterStars();
        makeConstellations();
        setupTimeline();
    }

//    private void setupField() {
//        this.width = pane.getMinWidth();
//        this.height = pane.getMinHeight();
//    }
    private void setupStars() {
        stars = new ArrayList<>();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int offset = 0;
        int row = 0;

        for (int k = (int) (2 * maxStarSize); k < screenSize.getHeight() - maxStarSize; k += 2 * maxStarSize + margin) {
            if (row % 2 == 0) {
                offset = (int) maxStarSize;
            } else {
                offset = 0;
            }
            for (int i = (int) (2 * maxStarSize + offset); i < screenSize.getWidth() - maxStarSize; i += 2 * maxStarSize + margin) {
                double maybe = Math.random() * 100;
                if (maybe <= pointProb) {
                    Star test = new Star(new Vector(i, k), Math.random() * maxStarSize + minStarSize);
                    stars.add(test);
                    pane.getChildren().add(test.getBody());
                }
            }
            row++;
        }
        for (int i = 0; i < stars.size(); i++) {
            double maybe = Math.random() * 100;
            if (maybe >= 51) {
                stars.get(i).setGrow(true);
            }
        }
    }

    private void makeConstellations() {
        for (Star a : stars) {
            double oldWidth = a.getBody().getRadius();
            a.getBody().setRadius((maxStarSize + margin) * connectRadius);
            for (Star b : stars) {
                if (b != a && a.getBody().intersects(b.getBody().getBoundsInParent())) {
                    double maybe = Math.random() * 100;
                    if (maybe <= connectProb) {
                        this.connect(a.getBody(), b.getBody());
                    }
                }
            }
            a.getBody().setRadius(oldWidth);
        }
    }

    //Helper Functions//////////////////////////////////////////////////////////
    protected void connect(Circle one, Circle two) {
        Node n1 = one, n2 = two;
        if (pane == null) {
            System.out.println("Tis null");
        } else {
            Pane parent = pane;
            Line line = new Line();
            line.setStrokeWidth(1);
            line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = n1.getBoundsInParent();
                return b.getMinX() + b.getWidth() / 2;
            }, n1.boundsInParentProperty()));
            line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = n1.getBoundsInParent();
                return b.getMinY() + b.getHeight() / 2;
            }, n1.boundsInParentProperty()));
            line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = n2.getBoundsInParent();
                return b.getMinX() + b.getWidth() / 2;
            }, n2.boundsInParentProperty()));
            line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = n2.getBoundsInParent();
                return b.getMinY() + b.getHeight() / 2;
            }, n2.boundsInParentProperty()));
            line.strokeWidthProperty().bind(one.radiusProperty().add(two.radiusProperty()).divide(10));
            parent.getChildren().add(line);
            n1.toFront();
            n2.toFront();
        }
    }

    private double mapColor(double x) {
        double inMin = 0, inMax = this.maxStarSize + 6, outMin = 1, outMax = 255;
        double done = (double) (outMin + ((outMax - outMin) / (inMax - inMin)) * (x - inMin));
        return done;
    }

    private void scatterStars() {
        boolean touched;
        for (Star curr : stars) {
            scatter(curr.getBody());
        }
    }

    private void scatter(Circle a) {
        double maybe = Math.random() * 100;
        if (maybe >= 50) {
            a.setCenterX((Math.random() * scatterMax + scatterMin) + a.getCenterX());
            a.toBack();
        } else {
            a.setCenterY((Math.random() * scatterMax + scatterMin) + a.getCenterY());
            a.toBack();
        }
    }
}
