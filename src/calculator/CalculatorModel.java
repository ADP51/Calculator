/*
 * File name: CalculatorModel
 * Author: Andrew Palmer
 * Course: CST8221 - JAP 302
 * Assignment: 1
 * Date: 2019-10-15
 * Professor: Svillen Ranev
 * Purpose: The model class for the calculator object. All calculations are handled here.
 */
package calculator;

/**
 * Author: Andrew Palmer
 * Version: 1.0
 * See: calculator
 * Since: 1.8.222
 */
public class CalculatorModel {

    public final static int PRECISION_0 = 0; //precision mode .0
    public final static int PRECISION_00 = 1; // precision mode .00
    public final static int PRECISION_E = 2; // precision mode Sci notation
    public final static int FLOAT_MODE = 0;
    public final static int INT_MODE = 1;
    public final static int OPERAND1_FLAG = 0;
    public final static int OPERAND2_FLAG = 1;

    private String[] formatModes = {"%.1f", "%.2f", "%E"}; // String formats for different precision modes
    private String operand1 = ""; // the first operand
    private String operand2 = ""; // the second operand
    private String arithmeticOperation = ""; // the arithmetic operator +,-.*,/
    private int operationalMode = FLOAT_MODE; // float mode or Hex mode
    private int precisionMode = PRECISION_00; // how many decimals does the output need to be
    private boolean errorState = false; // error state
    private String errorMessage = ""; // information on the error
    private int operandFlag = 0; // flag to check what operand to assign

    /**
     * Depending on the operational mode
     * @return the result of the input equation
     */
    private String calculate(){
        if (operationalMode == INT_MODE)  {
            return calculateInteger();
        } else {
            return calculateFloat();
        }
    }

    private String calculateInteger() {
        int op1 = 0;
        int op2 = 0;

        try {
            op1 = Integer.parseInt(operand1, 16);
            op2 = Integer.parseInt(operand2, 16);
        } catch(NumberFormatException e) {
            this.errorState = true;
            this.errorMessage = "Unrecognized operand.";
            return null;
        }

        if(op1 == 0 &&  "/".equals(arithmeticOperation)) {
            this.operand1 = "";
            this.operand2 = "";
            this.errorState = true;
            this.errorMessage = "Result is undefined.";
            return null;
        }

        if(op2 == 0 && "/".equals(arithmeticOperation)) {
            this.operand1 = "";
            this.operand2 = "";
            this.errorState = true;
            this.errorMessage = "Cannot divide by zero.";
            return null;
        }

        try {
            switch (arithmeticOperation) {
                case "\u002B":
                    operand1 = Integer.toHexString(op1+op2).toUpperCase();
                    return operand1;
                case "\u2212":
                    operand1 = Integer.toHexString(op1 - op2).toUpperCase();
                    return operand1;
                case "\u002A":
                    operand1 = Integer.toHexString(op1 * op2).toUpperCase();
                    return operand1;
                case "\u2215":
                    operand1 = Integer.toHexString(op1 / op2).toUpperCase();
                    return operand1;
                default:
                    this.errorState = true;
                    this.errorMessage = "Illegal arithmetic operator.";
                    return null;
            }
        } catch (ArithmeticException e) {
            this.errorState = true;
            this.errorMessage = "Unknown error";
            return null;
        }
    }

    private String calculateFloat() {
        double op1 = 0;
        double op2 = 0;
        double result = 0;
        try {
            op1 = Double.parseDouble(operand1);
            op2 = Double.parseDouble(operand2);

        } catch(NumberFormatException e) {
            this.errorState = true;
            this.errorMessage = "Illegal operands.";
            return null;
        }

        if(op1 == 0.0 && CalculatorViewController.DIVIDE_SYMBOL.equals(arithmeticOperation)) {
            this.operand1 = "";
            this.operand2 = "";
            this.errorState = true;
            this.errorMessage = "Result is undefined.";
            return null;
        }

        if(op2 == 0.0 && CalculatorViewController.DIVIDE_SYMBOL.equals(arithmeticOperation)) {
            this.operand1 = "";
            this.operand2 = "";
            this.errorState = true;
            this.errorMessage = "Cannot divide by zero.";
            return null;
        }

        try {
            switch (arithmeticOperation) {
                case "\u002B":
                    operand1 = String.format(formatModes[precisionMode], op1 + op2);
                    return operand1;
                case "\u2212":
                    operand1 = String.format(formatModes[precisionMode], op1 - op2);
                    return operand1;
                case "\u002A":
                    operand1 = String.format(formatModes[precisionMode], op1 * op2);
                    return operand1;
                case "\u2215":
                    operand1 = String.format(formatModes[precisionMode], op1 / op2);
                    return operand1;
                default:
                    this.errorState = true;
                    this.errorMessage = "Illegal arithmetic operator.";
                    return null;
            }
        } catch (ArithmeticException e) {
            this.errorState = true;
            this.errorMessage = "Unknown error";
            return null;
        }
    }

    /**
     * Setter method for precisionMode
     * @param precisionMode the integer value precision mode
     */
    public void setPrecisionMode(int precisionMode){
        this.precisionMode = precisionMode;
    }

    /**
     * Gets the operand flag to determine what operand to set
     * @return the operandFlag
     */
    public int getOperandFlag() {
        return this.operandFlag;
    }

    /**
     * Sets the operandFlag
     * @param flagValue the new operandFlag value
     */
    public void setOperandFlag(int flagValue) {
        this.operandFlag = flagValue;
    }

    /**
     * Switch the operational mode
     */
    public void toggleMode() {
        if(operationalMode == FLOAT_MODE) {
            this.operationalMode = INT_MODE;
        } else {
            this.operationalMode = FLOAT_MODE;
        }
    }

    /**
     * Remove the last operator/operand
     */
    public void removeLast() {
        if (operandFlag == OPERAND1_FLAG) { // check if the first operand is set
            if(!operand1.isEmpty()) {
                this.operand1 = operand1.substring(0, operand1.length() - 1); // pop the last char off the string
                if ("-".equals(this.operand1)) {
                    this.operand1 = ""; // if all numbers are deleted and only a negative sign remains then reset to 0
                }
            }
        } else {
            if (operand2.isEmpty()) { // if the first operand is set but there is nothing added to the second remove the arithmetic operator
                this.arithmeticOperation = "";
            } else {
                this.operand2 = operand2.substring(0, operand2.length()-1); // pop the last char off the string
                this.operand2 = "";
            }
        }
    }

    /**
     * Clears the current operation and resets all the appropriate fields
     */
    public void clear() {
        operand1 = "";
        operand2 = "";
        arithmeticOperation = "";
        operandFlag = OPERAND1_FLAG;
    }

    /**
     * Toggles the current operand negative/positive
     */
    public void toggleSign() {
        if (operandFlag == OPERAND1_FLAG){
            if(operand1.startsWith("-")) {
                operand1 = operand1.substring(1);
            } else {
                operand1 = "-" + operand1;
            }
        } else {
            if(operand2.startsWith("-")) {
                operand2 = operand2.substring(1);
            } else {
                operand2 = "-" + operand2;
            }
        }
    }

    /**
     * A method to display zero in the proper format when required.
     * @return Formatted String of 0
     */
    public String displayZero() {
        String zeroFormatted;
        if(operationalMode == INT_MODE){
            zeroFormatted = String.format("%d", 0);
        } else {
            zeroFormatted = String.format(formatModes[precisionMode], 0.0);
        }
        return zeroFormatted; // zero value formatted
    }

    /**
     * Gets the first operand
     * @return the operand
     */
    public String getOperand1() {
        return this.operand1;
    }
    /**
     * Set the first operand
     * @param operand the operand value
     */
    public void setOperand1(String operand) {
        if("0".equals(this.operand1)){
            operand1 = "";
        }
        this.operand1 += operand;
    }

    /**
     * Gets the second operand
     * @return the operand
     */
    public String getOperand2() {
        return this.operand2;
    }

    /**
     * Set the second operand
     * @param operand the operand value
     */
    public void setOperand2(String operand) {
        if("0".equals(this.operand2)){
            operand2 = "";
        }
        this.operand2 += operand;
    }

    /**
     * Gets the arithmetic operator
     * @return the arithmetic operator
     */
    public String getArithmeticOperation() {
        return this.arithmeticOperation;
    }

    /**
     * Set the arithmetic operator
     * @param operation the arithmetic operator
     */
    public void setArithmeticOperation(String operation) {
        this.arithmeticOperation = operation;
        operandFlag = OPERAND2_FLAG;
    }

    /**
     * Set the operational mode (float/integer)
     * @param mode the integer value representing the mode 0/1
     */
    public void setOperationalMode(int mode){
        this.operationalMode = mode;
    }

    /**
     * Calls the calculate method and returns the result
     * @return the result of the mathematical operation
     */
    public String getResult() {
        return calculate();
    }

    /**
     * Returns the error message
     * @return errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Clears the operand2 field
     */
    public void clearOperand2() {
        this.operand2 = "";
    }

    public int getOperationalMode() {
        return this.operationalMode;
    }
}
