package calculator;

import java.awt.*;
import javax.swing.*;

public class Calculator {

    public static void main(String[] args) {
        CalculatorSplashScreen splashScreen = new CalculatorSplashScreen(5000);
        splashScreen.showSplashWindow();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Calculator");
                frame.setPreferredSize(new Dimension(380, 540));
                frame.setContentPane(new CalculatorViewController());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
