/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Main;

import Background_Starfield.Background_Starfield;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Kolbe
 */
public class Main_View {

    private BorderPane root;
    private Pane background;
    private VBox header;

    protected Main_View() {
        setup();
    }

    private void setup() {
        root = new BorderPane();
        root.setStyle("-fx-background-color: gray");
        setupBackground();
    }
    
    protected Pane getRoot(){
        return this.root;
    }
    
    protected void setStage(Stage home){        
        root.minHeightProperty().bind(home.minHeightProperty());
        root.minWidthProperty().bind(home.minWidthProperty());
    }
    
    private void setupBackground(){
//        Background_Starfield hold = new Background_Starfield(root);
//        background = hold.getPane();
//        root.getChildren().add(background);
    }
    
    private void setupHeader(){
        header = new VBox();
//        header.getStyleClass().add("header");
        header.minWidthProperty().bind(root.widthProperty());
        header.setStyle("-fx-background-color: red");
        header.minHeightProperty().bind(root.heightProperty().divide(5));
        root.getChildren().add(header);
        root.setTop(header);
        header.toFront();
    }
    
    private void setupSelector(){
        
    }
    
    private void setupChoices(){
        
    }
}
