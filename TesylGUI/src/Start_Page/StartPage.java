/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Start_Page;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Kolbe
 */
public class StartPage {
    
    private Stage stage;
    private final StartView view;
    
    
    public StartPage(Stage stage){
        view = new StartView(stage);
    }
    
    public Pane getView(){
        return view.getViewpane();
    }
    
}