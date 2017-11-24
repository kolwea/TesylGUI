/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Polka;

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
    private final double KEYFRAME_DURATION = 50.0; //In milliseconds

    private final double EMITTER_RADIUS = 50.0;
    private final double EMIT_RADIUS = 5.0; //radius size of emitted circles
    private final Color EMIT_COLOR = Color.ANTIQUEWHITE;
    private final double EMIT_SPEED = 1.0; //multiplier for speed of emitted cirlces. 1.0 = 100% speed;
    private final double EMITTER_PADDING = 0;
    private final double EMIT_DISTANCE = 2 * EMIT_RADIUS + EMITTER_PADDING;

    //Class Variables///////////////////////////////////////////////////////////
    private Pane rootPane;
    private Stage stage;
    private KeyFrame keyframe;
    private Timeline timeline;
    private ArrayList<Emitter> emitters;
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

    //Helepr Functions//////////////////////////////////////////////////////////
    private void setup() {
        rootPane = new Pane();
        rootPane.minHeightProperty().bind(stage.minHeightProperty());
        rootPane.minWidthProperty().bind(stage.minWidthProperty());
        rootPane.setStyle(
                "-fx-background-color: #FFFFFF;"
                + "-fx-border-color: red;"
                + "-fx-border-width: 10px;");
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
//    emitters.update();
    }

    private void test() {
        emitters = new ArrayList<>();

        double dist = 0;
        for (int i = 0; i < rootPane.getMinHeight(); i += EMITTER_RADIUS + EMITTER_PADDING) {
            for (int k = 0; k < rootPane.getMinWidth(); k += EMITTER_RADIUS + EMITTER_PADDING) {
                Emitter hold = new Emitter(this.rootPane);
                Vector pos = new Vector(k, i);
                Vector velo = Vector.angleToVector(0);
                hold.setupInit(pos, velo, EMITTER_RADIUS, EMIT_RADIUS, EMIT_COLOR, EMIT_DISTANCE, EMIT_SPEED);
                emitters.add(hold);
            }
        }
        double distHold = 0;
        for (int j = 0; j < emitters.size()-1; j++) {
            Emitter one = emitters.get(j);
            Emitter two = emitters.get(j + 1);
            if (one == null || two == null); else {
                distHold = one.getPosition().distance(two.getPosition());
                if (distHold > dist && distHold < rootPane.getMinHeight()/2) {
                    dist = distHold;
                }
            }

        }
        for (Emitter a : emitters) {

            a.setEmitDistance(dist);
        }
    }

}
