/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Main;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Kolbe
 */
public class GraphBox {
    private StackPane pane;
    private final VBox parent;
    private Text name;
    private int index;
    
    protected GraphBox(VBox parent){
        this.parent = parent;
        setup();
    }
    
    protected Pane getPane(){
        return this.pane;
    }
    
    private void setup(){
        pane = new StackPane();
        name = new Text();
        
        pane.setStyle("-fx-background-color: red;");
        
        name.setText("A*");
        pane.getChildren().add(name);
        pane.setAlignment(Pos.CENTER);
                
        pane.minWidthProperty().bind(parent.minWidthProperty());
        pane.minHeightProperty().bind(parent.minHeightProperty().divide(5));
    }
    
    
    
}
