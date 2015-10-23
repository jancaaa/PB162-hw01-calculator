package cz.muni.fi.pb162.calculator.impl;

import cz.muni.fi.pb162.calculator.Calculator;
import cz.muni.fi.pb162.calculator.Result;

/**
 * @author: Jana Zahradnickova,  UCO 433598
 * @version: 23. 10. 2015
 */

/*
TODO: Konverze návratových typù double, String -> Result
TODO: Ošetøit, že byly zadány oba argumenty
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
        String operator;
        double firstArgument;
        double secondArgument;

        String[] tokens = input.split(" ");
        operator = tokens[0];
        firstArgument = Double.parseDouble(tokens[1]);
        if (!operator.equals(FAC_CMD))
            secondArgument = Double.parseDouble(tokens[2]); //pouze pokud argument existuje (resp. operace ho pozaduje

        switch (operator) {
            case FAC_CMD: {
                if (((firstArgument == Math.floor(firstArgument)) && !Double.isInfinite(firstArgument)) && (firstArgument >= 0))
                    return fac((int) firstArgument);
                else
                    return WRONG_ARGUMENTS_ERROR_MSG;

            }
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
                    return WRONG_ARGUMENTS_ERROR_MSG;
                else
                    return div(firstArgument, secondArgument);
            }

            default:
                return UNKNOWN_OPERATION_ERROR_MSG;
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
        return x + y;
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
        return x - y;
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
        return x * y;
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
            return COMPUTATION_ERROR_MSG;
        else
            return x / y;
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
            return COMPUTATION_ERROR_MSG; //faktorial zapornych cisel nelze spocitat
        if (x == 0)
            return 1;
        else
            return x * fac(x - 1);
    }
}
