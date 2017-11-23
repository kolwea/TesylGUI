/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Start_Page;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Kolbe
 */
public class StartView {

    private Pane rootPane;
    private Stage stage;
    private KeyFrame keyframe;
    private Timeline timeline;
    
    private Emitter test;

    private Pane background;

    protected StartView(Stage stage) {
        this.stage = stage;
        setup();
        test();
        setupTimeline();
    }

    protected Pane getViewpane() {
        return rootPane;
    }

    private void setup() {
        rootPane = new Pane();
        rootPane.minHeightProperty().bind(stage.minHeightProperty());
        rootPane.minWidthProperty().bind(stage.minWidthProperty());
        rootPane.setStyle(
                "-fx-background-color: #223344;"
                + "-fx-border-color: red;"
                + "-fx-border-width: 10px;");
    }

    private void setupTimeline() {
        keyframe = new KeyFrame(Duration.millis(10), (ActionEvent event) -> {
            update();
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void update() {
        test.update();
    }

    private void test() {
        test = new Emitter(rootPane);

    }

}
