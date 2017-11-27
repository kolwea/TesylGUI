/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Polka;

import Tools.Vector;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Kolbe
 */
public class Point {

    //Class Variables///////////////////////////////////////////////////////////
    private Vector position;
    private Vector velocity;
    private Circle body;
    private double speed;
    private double outboundCount;
    protected boolean inbound;

    //Static Variables//////////////////////////////////////////////////////////
    private static Pane pane;
    private static double width, height;
    private static final double MAXOUTBOUNDCOUNT = 200;

    protected Point() {
        setup();
    }

    //Class Functions///////////////////////////////////////////////////////////
    protected void setPosition(Vector pos) {
        this.position = pos;
        if (body != null) {
            body.setCenterX(pos.x);
            body.setCenterY(pos.y);
        }
    }

    protected Vector getPosition() {
        return this.position;
    }

    protected void setSpeed(double speed) {
        this.speed = speed;
    }

    protected double getSpeed() {
        return this.speed;
    }

    protected void setBody(Circle body) {
        this.body = body;
        this.position = new Vector(body.getCenterX(), body.getCenterY());
    }

    protected Circle getBody() {
        return this.body;
    }

    protected void setVelocity(Vector vect) {
        velocity = vect;
    }

    protected Vector getVelocity() {
        return this.velocity;
    }

    protected void setColor(Color col) {
        body.setFill(col);
    }

    protected Color getColor() {
        return (Color) body.getFill();
    }

    protected void update() {
        checkInBound();
        Vector vel = new Vector(velocity.x*speed,velocity.y*speed);
        position = position.add(vel);
        body.setFill(Color.rgb(35, (int) mapColor(position.x + position.y), 107));
        body.setCenterX(position.x);
        body.setCenterY(position.y);
    }

    protected void setupInit(Pane pane, Vector pos, Vector velocity, double radius, Color color, double speed) {
        this.position = pos;
        this.velocity = velocity;
        this.speed = speed;
        Point.pane = pane;

        body.setCenterX(position.x);
        body.setCenterY(position.y);
        body.setRadius(radius);
        body.setFill(color);
    }

    //Static Functions//////////////////////////////////////////////////////////
    protected static void updateBounds() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        width = screenSize.getWidth();
        height = screenSize.getHeight();
    }

    //Helper Functions//////////////////////////////////////////////////////////
    private void setup() {
        this.inbound = true;
        this.body = new Circle();
    }

    private void checkInBound() {
        if (position.x > width || position.x < 0) {
            outboundCount++;
        } else if (position.y < 0 || position.y > height) {
            outboundCount++;
        }

        if (outboundCount > MAXOUTBOUNDCOUNT) {
            inbound = false;
        }
    }

    private double mapColor(double x) {
        double inMin = 0, inMax = pane.getMaxHeight() + pane.getMinWidth() + 1, outMin = 100, outMax = 205;
        double done = (double) (outMin + ((outMax - outMin) / (inMax - inMin)) * (x - inMin));
        if (done >= 255) {
            done = 255;
        }
        return done;
    }

}
