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
    private double radius = 10.0,
            emitDistance = 20.0;

    private int index;
    private Pane parent;
    private double angle;
    private Vector velocity;
    private Vector position;
    private ArrayList<Circle> children;

    protected Emitter(Pane parent) {
        this.parent = parent;
        setupPosition();
    }

    protected void setupPosition() {
        position = new Vector(parent.getMaxWidth() / 2, parent.getMinHeight() / 2);
        angle = 0;
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
        Circle last = null;
        if(children.size() > 0)
            last = children.get(children.size() - 1);
        if (last == null) {
            emit();
        } else if (position.distance(new Vector(last.getCenterX(), last.getCenterY())) > emitDistance) {
            emit();
        }
    }

    private void emit() {
        Circle hold = new Circle();
        hold.setFill(color);
        hold.setCenterX(position.x);
        hold.setCenterY(position.y);
        hold.setRadius(radius);
        children.add(hold);
        parent.getChildren().add(hold);
    }

    protected void update() {
        emitNew();
        for (Circle curr : children) {
            double x = curr.getCenterX() + velocity.x;
            double y = curr.getCenterY() + velocity.y;
            curr.setCenterX(x);
            curr.setCenterY(y);
        }
    }

}
