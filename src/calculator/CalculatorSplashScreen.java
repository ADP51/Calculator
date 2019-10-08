package calculator;

import java.awt.*;
import javax.swing.*;

public class CalculatorSplashScreen extends JWindow {

    /**
     * Swing components are serializable and require serialVersionUID
     */
    private static final long serialVersionUID = 6248477390124803341L;
    /**
     * Splash screen duration
     */
    private final int duration;

    public CalculatorSplashScreen(int duration) {
        this.duration = duration;
    }

    public void showSplashWindow() {
        //create content pane
        JPanel content = new JPanel(new BorderLayout());
        // or use the window content pane
        //  JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.BLACK);

        // Set the window's bounds, position the window in the center of the screen
        int width = 700 + 10;
        int height = 900 + 10;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        //set the location and the size of the window
        setBounds(x, y, width, height);

        JLabel label = new JLabel(new ImageIcon(getClass().getResource("splash.jpeg")));

        JLabel demo = new JLabel("Andrew Palmer - 040719945", JLabel.LEFT);
        demo.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
        demo.setForeground(Color.WHITE);
        content.add(label, BorderLayout.CENTER);
        content.add(demo, BorderLayout.SOUTH);
        // create custom RGB color

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
