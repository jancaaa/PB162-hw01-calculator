package cz.muni.fi.pb162.calculator.impl;

import cz.muni.fi.pb162.calculator.Result;

/**
 * @author: Jana Zahradnickova,  UCO 433598
 * @version: 23. 10. 2015
 */

/*
TODO: Konverze n�vratov�ch typ� double, String -> Result
TODO: Ud�lat z toho roz���enou verzi BasicCalculatror (Step 4) - OK?
TODO: �prava popisu private metod
TODO: Task "Evaluate operations from textual input"
TODO: Zkontrolovat napojen� metod z NumeralCoventer
TODO: M� b�t class abstract?
TODO: Roz���it eval z Basic o p�evody z/do
TODO: P�ed�lat parsov�n� toDec a fromDec maj� jin� typy parametr�
TODO: Kontrola zda jsou argumenty pro toDec a fromDec int (jsou celo��seln�, ne desetinn�)
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

        if (operator.equals(TO_DEC_CMD) || operator.equals(FROM_DEC_CMD)) {
            if (tokens.length < 3)
                return new CalculationResult(WRONG_ARGUMENTS_ERROR_MSG,false);

            int firstArgument = Integer.parseInt(tokens[1]);
            if (firstArgument < 2 || firstArgument > 16 )
                return new CalculationResult(COMPUTATION_ERROR_MSG,false);

            if (operator.equals(TO_DEC_CMD)){
                String secondArgument = tokens[2];
                return toDec(firstArgument,secondArgument);
            }
            if (operator.equals(FROM_DEC_CMD)){
                int secondArgument = Integer.parseInt(tokens[2]);
                return fromDec(firstArgument,secondArgument);
            }
        } else {
            //volat eval z BasicCalculator
        }
        return null;
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
        int digit;
        for (int i = number.length() - 1; i >= 0; i--) { //cislo se zpracovava odzadu
            if (base <= 10) {
                digit = Character.getNumericValue(number.charAt(i));
            } else {
                digit = convertToInt(number.charAt(i));
            }
            result += digit * Math.pow(base, position); //prevod cislice na odpovidajici pozici
            position++;// posun pozice
        }
        return new CalculationResult(result,true);
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
        return new CalculationResult(result,true);
    }

    /**
     * P�ev�d� ��slo na znak
     *
     * @param number zadan� ��slo
     * @return zadan� ��slo jako znak
     */
    private static char convertToChar(int number) {
        char output;
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
        return 'X';
    }

    /**
     * P�ev�d� znak na ��slo
     *
     * @param character zadan� znak
     * @return ��seln� hodnota znaku
     */
    private static int convertToInt(char character) {
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
        return -1;
    }

}
