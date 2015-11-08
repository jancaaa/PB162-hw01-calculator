package cz.muni.fi.pb162.calculator.impl;

import cz.muni.fi.pb162.calculator.Result;

/**
 * @author: Jana Zahradnickova,  UCO 433598
 * @version: 23. 10. 2015
 */
public class AdvancedCalculator extends BasicCalculator implements ConvertingCalculator {
    /**
     * Evaluate textual input and perform computation
     *
     * @param input input string
     * @return result
     */
    @Override
    public Result eval(String input) {
        String[] tokens = input.split(" ");
        String operator = tokens[0];

        if (!isValidOperation(operator))
            return new CalculationResult(UNKNOWN_OPERATION_ERROR_MSG, false);

        if (operator.equals(TO_DEC_CMD) || operator.equals(FROM_DEC_CMD)) {
            if (tokens.length != 3)
                return new CalculationResult(WRONG_ARGUMENTS_ERROR_MSG, false);
            if (!isStringInt(tokens[1]))
                return new CalculationResult(WRONG_ARGUMENTS_ERROR_MSG, false);

            int firstArgument = Integer.parseInt(tokens[1]);

            if (operator.equals(TO_DEC_CMD)) {
                String secondArgument = tokens[2];
                return toDec(firstArgument, secondArgument);
            }
            if (operator.equals(FROM_DEC_CMD)) {

                if (!isStringInt(tokens[2]))
                    return new CalculationResult(COMPUTATION_ERROR_MSG, false);

                int secondArgument = Integer.parseInt(tokens[2]);
                return fromDec(firstArgument, secondArgument);
            }
        }
        return super.eval(input); //calls eval from BasicCalculator
    }

    /**
     * Convert a number in arbitrary numeral system (up to base 16) to decimal
     *
     * @param base   base of source numeral system (e.g. 2 for binary)
     * @param number number in source numeral system
     * @return result with numeric value set
     */
    @Override
    public Result toDec(int base, String number) {
        if (base < 2 || base > 16)
            return new CalculationResult(COMPUTATION_ERROR_MSG, false);
        if (!isStingValidNumber(base, number))
            return new CalculationResult(COMPUTATION_ERROR_MSG, false);

        int result = 0;
        int position = 0; //power base (char order from the end)
        int digit;
        for (int i = number.length() - 1; i >= 0; i--) { //processing from the end
            if (base <= 10)
                digit = Character.getNumericValue(number.charAt(i));
            else
                digit = convertToInt(number.charAt(i));

            result += digit * Math.pow(base, position); //conversion to right position
            position++;
        }
        return new CalculationResult(result, true);
    }

    /**
     * Convert a number from decimal system to any other numeral system (up to base 16)
     *
     * @param base   base of target numeral system (e.g. 2 for binary)
     * @param number number in decimal numeral system
     * @return result with alphanumeric value set
     */
    @Override
    public Result fromDec(int base, int number) {
        if (base < 2 || base > 16)
            return new CalculationResult(COMPUTATION_ERROR_MSG, false);
        String result = "";
        while (number != 0) {
            int remainder = number % base;
            number = number / base;
            result = convertToChar(remainder) + result; //concatenation
        }
        return new CalculationResult(result, true);
    }

    /**
     * Return if entered operation is valid (known by calculator)
     *
     * @param operator operation command/symbol
     * @return true - operation is valid (known), false - operation is not valid (not known)
     */
    private boolean isValidOperation(String operator) {
        return operator.equals(SUM_CMD) ||
                operator.equals(SUB_CMD) ||
                operator.equals(MUL_CMD) ||
                operator.equals(DIV_CMD) ||
                operator.equals(FAC_CMD) ||
                operator.equals(TO_DEC_CMD) ||
                operator.equals(FROM_DEC_CMD);
    }

    /**
     * Return if entered string is integer
     *
     * @param input entered string
     * @return true - entered string is integer, false - entered string is not integer
     */
    private boolean isStringInt(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i)))
                return false;
        }
        return true;
    }

    /**
     * Return if entered string is valid number (contains only allowed characters)
     *
     * @param base   base of numeral system
     * @param number number in entered base system
     * @return true - is valid, false - is not valid
     */
    private boolean isStingValidNumber(int base, String number) {
        if (base <= 10) {
            for (int i = 0; i < number.length(); i++) {
                if (!Character.isDigit(number.charAt(i)))
                    return false;
                else if (Character.getNumericValue(number.charAt(i)) >= base) {
                    return false;
                }
            }
        } else {
            int charValue;
            int offset;
            for (int i = 0; i < number.length(); i++) {
                if (!Character.isDigit(number.charAt(i))) {
                    charValue = Character.getNumericValue(number.charAt(i));
                    offset = charValue - Character.getNumericValue('A');
                    if (offset >= base - 10)
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * Convert char to numeric value
     *
     * @param character character to convert
     * @return numeric value of character
     */
    private static int convertToInt(char character) {
        switch (character) {
            case 'A': {
                return 10;
            }
            case 'B': {
                return 11;
            }
            case 'C': {
                return 12;
            }
            case 'D': {
                return 13;
            }
            case 'E': {
                return 14;
            }
            case 'F':
                return 15;

            default:
                return Character.getNumericValue(character);
        }
    }

    /**
     * Convert digit to char
     *
     * @param number digit to convert
     * @return entered number as char
     */
    private static char convertToChar(int number) {
        switch (number) {
            case 0: {
                return '0';
            }
            case 1: {
                return '1';
            }
            case 2: {
                return '2';
            }
            case 3: {
                return '3';
            }
            case 4: {
                return '4';
            }
            case 5: {
                return '5';
            }
            case 6: {
                return '6';
            }
            case 7: {
                return '7';
            }
            case 8: {
                return '8';
            }
            case 9: {
                return '9';
            }
            case 10: {
                return 'A';
            }
            case 11: {
                return 'B';
            }
            case 12: {
                return 'C';
            }
            case 13: {
                return 'D';
            }
            case 14: {
                return 'E';
            }
            case 15: {
                return 'F';
            }
        }
        return 'X'; //error
    }
}
