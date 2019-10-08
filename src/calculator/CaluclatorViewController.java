package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Author: Andrew Palmer
 */
public class CaluclatorViewController extends JPanel {
    private JTextField display1;
    private JTextField display2;
    private JLabel error;
    private JButton dotButton;
    private JButton[] hexButtons;

    /**
     * Constructor for CalculatorViewController creates a new panel
     * with a border layout and sets the border to a matte black border
     */
    public CaluclatorViewController() {
        JPanel calc = new JPanel(new BorderLayout());
        calc.setPreferredSize(new Dimension(380, 540)); //set the size of the application panel on screen
        calc.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));

        // panels to be added to calculator
        //TODO combine top two rows
        JPanel topRow = new JPanel(new BorderLayout());
        JPanel display = new JPanel(new BorderLayout());
        JPanel row2 = new JPanel(new BorderLayout());
        JPanel main = new JPanel(new BorderLayout()); // the panel that will contain all of arithmetic buttons
        JPanel leftArithmetic = new JPanel(new GridLayout(2, 1)); // multiply/divide buttons on the left of main
        JPanel rightArithmetic = new JPanel(new GridLayout(2, 1)); // +/- buttons on right side of main
        JPanel middleAritmetic = new JPanel(new GridLayout(3, 1)); // clear and numpad and equals will go inside of this
        JPanel numpad = new JPanel(new GridLayout(6, 3)); // the numpad for the calc will go in middleArithmetic


        //Create the mode/error button will be added to the top row "EAST"
        JButton modeError = new JButton("F");
        modeError.setBackground(Color.yellow);
        modeError.setPreferredSize(new Dimension(52, 55));
        modeError.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 5, Color.black));
        topRow.add(modeError, BorderLayout.EAST); // add button to panel

        //add the display1 and display 2 into single panel then add to the topRow panel
        display.add(display1, BorderLayout.NORTH);
        display.add(display2, BorderLayout.SOUTH);
        topRow.add(display, BorderLayout.CENTER);

        //Create the backspace button and add it to the topRow Panel
        JButton backspace = new JButton("\u0008");
        backspace.setPreferredSize(new Dimension(52, 55));
        backspace.setBackground(Color.yellow);
        backspace.setOpaque(false); //Make transparent
        backspace.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 1, Color.black));
        backspace.setToolTipText("Backspace Alt-B");
        backspace.setMnemonic(KeyEvent.VK_B);
        topRow.add(backspace, BorderLayout.WEST);

        //Create the second row


        //TODO add the top two rows together







    }

    /**
     * Creates a new button
     *
     * @param text    the button label
     * @param ac      the action controller
     * @param fg      the foreground color
     * @param bg      the background color
     * @param handler the ActionEventListener
     * @return the button
     */
    private JButton createButton(String text, String ac, Color fg, Color bg, ActionListener handler) {
        JButton button = new JButton(text);
        button.setBackground(bg);
        button.setForeground(fg);

        if (ac != null) {
            button.setActionCommand(ac);
        }

        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.addActionListener(handler);

        return button;
    }

    /**
     * Private inner class to implement action listener for calculator
     */
    private class Controller implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            display2.setText(e.getActionCommand());
        }
    }
}
