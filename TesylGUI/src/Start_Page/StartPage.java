/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Start_Page;

import Background_Moving_Polka.Background_Polka;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Kolbe
 */
public class StartPage {

    private Stage stage;
    private final Background_Polka background;
    private Pane rootPane;

    public StartPage(Stage stage) {
        background = new Background_Polka(stage);
        rootPane = background.getViewpane();
        rootPane.setStyle("-fx-background-color: #292F36;");

    }

    public Pane getView() {
        return rootPane;
    }

}
