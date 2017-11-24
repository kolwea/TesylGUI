/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Polka;

import Background_Polka.Point;
import Tools.Vector;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Kolbe
 */
public class Emitter {

    //Class variables///////////////////////////////////////////////////////////
    private Pane parent;
    private double radius;
    private Vector velocity;
    private Vector position;
    private Color color;
    private double emitSpeed;
    private double emitDistance;
    private ArrayList<Point> children;

    //Constructor Functions/////////////////////////////////////////////////////
    protected Emitter(Pane parent) {
        setup(parent);
    }

    //Class Functions///////////////////////////////////////////////////////////
    protected void setup(Pane parent) {
        this.parent = parent;
    }

    protected void setupInit(Vector position, Vector velocity,double radiusthis, double radius, Color color, double emitDist, double emitSpeed) {
        this.children = new ArrayList<>();
        this.position = position;
        this.velocity = velocity;
        this.color = color;
        this.emitDistance = emitDist;
        this.radius = radius*2;
        this.emitSpeed = emitSpeed;

        Circle body = new Circle();
        body.setCenterX(position.x);
        body.setCenterY(position.y);
//        body.setFill(Color.BLUE);
        body.setRadius(radiusthis);
        parent.getChildren().add(body);

    }

    protected void update() {
        ArrayList<Point> removal = new ArrayList();
        emitNew();
        for (Point curr : children) {
            if (curr.inbound) {
                curr.update();
            } else {
                removal.add(curr);
            }
        }
        Point.updateBounds();
        for (Point curr : removal) {
            parent.getChildren().remove(curr.getBody());
            children.remove(curr);
        }
    }
    
    protected void setEmitDistance(double dist){
        this.emitDistance = dist;
    }
    
    protected Vector getPosition(){
        return this.position;
    }

    //Helper Functions//////////////////////////////////////////////////////////
    private void emitNew() {
        Point last = null;
        if (children.size() > 0) {
            last = children.get(children.size() - 1);
        }
        if (last == null) {
            emit();
        } else if (position.distance(last.getPosition()) > emitDistance) {
            emit();
        }
    }

    private void emit() {
        Point hold = new Point();
        hold.setupInit(this.parent, position, velocity, radius, color, emitSpeed);
        children.add(hold);
        parent.getChildren().add(hold.getBody());
    }

}
