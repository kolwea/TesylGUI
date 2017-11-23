/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Start_Page;

import Tools.Vector;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Kolbe
 */
public class Point {

    private Vector pos;
    private Vector vector;
    private Circle body;
    private double outboundCount;
    protected boolean inbound;

    private static Pane parent;
    private static double width, height;
    private static final double maxOutboundCount = 100;

    private double radius = 10;
    private Color color = Color.VIOLET;

    protected Point(Pane par, Vector initPosition) {
        parent = par;
        inbound = true;
        pos = initPosition;
        setup();
    }

    protected void setPosition(Vector pos) {
        this.pos = pos;
        if (body != null) {
            body.setCenterX(pos.x);
            body.setCenterY(pos.y);
        }
    }

    protected Circle getBody() {
        return this.body;
    }

    protected void setVector(Vector vect) {
        vector = vect;
    }

    protected void update() {
        checkInBound();
        pos = pos.add(vector);
        body.setCenterX(pos.x);
        body.setCenterY(pos.y);
    }

    protected static void updateBounds() {
        width = parent.getMinWidth();
        height = parent.getMinHeight();
    }

    private void setup() {
        body = new Circle();
        body.setCenterX(pos.x);
        body.setCenterY(pos.y);
        body.setRadius(radius);
        body.setFill(color);
    }

    private void checkInBound() {
        if (pos.x > width || pos.x < 0) {
            outboundCount++;
        } else if (pos.y < 0 || pos.y > height) {
            outboundCount++;
        }

        if (outboundCount > maxOutboundCount) {
            inbound = false;
        }
    }

}
