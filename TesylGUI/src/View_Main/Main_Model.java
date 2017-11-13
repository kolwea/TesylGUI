/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Main;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Kolbe
 */
public class Main_Model {

    private Main_View view;
    private Main_Controller controller;

    public Main_Model(Stage home) {
        view = new Main_View();
        view.setStage(home);
       
    }

    public Pane getRoot() {
        return view.getRoot();
    }

    private void setStage(Stage home) {
        view.setStage(home);
    }
}
