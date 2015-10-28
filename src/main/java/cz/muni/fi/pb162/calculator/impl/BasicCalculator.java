package cz.muni.fi.pb162.calculator.impl;

import cz.muni.fi.pb162.calculator.Calculator;
import cz.muni.fi.pb162.calculator.Result;

/**
 * @author: Jana Zahradnickova,  UCO 433598
 * @version: 23. 10. 2015
 */

public class BasicCalculator implements Calculator {

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
        double firstArgument;
        double secondArgument;

        if (!isValidOperation(operator))
            return new CalculationResult(UNKNOWN_OPERATION_ERROR_MSG, false);

        if (operator.equals(FAC_CMD)) {
            if (tokens.length == 2) {
                firstArgument = Double.parseDouble(tokens[1]);
            } else {
                return new CalculationResult(WRONG_ARGUMENTS_ERROR_MSG, false);
            }
            if ((firstArgument == Math.floor(firstArgument)) && (!Double.isInfinite(firstArgument))) {
                return fac((int) firstArgument);
            } else {
                return new CalculationResult(WRONG_ARGUMENTS_ERROR_MSG, false);
            }
        }

        if (tokens.length == 3) {
            firstArgument = Double.parseDouble(tokens[1]);
            secondArgument = Double.parseDouble(tokens[2]);
            switch (operator) {
                case SUM_CMD: {
                    return sum(firstArgument, secondArgument);
                }
                case SUB_CMD: {
                    return sub(firstArgument, secondArgument);
                }
                case MUL_CMD: {
                    return mul(firstArgument, secondArgument);
                }
                case DIV_CMD: {
                    if (secondArgument == 0)
                        return new CalculationResult(WRONG_ARGUMENTS_ERROR_MSG, false);
                    else
                        return div(firstArgument, secondArgument);
                }
                default:
                    return new CalculationResult(UNKNOWN_OPERATION_ERROR_MSG, false);
            }
        } else {
            return new CalculationResult(WRONG_ARGUMENTS_ERROR_MSG, false);
        }
    }

    /**
     * Computes the sum of two numbers (x + y)
     *
     * @param x first number
     * @param y second number
     * @return result
     */
    @Override
    public Result sum(double x, double y) {
        return new CalculationResult(x + y, true);
    }

    /**
     * Subtract two numbers (x - y)
     *
     * @param x first number
     * @param y second number
     * @return result
     */
    @Override
    public Result sub(double x, double y) {
        return new CalculationResult(x - y, true);
    }

    /**
     * Multiply two number (x * y(
     *
     * @param x first number
     * @param y second number
     * @return result
     */
    @Override
    public Result mul(double x, double y) {
        return new CalculationResult(x * y, true);
    }

    /**
     * Calculate the division of two numbers (x / y)
     *
     * @param x first number
     * @param y second number
     * @return result
     */
    @Override
    public Result div(double x, double y) {
        //pozor na deleni nulou
        if (y == 0)
            return new CalculationResult(COMPUTATION_ERROR_MSG, false);
        else
            return new CalculationResult(x / y, true);
    }

    /**
     * Computes the factorial of given number;
     * We guarantee that the result for all tested inputs will fit into double;
     *
     * @param x input number
     * @return result
     */
    @Override
    public Result fac(int x) {
        if (x < 0)
            return new CalculationResult(COMPUTATION_ERROR_MSG, false); //faktorial zapornych cisel nelze spocitat
        if (x == 0)
            return new CalculationResult(1.0, true);
        else
            return new CalculationResult((double) (facRecursive(x)), true);
    }

    private int facRecursive(int x) {
        if (x == 0)
            return 1;
        else
            return x * facRecursive(x - 1);
    }

    /**
     * Return if entered operation is valid (known by calculator)
     *
     * @param operator operation command/symbol
     * @return true - operation is valid (known), false - operation is not valid (not known)
     */
    private boolean isValidOperation(String operator) {
        return (operator.equals(SUM_CMD) ||
                operator.equals(SUB_CMD) ||
                operator.equals(MUL_CMD) ||
                operator.equals(DIV_CMD) ||
                operator.equals(FAC_CMD));
    }
}
