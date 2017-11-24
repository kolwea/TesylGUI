/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesylgui;

import Start_Page.StartPage;
import Tools.Vector;
import View_Main.Main_Model;
import javafx.application.Application;
import javafx.scene.Scene;
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
        
        primaryStage.setHeight(600);
        primaryStage.setWidth(1100);

        Main_Model main = new Main_Model(primaryStage);
        StartPage start = new StartPage(primaryStage);
        
        Pane root = start.getView();
        
        Scene scene = new Scene(root);

        primaryStage.setTitle("Tesyl");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        Vector.angleToVector(0);
    }

}
