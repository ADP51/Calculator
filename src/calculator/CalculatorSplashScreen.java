/*
 * File name: CalculatorSplashScreen
 * Author: Andrew Palmer
 * Course: CST8221 - JAP 302
 * Assignment: 1
 * Date: 2019-10-15
 * Professor: Svillen Ranev
 * Purpose: Creates a splash screen for the calculator application
 */
package calculator;

import java.awt.*;
import javax.swing.*;

/**
 * Author: Andrew Palmer
 * Version: 1.0
 * See: calculator
 * Since: 1.8.222
 */
public class CalculatorSplashScreen extends JWindow {

    /**
     * Swing components are serializable and require serialVersionUID
     */
    private static final long serialVersionUID = 6248477390124803341L;

    /**
     * Splash screen duration
     */
    private final int duration;

    /**
     * The constructor for a new splash screen object
     * @param duration the duration that the splash screen lasts
     */
    protected CalculatorSplashScreen(int duration) {
        this.duration = duration;
    }

    /**
     * Launches and sets the properties of the splash screen object
     */
    protected void showSplashWindow() {

        JPanel content = new JPanel(new BorderLayout());//create content pane
        content.setBackground(Color.BLACK);

        int width = 700; // the width of the splash screen
        int height = 500; // the height of the splash screen
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;  // calculate the x value for the position
        int y = (screen.height - height) / 2; // calculate the y value for the position
        setBounds(x, y, width, height); // set the bounds of the splash screen

        JLabel label = new JLabel(new ImageIcon(getClass().getResource("splash.jpeg"))); // gets the picture for the splash screen

        JLabel demo = new JLabel("Andrew Palmer - 040719945", JLabel.LEFT); // sets the label for the bottom of the splash screen
        demo.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
        demo.setForeground(Color.WHITE);
        content.add(label, BorderLayout.CENTER);
        content.add(demo, BorderLayout.SOUTH);

        content.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));

        setContentPane(content);

        setVisible(true);

        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dispose();
    }
}
