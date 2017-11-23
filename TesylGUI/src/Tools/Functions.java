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
public class Functions {

    public Functions() {

    }

    public static double distance(double x1, double y1, double x2, double y2) {
        double a = Math.pow((x2 - x1), 2);
        double b = Math.pow((y2 - y1), 2);
        
        return Math.sqrt(a+b);
    }
}
