/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Start_Page;

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

    private Color color = Color.BLANCHEDALMOND;
    private double 
            radius = 10.0,
            emitDistance = 50.0;

    private int index;
    private Pane parent;
    private double angle;
    private Vector velocity;
    private Vector position;
    private ArrayList<Point> children;

    protected Emitter(Pane parent) {
        this.parent = parent;
        setupPosition();
    }

    protected void setupPosition() {
        position = new Vector(parent.getMaxWidth() / 2, parent.getMinHeight() / 2);
        angle = 45;
        velocity = Vector.angleToVector(angle);
        children = new ArrayList<>();

        Circle body = new Circle();
        body.setCenterX(position.x);
        body.setCenterY(position.y);
        body.setFill(Color.BLUE);
        body.setRadius(20.0);

        parent.getChildren().add(body);
    }

    private void emitNew() {
        Point last = null;
        if(children.size() > 0)
            last = children.get(children.size() - 1);
        if (last == null) {
            emit();
        } else if (position.distance(new Vector(last.getBody().getCenterX(), last.getBody().getCenterY())) > emitDistance) {
            emit();
        }
    }

    private void emit() {
        Point hold = new Point(parent,position);
        hold.setVector(velocity);
        children.add(hold);
        parent.getChildren().add(hold.getBody());
    }

    protected void update() {
        ArrayList<Point> removal = new ArrayList();
        emitNew();
        for (Point curr : children) {
            if(curr.inbound)
                curr.update();
            else{
                removal.add(curr);
            }
        }
        Point.updateBounds();
        for(Point curr : removal){
            System.out.println("REMOVED");
            parent.getChildren().remove(curr.getBody());
            children.remove(curr);
        }
    }

}
