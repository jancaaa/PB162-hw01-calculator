package cz.muni.fi.pb162.calculator.impl;

import cz.muni.fi.pb162.calculator.Result;

/**
 *
 * @author: Jana Zahradnickova,  UCO 433598
 * @version: 23. 10. 2015
 *
 */
public class CalculationResult implements Result
{
    double numericValue;
    private boolean numericValueSet = false;
    String alphanumericValue;
    boolean isSuccessful;

    @Override
    public boolean isSuccess() {
        return isSuccessful;
    }

    @Override
    public boolean isAlphanumeric() {
        return (alphanumericValue != null);
    }

    @Override
    public boolean isNumeric() {
        return (numericValueSet);
    }

    @Override
    public double getNumericValue() {
        return numericValue;
    }

    @Override
    public String getAlphanumericValue() {
        return alphanumericValue;
    }
}
