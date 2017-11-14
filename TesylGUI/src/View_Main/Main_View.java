/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Main;

import Background_Starfield.Background_Starfield;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private HBox selector;
     

    protected Main_View(Stage home) {
        setup();
        setStage(home);
    }

    private void setup() {
        root = new BorderPane();
        root.setStyle("-fx-background-color: #454955");

    }

    protected Pane getRoot() {
        return this.root;
    }

    protected void setStage(Stage home) {
//        root.setMinHeight(home.getMinHeight());
//        root.setMinWidth(home.getMinWidth());
        root.minHeightProperty().bind(home.minHeightProperty());
        root.minWidthProperty().bind(home.minWidthProperty());
        System.out.println(root.getMinWidth() + " " + root.getMinHeight());
        setupBackground();
        setupSelector();
    }

    private void setupBackground() {
        Background_Starfield hold = new Background_Starfield(root);
        background = hold.getPane();
        root.getChildren().add(background);
    }

    private void setupHeader() {
        header = new VBox();
//        header.getStyleClass().add("header");
        header.minWidthProperty().bind(root.widthProperty());
        header.setStyle("-fx-background-color: red");
        header.minHeightProperty().bind(root.heightProperty().divide(5));
        root.getChildren().add(header);
        root.setTop(header);
        header.toFront();
    }

    private void setupSelector() {
        selector = new HBox();
        root.setLeft(selector);
        selector.toFront();
        selector.minWidthProperty().bind(root.minWidthProperty().divide(3));
        selector.minHeightProperty().bind(root.minHeightProperty());
        System.out.println(selector.getMinWidth() + " SELECTOR " + selector.getMinHeight());
        selector.toFront();
        selector.setStyle(
                "-fx-background-color: #f3eff5;"+                
//                "-fx-background-insets: 10; " +
                "-fx-effect: dropshadow(gaussian, black, 10, 0, 2, 0);");
    }

    private void setupChoices() {

    }
}
