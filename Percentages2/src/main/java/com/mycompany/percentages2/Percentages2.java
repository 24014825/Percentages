/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.percentages2;

/**
 *
 * @author 24014825
 */
import java.util.Scanner;
public class Percentages2 {

       public static void computePercent(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Cannot divide by zero!");
            return;
        }
        double percent = (num1 / num2) * 100;
        System.out.println(num1 + " is " + percent + " percent of " + num2);
    }
          public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double a = input.nextDouble();

        System.out.print("Enter second number: ");
        double b = input.nextDouble();
          computePercent(a, b);
          computePercent(b, a);
            input.close();
     
    }
}
