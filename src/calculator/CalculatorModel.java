package calculator;

import java.text.NumberFormat;

/**
 *
 */
public class CalculatorModel {
    private String[] formatModes = {"%.1f", "%.2f"};
    private String operand1;
    private String operand2;
    private String arithmeticOperation;
    private int operationalMode;
    private int precisionMode;
    private boolean errorState;
    private String errorMessage = null;

    public void setOperand1(String operand) {
        this.operand1 = operand;
    }

    public void setOperand2(String operand) {
        this.operand2 = operand;
    }

    public void setArithmeticOperation(String operation) {
        this.arithmeticOperation = operation;
    }

    public void setOperationalMode(int mode){
        this.operationalMode = mode;
    }

    public String getResult() {
        return calculate();
    }

    private String calculate(){
        if (operationalMode == 1)  {
            return calculateInteger();
        } else {
            return calculateFloat();
        }
    }

    private String calculateInteger() {
        int op1 = 0;
        int op2 = 0;

        try {
            op1 = Integer.parseInt(operand1);
            op2 = Integer.parseInt(operand2);
        } catch(NumberFormatException e) {
            errorState = true;
            return null;
        }

        if(op1 == 0 && op2 == 0 && "/".equals(arithmeticOperation)) {
            errorState = true;
            errorMessage = "Result is undefined.";
            return null;
        }

        if(op2 == 0 && "/".equals(arithmeticOperation)) {
            errorState = true;
            errorMessage = "Cannot divide by zero.";
            return null;
        }

        switch (arithmeticOperation) {
            case "+":
                return String.format("%d", op1 + op2);
            case "-":
                return String.format("%d", op1 - op2);
            case "*":
                return String.format("%d", op1 * op2);
            case "/":
                return String.format("%d", op1 / op2);
            default:
                return "error";
        }
    }

    private String calculateFloat() {
        double op1 = 0;
        double op2 = 0;

        try {
            op1 = Double.parseDouble(operand1);
            op2 = Double.parseDouble(operand2);

        } catch(NumberFormatException e) {
            errorState = true;
            errorMessage = "Illegal operands.";
            return null;
        }

        if(op1 == 0.0 && op2 == 0.0 && "/".equals(arithmeticOperation)) {
            errorState = true;
            errorMessage = "Result is undefined.";
            return null;
        }

        if(op2 == 0.0 && "/".equals(arithmeticOperation)) {
            errorState = true;
            errorMessage = "Cannot divide by zero.";
            return null;
        }

        switch (arithmeticOperation) {
            case "+":
                return String.format(formatModes[precisionMode], op1 + op2);
            case "-":
                return String.format(formatModes[precisionMode], op1 - op2);
            case "*":
                return String.format(formatModes[precisionMode], op1 * op2);
            case "/":
                return String.format(formatModes[precisionMode], op1 / op2);
            default:
                errorState = true;
                errorMessage = "Illegal arithmetic operator.";
                return null;
        }
    }



}
