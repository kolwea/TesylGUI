/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Starfield;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Kolbe
 */
public class Star {

    private Circle body;
    private Vector position;
    private ArrayList<Star> neighbors;
    private boolean grow;
    private static double growRate, minStarSize, maxStarSize;
    private double localMinSize, localMaxSize;

    protected Star() {
        setupBody();
    }

    protected Star(Vector initialPosition, double preferredRadius) {
        setupBody();
        this.setPosition(initialPosition);
        this.setPreferedSize(preferredRadius);
        growRate = 0.03;
        minStarSize = 5.0;
        maxStarSize = 18.0;
    }

    protected void setPosition(Vector pos) {
        this.position = pos;
        body.setCenterX(position.x);
        body.setCenterY(position.y);
    }

    protected void setPreferedSize(double size) {
        body.setRadius(size);
        localMinSize = size;
        localMaxSize = size;
        if (localMinSize - 3 > minStarSize) {
            localMinSize -= 5;
        }
        if (localMaxSize + 3 < maxStarSize) {
            localMaxSize += 3;
        }
    }

    protected static void setGrowRate(double rate) {
        growRate = rate;
    }
    
    protected void setGrow(boolean what){
        grow = what;
    }
    
    protected Circle getBody(){
        return this.body;
    }

    //Update/////////////////////
    protected void update() {
        updateSize();
        updateColor();
    }

    protected void updateSize() {
        double radius = body.getRadius();
        if (grow) {
            body.setRadius(radius + Math.random() * growRate);
            radius = body.getRadius();
            if (radius >= localMaxSize) {
                grow = false;
                updateSize();
            }
        } else {
            body.setRadius(radius - Math.random() * growRate);
            radius = body.getRadius();
            if (radius <= localMinSize) {
                grow = true;
                updateSize();
            }
        }
    }

    private void updateColor() {
        double radius = body.getRadius();
        body.setFill(Color.rgb(
                (int) (mapColor(radius) + Math.random() * 3),
                (int) (mapColor(radius) + Math.random() * 3),
                (int) (mapColor(radius) + Math.random() * 3)
        ));
    }
    
    //Helper Functions//////////////////////////////////////////////////////////
    
    private void setupBody(){
        body = new Circle();
        grow = false;
    }

    private double mapColor(double x) {
        double inMin = 0, inMax = localMaxSize+1, outMin = 1, outMax = 255;
        double done = (double) (outMin + ((outMax - outMin) / (inMax - inMin)) * (x - inMin));
        return done;
    }

}
