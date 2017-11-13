/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Starfield;

import javafx.scene.layout.Pane;

/**
 *
 * @author Kolbe
 */
public abstract class Background {
    protected Pane pane,parent;
    protected double width,height;
     
    public Background(Pane parent){
     this.parent = parent;
    }
    
    
    public abstract void updateBackground();
    
    public abstract Pane getPane();
}
