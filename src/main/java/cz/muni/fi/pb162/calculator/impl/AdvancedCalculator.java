package cz.muni.fi.pb162.calculator.impl;

import cz.muni.fi.pb162.calculator.Result;

/**
 * @author: Jana Zahradnickova,  UCO 433598
 * @version: 23. 10. 2015
 */

/*
TODO: Konverze návratových typù double, String -> Result
TODO: Udìlat z toho rozšíøenou verzi BasicCalculatror (Step 4) - OK?
TODO: Úprava popisu private metod
TODO: Task "Evaluate operations from textual input"
TODO: Zkontrolovat napojení metod z NumeralCoventer
TODO: Má být class abstract?
TODO: Rozšíøit eval z Basic o pøevody z/do
*/

public abstract class AdvancedCalculator extends BasicCalculator implements ConvertingCalculator {
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

        if (operator.equals(TO_DEC_CMD) || operator.equals(FROM_DEC_CMD)) {
            if (tokens.length < 3)
                return WRONG_ARGUMENTS_ERROR_MSG;

            firstArgument = Double.parseDouble(tokens[1]);
            secondArgument = Double.parseDouble(tokens[2]);
            if (firstArgument < 2 || firstArgument > 16)
                return COMPUTATION_ERROR_MSG;

            if (operator.equals(TO_DEC_CMD)){
                return toDec(firstArgument,secondArgument);
            }
            if (operator.equals(FROM_DEC_CMD)){
                return fromDec(firstArgument,secondArgument);
            }
        } else {
            //volat eval z BasicCalculator
        }
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
        int result = 0; //vysledek
        int position = 0; //kolikate misto odzadu pocitame (pro mocninu)
        int digit = -1;
        for (int i = number.length() - 1; i >= 0; i--) { //cislo se zpracovava odzadu
            if (base <= 10) {
                digit = Character.getNumericValue(number.charAt(i));
            } else {
                digit = convertToInt(number.charAt(i));
            }
            result += digit * Math.pow(base, position); //prevod cislice na odpovidajici pozici
            position++;// posun pozice
        }
        return result;
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
        String result = "";
        while (number != 0) {
            int remainder = number % base; //zjisteni zbytku (hodnoty na odpovidajici pozici)
            number = number / base; //posun o pozici dal
            result = convertToChar(remainder) + result; //zretezit
        }
        return result;
    }

    /**
     * Pøevádí èíslo na znak
     *
     * @param number
     * @return zadané èíslo jako znak
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
    }

    /**
     * Pøevádí znak na èíslo
     *
     * @param character
     * @return èíselná hodnota znaku
     */
    private static char convertToInt(int character) {
        switch (character) {
            case '0': {
                return 0;
            }
            case '1': {
                return 1;
            }
            case '2': {
                return 2;
            }
            case '3': {
                return 3;
            }
            case '4': {
                return 4;
            }
            case '5': {
                return 5;
            }
            case '6': {
                return 6;
            }
            case '7': {
                return 7;
            }
            case '8': {
                return 8;
            }
            case '9': {
                return 9;
            }
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
            case 'F': {
                return 15;

            }
        }
    }
}
