/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Starfield;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * @author Kolbe
 */
public class Background_Starfield extends Background {

    private ArrayList<Circle> stars;
    private int[] direction;
    private Line[] lines;
    private double minStarSize = 2.0, maxStarSize = 8.0, strokeWidth = 1.0, margin = 2.0;
    private Timeline timeline;
    private KeyFrame keyframe;

    public Background_Starfield(Pane parent) {
        super(parent);
        pane = new Pane();
        pane.setMinSize(1500, 1000);
        setupField();
        testPoints();
        this.setupTimeline();
    }

    private void setupField() {
        this.width = pane.getMinWidth();
        this.height = pane.getMinHeight();

    }

    @Override
    public void updateBackground() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pane getPane() {
        return this.pane;
    }

    //Helper Functions/////////////////////////////////////////////////////
    private int getStarColumnCount() {
        return (int) (height / (maxStarSize + margin));
    }

    private int getStarRowCount() {
        return (int) (height / (maxStarSize + margin));
    }

    private void setupTimeline() {
        keyframe = new KeyFrame(Duration.millis(25), (ActionEvent event) -> {
            update();
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void update() {
        for (int i = 0; i < stars.size(); i++) {
            Circle curr = stars.get(i);
            if (curr.getRadius() <= minStarSize) {
                direction[i] = 1;
            } else if (curr.getRadius() >= maxStarSize) {
                direction[i] = 0;
            }
            if (direction[i] == 1) {
                curr.setRadius(curr.getRadius() + Math.random());
            } else {
                curr.setRadius(curr.getRadius() - Math.random());
            }
//            System.out.println("Radius: " + curr.getRadius() + " Map: " + this.map(curr.getRadius()));
            curr.setFill(Color.rgb((int) map(curr.getRadius()), 110, (int) map(curr.getRadius())));
        }
    }

    public void testPoints() {
        stars = new ArrayList<>();
        int offset = 0;
        int row = 0;
        for (int k = (int) (2 * maxStarSize); k < height - maxStarSize; k += 2 * maxStarSize + margin) {
            if (row % 2 == 0) {
                offset = (int) maxStarSize;
            } else {
                offset = 0;
            }
            for (int i = (int) (2 * maxStarSize + offset); i < width - maxStarSize; i += 2 * maxStarSize + margin) {
                Circle test = new Circle();
                test.setRadius(Math.random() * maxStarSize + minStarSize);
                test.setCenterX(i);
                test.setCenterY(k);
                test.setFill(Color.BROWN);
                stars.add(test);
                pane.getChildren().add(test);
            }
            row++;
        }
        direction = new int[stars.size()];
        for (int i = 0; i < direction.length; i++) {
            double maybe = Math.random() * 100;
            if (maybe <= 50) {
                direction[i] = 1;
            }
        }
        this.makeConstilations();
    }

    private void makeConstilations() {
        for (Circle a : stars) {
            double oldWidth = a.getRadius();
            a.setRadius(maxStarSize * 2 + margin);
            for (Circle b : stars) {
                if (b != a && a.intersects(b.getBoundsInParent())) {
                    double maybe = Math.random() * 100;
                    if (maybe <= 30) {
                        this.connect(a, b);
                    }
                }
            }
            a.setRadius(oldWidth);
        }
    }

    protected void connect(Circle one, Circle two) {
        Node n1 = one, n2 = two;
        double strokeWidth;
        if (one.getRadius() < two.getRadius()) {
            strokeWidth = one.getRadius() / 2;
        } else {
            strokeWidth = two.getRadius() / 2;
        }
        if (pane == null) {
            System.out.println("Tis null");
        } else {
            Pane parent = pane;
            Line line = new Line();
            line.setStrokeWidth(strokeWidth);
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
            parent.getChildren().add(line);
            n1.toFront();
            n2.toFront();
        }
    }

    private double map(double x) {
        double inMin = 0, inMax = this.maxStarSize+1, outMin = 50, outMax = 210;
        double done = (double) (outMin + ((outMax - outMin) / (inMax - inMin)) * (x - inMin));
        return done;
    }

}
