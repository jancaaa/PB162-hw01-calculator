package cz.muni.fi.pb162.calculator.impl;

import cz.muni.fi.pb162.calculator.Result;

/**
 * @author: Jana Zahradnickova,  UCO 433598
 * @version: 23. 10. 2015
 */
public class CalculationResult implements Result {
    private double numericValue;
    private boolean numericValueSet = false;
    private String alphanumericValue;
    private boolean isSuccessful;

    public CalculationResult(double numericValue, boolean isSuccessful) {
        this.numericValue = numericValue;
        this.numericValueSet = true;
        this.isSuccessful = isSuccessful;
    }

    public CalculationResult(String alphanumericValue, boolean isSuccessful) {
        this.alphanumericValue = alphanumericValue;
        this.isSuccessful = isSuccessful;
    }

    /**
     * Method used to determine whether result is successful
     *
     * @return true if this result contains value
     */
    @Override
    public boolean isSuccess() {
        return isSuccessful;
    }

    /**
     * Method used to determine whether result contains alphanumeric value
     *
     * @return true if this result has alphanumeric value
     */
    @Override
    public boolean isAlphanumeric() {
        return (alphanumericValue != null);
    }

    /**
     * Method used to determine whether result contains numeric value
     *
     * @return true if this result has numeric value
     */
    @Override
    public boolean isNumeric() {
        return (numericValueSet);
    }

    /**
     * If result contains numeric value, this method should be used to retrieve it
     *
     * @return value if successful and numeric,  Double.NaN otherwise;
     */
    @Override
    public double getNumericValue() {
        if (numericValueSet)
            return numericValue;
        else
            return Double.NaN;
    }

    /**
     * If result contains alphanumeric value or error message, this method should be used to retrieve it
     *
     * @return value if successful and alphanumeric, error message if not successful, null otherwise;
     */
    @Override
    public String getAlphanumericValue() {
        return alphanumericValue;
    }
}
