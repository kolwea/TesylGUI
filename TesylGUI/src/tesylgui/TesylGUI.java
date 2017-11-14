/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesylgui;

import Background_Starfield.Background_Starfield;
import View_Main.Main_Model;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Kolbe
 */
public class TesylGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(1100);
        Main_Model main = new Main_Model(primaryStage);
        Pane root = main.getRoot();
//        Background_Starfield stars = new Background_Starfield(root);

        Button btn = new Button();
        btn.setText("Populate");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                if (stars != null) {
//                    stars.testPoints();
//                }
            }
        });

//        root.getChildren().addAll(stars.getPane(), btn);
        Scene scene = new Scene(root, 1100, 600);
        scene.getStylesheets().add("tesylgui/MainStyle.css");

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
