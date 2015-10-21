Homework assignment no. 1, Calculator
====================================

**Publication date:** 4. week of the semester 

**Submission deadline:** 7. week of the semester (Friday, November 6)

General information
-------------------
The goal of this assignment is to write an implementation for a program which will act as calculator and numeral system converter. Here you will find the most important information needed to succesfully complete this assignment. For more detailed information please consult the javadoc of provided interfaces.

The maximum number of points for this assignment is **70**.

- **40 points** for passing tests (attached tests do not guarantee a 100% correctenss).
- **20 points** for correct implementation (evaluated by your class teacher).
- **10 points** for clean and elegant implementation (code conventions, minimal code repetition).

In cases **when provided tests do not pass** with submited solution you can be granted no more than **40 points** (this means that you can be granted **0 points** as well)!

### Preconditions
To succesfully implement this assignemnt you need to know the following

1. What is the difference between class and object and how to work with both.
2. What are interfaces and how to use them.
3. What is inheritence and how it helps to avoid duplicities in your code.
4. How to use basic control structures such as if, while and switch.
5. Basic algorithmics and knowledge of numeral systems with base 2-16.

### Project structure
The structure of project provided as a base for your implementation should meet the following criteria.

1. Package ```cz.muni.fi.pb162.calculator``` contains classes and interfaces provided as part of the assignment.
  - **Do not modify or add any classes or subpackages into this package.**
2. Package  ```cz.muni.fi.pb162.calculator.impl``` should contain your implementation.
  - **Anything outside this package will be ignored during evaluation.**

### Names in this document
Unless fully classified name is provided, all class names are relative to  package ```cz.muni.fi.pb162.calculator``` or ```cz.muni.fi.pb162.calculator.impl``` for classes impelemnted as part of your solution.

### Compiling the project
The project can be compiled and packaged in the same way you already know 

```bash
$ mvn clean compile
```

The only differen is, that unlike with seminar project, this time checks for missing documentation and style violation will produce an error. 
You can temporarily disable this behavior whnen running this command. 

```bash
$ mvn clean compile -Dcheckstyle.fail=false
```

You can consult your seminar teacher to help you set the ```checkstyle.fail``` property in your IDE (or just google it). 


### Submitting the assignment
The procedure to submit your solution may differ based on your seminar group. However generally it should be ok to submit ```target/hw01-calculator-1.0-SNAPSHOT-sources.jar``` to the homework vault.

Step 1: Implement Result interface
---------------------------
Create an implementation of ```cz.muni.fi.pb162.calculator.Result``` named ```CalculationResult```. This class represents a result of operation performed on our calculator and can hold two different values:

1. Numeric (double) value
2. Alphanumeric (String) value

Beside this the class also contains information whether performed operation was successful. If operation failed, then alphanumeric value represents the error message.


Step 2: Implement Calculator interface
------------------------------
Create an implementation of ```cz.muni.fi.pb162.calculator.Calculator``` named ```BasicCalculator```. This class represents a basic calculator capable of these operations:

1. Sum of two numbers
2. Multiplication
3. Division
4. Subtraction
5. Factorial

The function and implementation of these should be quite straightforward based on related javadoc.

### Evaluate operations from textual input
Interface ```cz.muni.fi.pb162.calculator.Calculator``` defines a method which allows the evaluation of mentioned operations in somewhat more natural (textual) form.

```java
 /**
 * Evaluate textual input and perform computation
 * @param input input string
 * @return result
 */
Result eval(String input);
```

As demonstrated by example bellow. The method expects a single String argument (operator followed by space separated operands)

```java
calc.eval("+ 1 2") // returns result containing 3
calc.eval("/ 1 2") // returns result containing 2
calc.eval("! 4")  // returns result containing 24
```
Intefaces ```cz.muni.fi.pb162.calculator.Calculator``` defines constans for each required operator.

### Error handling
Errors may happen, not every operation will work with arbitrary value of its operands. If the operation fails (e.g. somebody attemts to divide by zero) then the operation is expected to return a ```Result``` instance with appropriate error message. Error messages are also defined as constants in ```cz.muni.fi.pb162.calculator.Calculator```:

1. ```COMPUTATION_ERROR_MSG```
  - Should be used for operation errors.
2. ```UNKNOWN_OPERATION_ERROR_MSG```
  - Should be used by ```eval``` method when unknown oprator is used.
3. ```WRONG_ARGUMENTS_ERROR_MSG```
  - Should be used by ```eval``` method when operator is followed by insufficient number of arguments.


Step 3: Create ConvertingCalculator interface
-------------------------------------
Create new interface named ```ConvertingCalculator``` which will combine the API of both ```cz.muni.fi.pb162.calculator.Calculator``` and ```cz.muni.fi.pb162.calculator.NumeralConverter```.

Step 4: Implement ConvertingCalculator interface
----------------------------------------
Create an implementation of ```cz.muni.fi.pb162.calculator.impl.ConvertingCalculator``` named ```AdvancedCalculator```. This class represents a calculator with the same operations as ```BasicCalculator```. You are required to avoid unnecessary code duplicities.


### Numeral system conversions
In addition, ```AdvancedCalculator``` can also convert numbers in variety of numeral systems (base 2-16) to decimal system and the other way around. ```NumeralConverter``` defines the following methods:

```java
/**
 * Convert a number in arbitrary numeral system (up to base 16) to decimal
 * @param base base of source numeral system (e.g. 2 for binary)
 * @param number number in source numeral system
 * @return result with numeric value set
 */
Result toDec(int base, String number);

/**
 * Convert a number from decimal system to any other numeral system (up to base 16)
 * @param base base of target numeral system (e.g. 2 for binary)
 * @param number number in decimal numeral system
 * @return result with alphanumeric value set
 */
Result fromDec(int base, int number);
```
The purpose of these methods should be clean from their javadoc, there is few other things which are worth pointing out though:

1. String is used due to characters usually used as letters (A-F)
2. You are required to implement these algorithms yourself.
  - This means that the use of any part of java language or library which allows the conversion is not allowed.

Interface ```cz.muni.fi.pb162.calculator.NumeralConverter``` defines constant ```DIGITS``` which is a string of all digit characters used by numeral systems with bases up to 16. Even though you are not required to use this constant, you might find it useful.

### Evaluate operations from textual input
Also  ```AdvancedCalulator``` should allow the evaluation of operations by calling the ```eval(String input);``` method. However do not forget that this class defines few methods which are not present in ```BasicCalculator```. Once again, you should try to avoid code repetition.

Operator constants for these can be found in ```cz.muni.fi.pb162.calculator.NumeralConverter```.