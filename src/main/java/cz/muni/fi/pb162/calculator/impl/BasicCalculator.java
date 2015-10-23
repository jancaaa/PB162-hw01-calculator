package cz.muni.fi.pb162.calculator.impl;

import cz.muni.fi.pb162.calculator.Calculator;
import cz.muni.fi.pb162.calculator.Result;

/**
 *
 * @author: Jana Zahradnickova,  UCO 433598
 * @version: 23. 10. 2015
 *
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
        //rozparsovat input
        //zjistit korektnost operace
        //provedst operaci

        char operator;
        double firstArgument;
        double secondArgument;
        return null;
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
        if (y==0)
            return COMPUTATION_ERROR_MSG;
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
        //pozor na zaporna cisla
        if (x<0)
            return COMPUTATION_ERROR_MSG;
        if (x == 0)
            return 1;
        else
            return x * fac(x - 1);
    }
}
