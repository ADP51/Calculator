/*
 * File name: Calculator
 * Author: Andrew Palmer
 * Course: CST8221 - JAP 302
 * Assignment: 1
 * Date: 2019-10-15
 * Professor: Svillen Ranev
 * Purpose: The main for the calculator application
 */
package calculator;

import java.awt.*;
import javax.swing.*;

/**
 * The calculator class contains the main function for the application.
 */
public class Calculator {

    public static void main(String[] args) {
        CalculatorSplashScreen splashScreen = new CalculatorSplashScreen(5000); // create new splash screen object with 5 sec timer
        splashScreen.showSplashWindow();

        // invoke a new runnable instance of Calculator after the splash screen finishes
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Calculator");
                frame.setPreferredSize(new Dimension(380, 540));
                frame.setContentPane(new CalculatorViewController());
                frame.setLocationByPlatform(true);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
