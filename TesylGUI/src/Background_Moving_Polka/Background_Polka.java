/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Moving_Polka;

import Tools.Vector;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Kolbe
 */
public class Background_Polka {

    //Constant Variables////////////////////////////////////////////////////////
    private final double KEYFRAME_DURATION = 10.0; //In milliseconds

    private final double EMITTER_RADIUS = 20.0;
    private final double EMIT_RADIUS = 10.0; //radius size of emitted circles
    private final Color EMIT_COLOR = Color.RED;
    private final double EMIT_SPEED = 2.0; //multiplier for speed of emitted cirlces. 1.0 = 100% speed;
    private final double EMITTER_PADDING = 100;
    private final double INIT_VECTOR_ANGLE = 45;
    private final double EMIT_DISTANCE = 0;
    private final double CHANGE_FRAME_INTERVAL = 100;

    //Class Variables///////////////////////////////////////////////////////////
    private Pane rootPane;
    private Stage stage;
    private KeyFrame keyframe;
    private Timeline timeline;
    private ArrayList<Emitter> emitters;
    private double frameCount;
//    private Emitter emitters;
    //Contructor Functions//////////////////////////////////////////////////////

    public Background_Polka(Stage stage) {
        this.stage = stage;
        setup();
        test();
        setupTimeline();
    }

    //Class Functions///////////////////////////////////////////////////////////
    public Pane getViewpane() {
        return rootPane;
    }

    //Helper Functions//////////////////////////////////////////////////////////
    private void setup() {
        rootPane = new Pane();
        rootPane.minHeightProperty().bind(stage.minHeightProperty());
        rootPane.minWidthProperty().bind(stage.minWidthProperty());
        rootPane.setStyle(
                "-fx-background-color: #FFFFFF;"
        //                + "-fx-border-color: red;"
        //                + "-fx-border-width: 10px;"
        );
    }

    private void setupTimeline() {
        keyframe = new KeyFrame(Duration.millis(KEYFRAME_DURATION), (ActionEvent event) -> {
            update();
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void update() {
        for (Emitter curr : emitters) {
            curr.update();
        }
    }

    private void test() {
        emitters = new ArrayList<>();
        double dist = 0, i, k;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//                    hold.setupInit(pos, INIT_VECTOR_ANGLE, EMITTER_RADIUS, EMIT_RADIUS, EMIT_COLOR, EMIT_DISTANCE, EMIT_SPEED);

        for (i = -(EMITTER_RADIUS + EMITTER_PADDING); i < screenSize.getWidth() + (EMITTER_RADIUS + EMITTER_PADDING); i += EMITTER_RADIUS + EMITTER_PADDING) {
            for (k = -(EMITTER_RADIUS + EMITTER_PADDING); k < screenSize.getHeight() + (EMITTER_RADIUS + EMITTER_PADDING); k += EMITTER_RADIUS + EMITTER_PADDING) {
                if ((i < 0 || i >= screenSize.getWidth()) || (k < 0 || k >= screenSize.getHeight())) {
                    Emitter hold = new Emitter(rootPane);
                    Vector pos = new Vector(k, i);
                    if (k >= screenSize.getWidth()/2) {
                        System.out.println(i);
                        hold.setupInit(pos, INIT_VECTOR_ANGLE+90, EMITTER_RADIUS, EMIT_RADIUS, EMIT_COLOR, EMIT_DISTANCE, EMIT_SPEED);
                    } else {
                        hold.setupInit(pos, INIT_VECTOR_ANGLE, EMITTER_RADIUS, EMIT_RADIUS, EMIT_COLOR, EMIT_DISTANCE, EMIT_SPEED);
                    }
                    emitters.add(hold);
                }
            }
        }

        Emitter first = emitters.get(0);
        boolean found = false;
        Vector space = new Vector(EMITTER_RADIUS + EMITTER_PADDING, EMITTER_RADIUS + EMITTER_PADDING);
        for (int j = 1; j < emitters.size(); j++) {
            if (found == false) {
                Emitter curr = emitters.get(j);
                if (curr.getPosition().y != first.getPosition().y) {
                    dist = curr.getPosition().distance(first.getPosition());
                    found = true;
                }
            }
        }

        if (dist == 0) {
            System.out.println("WHAAAAAAAt");
            dist = 20;
        }
        for (Emitter a : emitters) {
            a.setEmitDistance(dist);
        }
    }

}
