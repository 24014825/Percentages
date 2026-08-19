/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.percentage;

/**
 *
 * @author 24014825
 */
public class Percentage {

    public static void computePercent(double num1, double num2) {
        double percent = (num1 / num2) * 100;
        System.out.println(num1 + " is " + percent + " percent of " + num2);
    }
     public static void main(String[] args) {
        double a = 2.0;
        double b = 5.0;

          computePercent(a, b);
          computePercent(b, a);
    }
}
