/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Polka;

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
    private Point last;

    //Constructor Functions/////////////////////////////////////////////////////
    protected Emitter(Pane parent) {
        setup(parent);
    }

    //Class Functions///////////////////////////////////////////////////////////
    protected void setup(Pane parent) {
        this.parent = parent;
    }

    protected void setupInit(Vector position, Vector velocity, double radiusthis, double radius, Color color, double emitDist, double emitSpeed) {
        this.position = position;
        this.velocity = velocity;
        this.color = color;
        this.emitDistance = emitDist;
        this.radius = radius * 2;
        this.emitSpeed = emitSpeed;
        this.children = new ArrayList<>();

        Circle body = new Circle();
        body.setCenterX(position.x);
        body.setCenterY(position.y);
//        body.setFill(Color.BLUE);
        body.setRadius(0);
        parent.getChildren().add(body);

    }

    protected void update() {
        if(children.isEmpty())
            last = null;
        ArrayList<Point> remove = new ArrayList<>();
        emitNew();
        Point.updateBounds();
        for (Point curr : children) {
            curr.update();
            if (!curr.inbound && (position.distance(curr.getPosition()) % emitDistance == 0)) {
                remove.add(curr);
            }
        }
        for (Point curr : remove) {
            parent.getChildren().remove(curr.getBody());
            children.remove(curr);
        }
    }

    protected void setEmitDistance(double dist) {
        this.emitDistance = dist;
    }

    protected Vector getPosition() {
        return this.position;
    }

    //Helper Functions//////////////////////////////////////////////////////////
    private void emitNew() {
        if (last == null) {
            emit();
        } else if (position.distance(last.getPosition().add(velocity)) == emitDistance){
            emit();
        }
    }

    private void emit() {
        Point hold = new Point();
        hold.setupInit(this.parent, position, velocity, radius, color, emitSpeed);
        last = hold;
        children.add(hold);
        parent.getChildren().add(hold.getBody());
    }

}
