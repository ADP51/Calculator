package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Author: Andrew Palmer
 */
public class CalculatorViewController extends JPanel {
    private JTextField display1;
    private JTextField display2;
    private JLabel error;
    private JButton dotButton;
    private JButton[] hexButtons = new JButton[6];

    /**
     * Constructor for CalculatorViewController creates a new panel
     * with a border layout and sets the border to a matte black border
     */
    public CalculatorViewController() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(380, 540)); //set the size of the application panel on screen
        this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));

        // panels to be added to this
        JPanel topRow = new JPanel(new BorderLayout()); // To be combined with the row2
        JPanel display = new JPanel(new BorderLayout()); //combining the two displays together add to top row
        JPanel row2 = new JPanel(new BorderLayout()); // to be combined with topRow
        JPanel top = new JPanel(new BorderLayout()); // combine the top two rows into this panel
        JPanel main = new JPanel(new BorderLayout()); // the panel that will contain all of arithmetic buttons
        JPanel leftArithmetic = new JPanel(new GridLayout(2, 1)); // multiply/divide buttons on the left of main
        JPanel rightArithmetic = new JPanel(new GridLayout(2, 1)); // +/- buttons on right side of main
        JPanel middleArithmetic = new JPanel(new BorderLayout()); // clear and numpad and equals will go inside of this
        JPanel numpad = new JPanel(new GridLayout(6, 3, 3, 3)); // the numpad for the this will go in middleArithmetic

        //Create the mode/error button will be added to the top row "EAST"
        JButton modeError = createButton("F", "Error", Color.BLACK, Color.yellow, new Controller());
        modeError.setPreferredSize(new Dimension(52, 55));
        modeError.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 5, Color.black));
        topRow.add(modeError, BorderLayout.WEST); // add button to panel

        //add the display1 and display 2 into single panel then add to the topRow panel
        display1 = new JTextField("0.0", 14);
        display2 = new JTextField("0.0", 14);
        display1.setPreferredSize(new Dimension(0, 30));
        display2.setPreferredSize(new Dimension(0, 30));
        display2.setHorizontalAlignment(JTextField.RIGHT);
        display.add(display1, BorderLayout.CENTER);
        display.add(display2, BorderLayout.CENTER);
        topRow.add(display, BorderLayout.CENTER);

        //Create the backspace button and add it to the topRow Panel
        JButton backspace = createButton("\u21da", "\u21da", Color.BLACK, Color.yellow, new Controller());
        backspace.setPreferredSize(new Dimension(52, 55));
        backspace.setOpaque(false); //Make transparent
        backspace.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 1, Color.black));
        backspace.setToolTipText("Backspace Alt-B");
        backspace.setMnemonic(KeyEvent.VK_B);
        topRow.add(backspace, BorderLayout.EAST);

        //Create the second row
        row2.setBackground(Color.BLACK);

        // Hexdecimal check box with attributes
        JPanel hexButtonPanel = new JPanel();
        JCheckBox hexCheck = new JCheckBox("Hex");
        hexCheck.setBackground(Color.GREEN);
        hexCheck.setOpaque(true);
        hexCheck.setActionCommand("Hex");
        hexCheck.addActionListener(new Controller());
        hexCheck.setBorderPainted(false);
        hexButtonPanel.add(hexCheck, BorderLayout.LINE_START);

        //Setup radio buttons
        JPanel radio = new JPanel(new GridLayout(1, 3));
        JRadioButton radioButtonOne = new JRadioButton(".0", false);
        radioButtonOne.setBackground(Color.YELLOW);
        radioButtonOne.setOpaque(true);
        radioButtonOne.setActionCommand("0.0");
        radioButtonOne.setBorderPainted(false);
        radioButtonOne.addActionListener(new Controller());

        // Selected at Default with attributes
        JRadioButton radioButtonTwo = new JRadioButton(".00", true);
        radioButtonTwo.setBackground(Color.YELLOW);
        radioButtonTwo.setOpaque(true);
        radioButtonTwo.setBorderPainted(false);
        radioButtonTwo.setActionCommand("0.00");
        radioButtonTwo.addActionListener(new Controller());

        // Sci not selected with attributes
        JRadioButton sciRadioButton = new JRadioButton("Sci", false);
        sciRadioButton.setBackground(Color.YELLOW);
        sciRadioButton.setOpaque(true);
        sciRadioButton.setActionCommand("Sci");
        sciRadioButton.setBorderPainted(false);
        sciRadioButton.addActionListener(new Controller());

        //add the buttons to panel
        radio.add(radioButtonOne);
        radio.add(radioButtonTwo);
        radio.add(sciRadioButton);

        //Create new button group and add all necessary buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(hexCheck);
        buttonGroup.add(radioButtonOne);
        buttonGroup.add(radioButtonTwo);
        buttonGroup.add(sciRadioButton);

        //Create new panel for buttons
        row2.add(hexButtonPanel, BorderLayout.LINE_START);
        row2.add(radio, BorderLayout.LINE_END);


        //add the top two rows together then add to the thisulator panel
        top.add(topRow, BorderLayout.NORTH);
        top.add(row2, BorderLayout.SOUTH);
        this.add(top, BorderLayout.NORTH); // added to this

        // Creating the left panel with the * and / buttons
        JButton multiply = createButton("\u002A", "\u002A", Color.BLACK, Color.CYAN, new Controller());
        JButton divide = createButton("\u2215", "\u2215", Color.BLACK, Color.CYAN, new Controller());
        multiply.setPreferredSize(new Dimension(48, 45));
        divide.setPreferredSize(new Dimension(48, 45));
        multiply.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 5, Color.black));
        divide.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 5, Color.black));
        leftArithmetic.add(multiply, BorderLayout.NORTH);
        leftArithmetic.add(divide, BorderLayout.SOUTH);
        main.add(leftArithmetic, BorderLayout.WEST);

        //Create the right panel with the + and - buttons
        JButton plus = createButton("\u002B", "\u002B", Color.BLACK, Color.CYAN, new Controller());
        JButton minus = createButton("\u2212", "\u2212", Color.BLACK, Color.CYAN, new Controller());
        plus.setPreferredSize(new Dimension(48, 45));
        minus.setPreferredSize(new Dimension(48, 45));
        plus.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
        minus.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
        rightArithmetic.add(plus, BorderLayout.NORTH);
        rightArithmetic.add(minus, BorderLayout.SOUTH);
        main.add(rightArithmetic, BorderLayout.EAST);

        // Create the Clear button
        JButton clear = createButton("C", "C", Color.BLACK, Color.RED, new Controller());
        middleArithmetic.add(clear, BorderLayout.NORTH);

        // Create the equals button
        JButton equals = createButton("=", "=", Color.BLACK, Color.YELLOW, new Controller());
        middleArithmetic.add(equals, BorderLayout.SOUTH);

        // Create the hexButtons
        char hex = 'A';
        for (int i = 0; i < 6; i++) {
            hex += i;
            hexButtons[i] = createButton(String.valueOf(hex), String.valueOf(hex), Color.BLACK, Color.BLUE, new Controller());
            numpad.add(hexButtons[i]);
        }

        // Create the number buttons
        for (int i = 1; i < 10; i++) {
            numpad.add(createButton(String.valueOf(i), String.valueOf(i), Color.BLACK, Color.BLUE, new Controller()));
        }

        numpad.add(createButton(".", ".", Color.BLACK, Color.MAGENTA, new Controller()));
        numpad.add(createButton("0", "0", Color.BLACK, Color.BLUE, new Controller()));
        numpad.add(createButton("\u00B1", "\u00B1", Color.BLACK, Color.BLUE, new Controller()));
        middleArithmetic.add(numpad, BorderLayout.CENTER);
        main.add(middleArithmetic, BorderLayout.CENTER);
        this.add(main, BorderLayout.CENTER);
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
