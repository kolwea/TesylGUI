/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Starfield;

import java.util.ArrayList;
import javafx.scene.shape.Circle;

/**
 *
 * @author Kolbe
 */
public class Star {
    private Circle body;
    private Vector position;
    private ArrayList<Star> neighbors;
    private double size;
    
    protected Star(){
        
    }
    
    protected void setPosition(Vector pos){
        this.position = pos;
    }
    
    protected void setPreferedSize(double size){
        this.size = size;
    }
    
    protected void pulse(){
    }
}
