package cz.muni.fi.pb162.calculator.impl;

import cz.muni.fi.pb162.calculator.Result;

/**
 * @author: Jana Zahradnickova,  UCO 433598
 * @version: 23. 10. 2015
 */

/*
DONE: Konverze návratových typù double, String -> Result
TODO: Udìlat z toho rozšíøenou verzi BasicCalculatror (Step 4) - OK?
TODO: Úprava popisu private metod
TODO: Task "Evaluate operations from textual input"
TODO: Zkontrolovat napojení metod z NumeralCoventer
TODO: Má být class abstract?
TODO: Rozšíøit eval z Basic o pøevody z/do
DONE: Pøedìlat parsování toDec a fromDec mají jiné typy parametrù
TODO: Kontrola zda jsou argumenty pro toDec a fromDec int (jsou celoèíselné, ne desetinné)
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
            if (!isValidOperation(tokens[1]))
                return new CalculationResult(COMPUTATION_ERROR_MSG, false);

            int firstArgument = Integer.parseInt(tokens[1]);
            if (firstArgument < 2 || firstArgument > 16)
                return new CalculationResult(COMPUTATION_ERROR_MSG, false);

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
        } else {
            //volat eval z BasicCalculator
            double firstArgument;
            double secondArgument;

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
        String result = "";
        while (number != 0) {
            int remainder = number % base; //zjisteni zbytku (hodnoty na odpovidajici pozici)
            number = number / base; //posun o pozici dal
            result = convertToChar(remainder) + result; //zretezit
        }
        return new CalculationResult(result, true);
    }

    /**
     * Pøevádí èíslo na znak
     *
     * @param number zadané èíslo
     * @return zadané èíslo jako znak
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
     * Pøevádí znak na èíslo
     *
     * @param character zadaný znak
     * @return èíselná hodnota znaku
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

    /**
     * Vrací zda je zdaná operace korektní (pro kalkulaèku známá)
     *
     * @param operator operace
     * @return true - operace je korektní, false - operace není korektní
     */
    private boolean isValidOperation(String operator) {
        return (operator.equals(SUM_CMD) ||
                operator.equals(SUB_CMD) ||
                operator.equals(MUL_CMD) ||
                operator.equals(DIV_CMD) ||
                operator.equals(FAC_CMD) ||
                operator.equals(TO_DEC_CMD) ||
                operator.equals(FROM_DEC_CMD));
    }

    private boolean isStringInt (String input){
        for(int i = 0; i < input.length(); i++) {
            if (input.charAt(i)== '.')
                return false;
        }
        return true;
    }
}
