/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

/**
 *
 * @author Kolbe
 */
public class Vector {

    public double x, y;

    public Vector() {

    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector me) {
        Vector hai = new Vector(x + me.x, y + me.y);
        return hai;
    }

    public Vector sub(Vector me) {
        Vector hai = new Vector(x - me.x, y - me.y);
        return hai;
    }

    public void printVector() {
        System.out.println("X: " + x + " Y: " + y);
    }

    @Override
    public Vector clone() {
        return new Vector(x, y);
    }

    public static Vector angleToVector(double angle) {
        double x = getAngleVelocityX(angle), y = getAngleVelocityY(angle);
        return new Vector(x, y);

    }

    private static double getAngleVelocityX(double x) {
        double angle;
        if (x > 180) {
            angle = mapXOver(x);
        } else {
            angle = x;
        }
        double inMin = 0, inMax = 180, outMin = 1.000, outMax = -1.000;
        double done = (double) (outMin + ((outMax - outMin) / (inMax - inMin)) * (angle - inMin));
        return done;
    }

    private static double mapXOver(double x) {
        double inMin = 180, inMax = 360, outMin = 180, outMax = 0;
        double done = (double) (outMin + ((outMax - outMin) / (inMax - inMin)) * (x - inMin));
        return done;
    }

    private static double getAngleVelocityY(double x) {
        double angle;
        if (x > 270) {
            angle = mapYHigh(x);
        } else if (x < 90) {
            angle = mapYLow(x);
        } else {
            angle = x;
        }
        double inMin = 90, inMax = 270, outMin = -1.000, outMax = 1.000;
        double done = (double) (outMin + ((outMax - outMin) / (inMax - inMin)) * (angle - inMin));
        return done;
    }

    private static double mapYLow(double x) {
        double inMin = 0, inMax = 90, outMin = 180, outMax = 90;
        double done = (double) (outMin + ((outMax - outMin) / (inMax - inMin)) * (x - inMin));
        return done;
    }

    private static double mapYHigh(double x) {
        double inMin = 270, inMax = 360, outMin = 270, outMax = 180;
        double done = (double) (outMin + ((outMax - outMin) / (inMax - inMin)) * (x - inMin));
        return done;
    }
    
    public double distance(Vector comp){
        return Functions.distance(x, y, comp.x, comp.y);
    }
}
