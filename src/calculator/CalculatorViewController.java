/*
 * File name: CalculatorViewController
 * Author: Andrew Palmer
 * Course: CST8221 - JAP 302
 * Assignment: 1
 * Date: 2019-10-15
 * Professor: Svillen Ranev
 * Purpose: The controller and view class for calculator app
 * Class List: CalculatorViewController, Controller
 */
package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Author: Andrew Palmer
 * Version: 1.0
 * See: calculator
 * Since: 1.8.222
 */
public class CalculatorViewController extends JPanel {

    public static String BACKSPACE_ARROW = "\u21DA";
    public static String MULTIPLY_SYMBOL = "\u002A";
    public static String DIVIDE_SYMBOL = "\u2215";
    public static String PLUS_SYMBOL = "\u002B";
    public static String MINUS_SYMBOL = "\u2212";

    /**
     * The top half display
     */
    private JTextField display1;

    /**
     * The bottom half of the display
     */
    private JTextField display2;

    /**
     * The error label
     */
    private JLabel error;

    /**
     * The . button on the calculator
     */
    private JButton dotButton;

    /**
     * The array if hex value buttons
     */
    private JButton[] hexButtons = new JButton[6];

    /**
     * Array of numbers for the numpad buttons
     */
    private int[] numpadNumbers = {7,8,9,4,5,6,1,2,3};

    /**
     * Constructor for CalculatorViewController creates a new panel
     * with a border layout and sets the border to a matte black border
     */
    public CalculatorViewController() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(380, 540)); //set the size of the application panel on screen
        this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
        Controller controller = new Controller();

        // panels to be added to this
        JPanel topRow = new JPanel(new BorderLayout()); // To be combined with the row2
        JPanel display = new JPanel(new BorderLayout()); //combining the two displays together add to top row
        JPanel row2 = new JPanel(new BorderLayout()); // to be combined with topRow
        JPanel top = new JPanel(new BorderLayout()); // combine the top two rows into this panel
        JPanel main = new JPanel(new BorderLayout()); // the panel that will contain all of arithmetic buttons
        JPanel leftArithmetic = new JPanel(new GridLayout(2, 1,0,3)); // multiply/divide buttons on the left of main
        JPanel rightArithmetic = new JPanel(new GridLayout(2, 1,0,3)); // +/- buttons on right side of main
        JPanel middleArithmetic = new JPanel(new BorderLayout()); // clear and numpad and equals will go inside of this
        JPanel numpad = new JPanel(new GridLayout(6, 3, 3, 3)); // the numpad for the this will go in middleArithmetic

        //Create the mode/error button will be added to the top row "EAST"
        error = new JLabel("F", JLabel.CENTER);
        error.setVerticalTextPosition(JLabel.CENTER);
        error.setPreferredSize(new Dimension(52, 55));
        error.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 5, Color.black));
        error.setOpaque(true);
        error.setBackground(Color.YELLOW);
        topRow.setBackground(Color.YELLOW); // set top row bg yellow per requirements
        topRow.add(error, BorderLayout.WEST); // add button to panel

        //add the display1 and display 2 into single panel then add to the topRow panel
        display1 = new JTextField("", 14);
        display2 = new JTextField("0.0", 14);
        display1.setPreferredSize(new Dimension(0, 30));
        display2.setPreferredSize(new Dimension(0, 30));
        display1.setBorder(BorderFactory.createEmptyBorder());
        display2.setBorder(BorderFactory.createEmptyBorder());
        display1.setHorizontalAlignment(JTextField.RIGHT);
        display2.setHorizontalAlignment(JTextField.RIGHT);
        display1.setEditable(false);
        display2.setEditable(false);
        display.add(display1, BorderLayout.NORTH);
        display.add(display2, BorderLayout.SOUTH);
        display.setOpaque(true);
        topRow.add(display, BorderLayout.CENTER);
        topRow.setBorder(BorderFactory.createMatteBorder(0,0,5,0, Color.BLACK));

        //Create the backspace button and add it to the topRow Panel
        JButton backspace = createButton("\u21DA", "\u21DA", Color.BLACK, Color.YELLOW, controller);
        backspace.setPreferredSize(new Dimension(52, 55));
        backspace.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 1, Color.black));
        backspace.setToolTipText("Backspace Alt-B");
        backspace.setMnemonic(KeyEvent.VK_B);
        backspace.setOpaque(false);
        backspace.setBorderPainted(true);
        topRow.add(backspace, BorderLayout.EAST);

        //Create the second row
        row2.setBackground(Color.BLACK);
        row2.setBorder(BorderFactory.createMatteBorder(5,1,5,1, Color.BLACK));

        // Hexdecimal check box with attributes
        JCheckBox hexCheck = new JCheckBox("Hex");
        hexCheck.setPreferredSize(new Dimension(47,0));
        hexCheck.setAlignmentX(Component.LEFT_ALIGNMENT);
        hexCheck.setBackground(Color.GREEN);
        hexCheck.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 5, Color.black));
        hexCheck.setOpaque(true);
        hexCheck.setActionCommand("Hex");
        hexCheck.addActionListener(controller);
        JPanel hexBox = new JPanel(new GridLayout(1,1));
        hexBox.add(hexCheck);
        hexBox.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.black));

        //Setup radio buttons
        JPanel radio = new JPanel(new GridLayout(1, 3));
        radio.setBorder(BorderFactory.createMatteBorder(5,0,5,0,Color.BLACK));
        JRadioButton radioButtonOne = new JRadioButton(".0", false);
        radioButtonOne.setBackground(Color.YELLOW);
        radioButtonOne.setOpaque(true);
        radioButtonOne.setActionCommand("oneDecimal");
        radioButtonOne.setBorderPainted(false);

        // Selected at Default with attributes
        JRadioButton radioButtonTwo = new JRadioButton(".00", true);
        radioButtonTwo.setBackground(Color.YELLOW);
        radioButtonTwo.setOpaque(true);
        radioButtonTwo.setBorderPainted(false);
        radioButtonTwo.setActionCommand("twoDecimal");

        // Sci not selected with attributes
        JRadioButton sciRadioButton = new JRadioButton("Sci", false);
        sciRadioButton.setBackground(Color.YELLOW);
        sciRadioButton.setOpaque(true);
        sciRadioButton.setActionCommand("Sci");
        sciRadioButton.setBorderPainted(false);

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

        //set the action listener for the radio buttons
        radioButtonOne.addActionListener(controller);
        radioButtonTwo.addActionListener(controller);
        sciRadioButton.addActionListener(controller);

        //Create new panel for buttons
        row2.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.BLACK));
        row2.add(hexBox, BorderLayout.LINE_START);
        row2.add(radio, BorderLayout.LINE_END);

        //add the top two rows together then add to the this panel
        top.add(topRow, BorderLayout.NORTH);
        top.add(row2, BorderLayout.SOUTH);
        this.add(top, BorderLayout.NORTH); // added to this

        // Creating the left panel with the * and / buttons
        JButton multiply = createButton("\u002A", "\u002A", Color.BLACK, Color.CYAN, controller);
        JButton divide = createButton("\u2215", "\u2215", Color.BLACK, Color.CYAN, controller);
        multiply.setPreferredSize(new Dimension(48, 45));
        divide.setPreferredSize(new Dimension(48, 45));
        multiply.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
        divide.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
        multiply.setOpaque(true);
        divide.setOpaque(true);
        multiply.setBorderPainted(true);
        divide.setBorderPainted(true);

        //add buttons to leftArithmetic
        leftArithmetic.add(multiply);
        leftArithmetic.add(divide);
        leftArithmetic.setBackground(Color.BLACK);
        main.add(leftArithmetic, BorderLayout.WEST);

        //Create the right panel with the + and - buttons
        JButton plus = createButton("\u002B", "\u002B", Color.BLACK, Color.CYAN, controller);
        JButton minus = createButton("\u2212", "\u2212", Color.BLACK, Color.CYAN, controller);
        plus.setPreferredSize(new Dimension(48, 45));
        minus.setPreferredSize(new Dimension(48, 45));
        plus.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
        minus.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
        plus.setOpaque(true);
        plus.setBorderPainted(true);
        minus.setOpaque(true);
        minus.setBorderPainted(true);

        //add buttons to rightArithmetic
        rightArithmetic.add(plus);
        rightArithmetic.add(minus);
        rightArithmetic.setBackground(Color.BLACK);
        main.add(rightArithmetic, BorderLayout.EAST);

        // Create the Clear button
        JButton clear = createButton("C", "clear", Color.BLACK, Color.RED, controller);
        clear.setPreferredSize(new Dimension(0,45));
        clear.setOpaque(true);
        clear.setBorderPainted(false);
        middleArithmetic.add(clear, BorderLayout.NORTH);

        // Create the equals button
        JButton equals = createButton("=", "=", Color.BLACK, Color.YELLOW, controller);
        equals.setPreferredSize(new Dimension(0,45));
        equals.setOpaque(true);
        equals.setBorderPainted(false);
        middleArithmetic.add(equals, BorderLayout.SOUTH);

        // Create the hexButtons
        char hex = 'A'; // Letter value for hex buttons
        for (int i = 0; i < hexButtons.length; i++) {
            if(i > 0) {
                hex++; // increment the hex value for the next button
            }
            hexButtons[i] = createButton(String.valueOf(hex), String.valueOf(hex), Color.BLACK, Color.BLUE, controller);
            hexButtons[i].setEnabled(false);
            hexButtons[i].setOpaque(true);
            hexButtons[i].setBorderPainted(false);
            numpad.add(hexButtons[i]);
        }

        // Create the number buttons from the numpadNumbers array
        for (int i = 0; i < numpadNumbers.length; i++) {
            JButton temp = createButton(String.valueOf(numpadNumbers[i]), String.valueOf(numpadNumbers[i]), Color.BLACK, Color.BLUE, controller);
            temp.setBorderPainted(false);
            temp.setOpaque(true);
            numpad.add(temp);
        }

        //set the dotButton
        dotButton =  createButton(".", ".", Color.BLACK, Color.MAGENTA, controller);
        dotButton.setOpaque(true);
        dotButton.setBorderPainted(false);
        numpad.add(dotButton);

        // Sets the 0 button
        JButton zeroButton = createButton("0", "0", Color.BLACK, Color.BLUE, controller);
        zeroButton.setOpaque(true);
        zeroButton.setBorderPainted(false);
        numpad.add(zeroButton);

        //Set the plus minus button
        JButton plusMinus = createButton("\u00B1", "\u00B1", Color.BLACK, Color.MAGENTA, controller);
        plusMinus.setOpaque(true);
        plusMinus.setBorderPainted(false);
        numpad.add(plusMinus);

        //set the border for numpad and set background
        numpad.setBorder(BorderFactory.createMatteBorder(1,2,1,2,Color.WHITE));
        numpad.setBackground(Color.WHITE);

        // add the numpad to the middle arithmetic then add that to main panel
        middleArithmetic.add(numpad, BorderLayout.CENTER);
        middleArithmetic.setBorder(BorderFactory.createMatteBorder(0,4,0,4,Color.BLACK));

        main.add(middleArithmetic, BorderLayout.CENTER);
        main.setBackground(Color.BLACK);
        main.setBorder(BorderFactory.createMatteBorder(5,0,0,0,Color.BLACK));
        this.add(main, BorderLayout.CENTER); // add the main panel to the class panel
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

        button.setFont(new Font("", Font.PLAIN, 20));
        button.addActionListener(handler);

        return button;
    }

    /**
     * Author: Andrew Palmer
     * Version: 1.0
     * See: calculator
     * Since: 1.8.222
     */
    private class Controller implements ActionListener {

        private CalculatorModel calculatorModel; // the calculator model for the controller
        private boolean equalState = false; // a flag for controlling behaviour after the equal button is pressed

        public Controller(){
            this.calculatorModel = new CalculatorModel(); // new model object
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            checkEqualState(e.getActionCommand());
            if (isNumeric(e.getActionCommand())) { //check to see if the action command is a operand input
                if (calculatorModel.getOperandFlag() == CalculatorModel.OPERAND1_FLAG) { // check to see which operator to set
                    calculatorModel.setOperand1(e.getActionCommand()); // set the first operand
                    display2.setText(calculatorModel.getOperand1());
                } else {
                    calculatorModel.setOperand2(e.getActionCommand()); //sets the second operand
                    display2.setText(calculatorModel.getOperand2());
                }
            } else if(isArithmeticOperator(e.getActionCommand())){ // check to see if the action was an arithmetic operator
                calculatorModel.setArithmeticOperation(e.getActionCommand()); // set the operator
                display1.setText(calculatorModel.getOperand1() + " " + calculatorModel.getArithmeticOperation());
            } else {
                switch(e.getActionCommand()){
                    case "F":
                        calculatorModel.toggleMode();
                        break;
                    case "\u21DA":
                        calculatorModel.removeLast();
                        if(calculatorModel.getOperandFlag() == CalculatorModel.OPERAND1_FLAG) {
                            if (calculatorModel.getOperand1().isEmpty() || "-".equals(calculatorModel.getOperand1())) {
                                display2.setText(calculatorModel.displayZero());
                            } else {
                                display2.setText(calculatorModel.getOperand1());
                            }
                        } else {
                            if(calculatorModel.getOperand2().isEmpty() || "-".equals(calculatorModel.getOperand2())){
                                display2.setText(calculatorModel.displayZero());
                            } else {
                                display2.setText(calculatorModel.getOperand2());
                            }
                        }
                        break;
                    case "Hex":
                        calculatorModel.clear();
                        enableHexButtons();
                        calculatorModel.setOperationalMode(CalculatorModel.INT_MODE); // set to integer calculations
                        dotButton.setEnabled(false);
                        error.setBackground(Color.GREEN);
                        error.setText("H");
                        display2.setText(calculatorModel.displayZero());
                        break;
                    case "oneDecimal":
                        calculatorModel.clear();
                        calculatorModel.setPrecisionMode(CalculatorModel.PRECISION_0);
                        calculatorModel.setOperationalMode(CalculatorModel.FLOAT_MODE);
                        disableHexButtons();
                        dotButton.setEnabled(true);
                        resetErrorLabel();
                        display2.setText(calculatorModel.displayZero());
                        break;
                    case "twoDecimal":
                        calculatorModel.clear();
                        calculatorModel.setPrecisionMode(CalculatorModel.PRECISION_00);
                        calculatorModel.setOperationalMode(CalculatorModel.FLOAT_MODE);
                        disableHexButtons();
                        dotButton.setEnabled(true);
                        resetErrorLabel();
                        display2.setText(calculatorModel.displayZero());
                        break;
                    case "Sci":
                        calculatorModel.clear();
                        calculatorModel.setPrecisionMode(CalculatorModel.PRECISION_E);
                        calculatorModel.setOperationalMode(CalculatorModel.FLOAT_MODE);
                        disableHexButtons();
                        dotButton.setEnabled(true);
                        resetErrorLabel();
                        display2.setText(calculatorModel.displayZero());
                        break;
                    case "clear":
                        display1.setText("");
                        calculatorModel.clear();
                        display2.setText(calculatorModel.displayZero());
                        break;
                    case ".":
                        if(calculatorModel.getOperandFlag() == CalculatorModel.OPERAND1_FLAG){
                            calculatorModel.setOperand1(".");
                            display2.setText(calculatorModel.getOperand1());
                        } else {
                            calculatorModel.setOperand2(".");
                            display2.setText(calculatorModel.getOperand2());
                        }
                        break;
                    case "\u00B1":
                        calculatorModel.toggleSign();
                        if(calculatorModel.getOperandFlag() == CalculatorModel.OPERAND1_FLAG){
                            if(calculatorModel.getOperand1().isEmpty()){
                                display2.setText(calculatorModel.displayZero());
                            }
                            display2.setText(calculatorModel.getOperand1());
                        } else {
                            if(calculatorModel.getOperand2().isEmpty()){
                                display2.setText(calculatorModel.displayZero());
                            }
                            display2.setText(calculatorModel.getOperand2());
                        }
                        break;
                    case "=":
                        checkForShortcut();
                        String result = calculatorModel.getResult();
                        if(result != null) {
                            display2.setText(result);
                        } else {
                            error.setBackground(Color.RED);
                            error.setText("E");
                            display2.setText(calculatorModel.getErrorMessage());
                        }
                        display1.setText("");
                        equalState = true;
                        break;
                }
            }
        }

        /**
         * A convenience method to check if the action command is a number
         * @param value the string to check
         * @return boolean value
         */
        private boolean isNumeric(String value) {
            return value.matches("-?[0-9a-fA-F]+");
        }

        /**
         * Checks to see if the given string value is one of the accepted arithmetic operators
         * @param value the value to check
         * @return boolean value
         */
        private boolean isArithmeticOperator(String value) {
            if ("\u002B".equals(value) || "\u2212".equals(value) || "\u002A".equals(value) || "\u2215".equals(value)) {
                return true;
            }
            return false;
        }

        /**
         *  Loops through the hexButtons and enables them
         */
        private void enableHexButtons() {
            for(JButton button : hexButtons) {
                button.setEnabled(true); // toggle the enabled value
            }
        }

        /**
         * Loops through the hexButtons and disables them
         */
        public void disableHexButtons() {
            for(JButton button : hexButtons) {
                button.setEnabled(false); // toggle the enabled value
            }
        }

        public void resetErrorLabel() {
            error.setBackground(Color.YELLOW);
            error.setText("F");
        }

        /**
         * Checks to see if the user has entered a shortcut and sets the second operand as the same as the first.
         */
        public void checkForShortcut() {
            if(!calculatorModel.getOperand1().isEmpty() && !calculatorModel.getArithmeticOperation().isEmpty() && calculatorModel.getOperand2().isEmpty()) {
                calculatorModel.setOperand2(calculatorModel.getOperand1());
            }
        }

        /**
         * This method will check for the next performed action after the = action was performed and will allow the user to
         * begin a new calculation without pressing the clear button.
         * @param actionEvent the ActionEvent
         */
        public void checkEqualState(String actionEvent) {
            if (this.equalState) {
                if (isNumeric(actionEvent)) { // check to see if actionEvent is numeric and clear the calculator model if it is
                    calculatorModel.clear();
                } else if(isArithmeticOperator(actionEvent)){
                    calculatorModel.clearOperand2();
                }
            }
            equalState = false;
        }

        public void errorDisable() {
            //if calculator is in hex mode disable hex buttons
            if(calculatorModel.getOperationalMode() == calculatorModel.INT_MODE) {
                for(JButton button : hexButtons) {
                    button.setEnabled(false);
                }
            }

        }
    }
}
