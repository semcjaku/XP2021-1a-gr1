import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class Tests {

    @Test
    public void Add_AddsUpToTwoNumber_WhenStringIsValid_ForEmptyString() throws Exception {
        //Arrange
        String calculation = "";
        int expected = 0;
        Calculator calc = new Calculator();

        //Act
        int result = calc.Add(calculation);

        //Assets
        assertEquals(expected, result);
    }

    @Test
    public void Add_AddsUpToTwoNumber_WhenStringIsValid_For1() throws Exception {
        //Arrange
        String calculation = "1";
        int expected = 1;
        Calculator calc = new Calculator();

        //Act
        int result = calc.Add(calculation);

        //Assets
        assertEquals(expected, result);
    }

    @Test
    public void Add_AddsUpToTwoNumber_WhenStringIsValid_For12() throws Exception {
        //Arrange
        String calculation = "1,2";
        int expected = 3;
        Calculator calc = new Calculator();

        //Act
        int result = calc.Add(calculation);

        //Assets
        assertEquals(expected, result);
    }

    @Test
    public void Add_AddsUpToAnyNumber_WhenStringIsValid_ForThreeNumbers() throws Exception {
        //Arrange
        String calculation = "1,2,3";
        int expected = 6;
        Calculator calc = new Calculator();

        //Act
        int result = calc.Add(calculation);

        //Assets
        assertEquals(expected, result);
    }

    @Test
    public void Add_AddsUpToAnyNumber_WhenStringIsValid_ForFourNumber() throws Exception {
        //Arrange
        String calculation = "10,90,10,20";
        int expected = 130;
        Calculator calc = new Calculator();

        //Act
        int result = calc.Add(calculation);

        //Assets
        assertEquals(expected, result);
    }

    @Test
    public void Add_AddsNumbersUsingNewLineDelimiter_WhenStringIsValid_ForOneNewLine() throws Exception {
        //Arrange
        String calculation = "10\n90,10,20";
        int expected = 130;
        Calculator calc = new Calculator();

        //Act
        int result = calc.Add(calculation);

        //Assets
        assertEquals(expected, result);
    }

    @Test
    public void Add_AddsNumbersUsingNewLineDelimiter_WhenStringIsValid_ForTwoNewLines() throws Exception {
        //Arrange
        String calculation = "10\n90,10\n20";
        int expected = 130;
        Calculator calc = new Calculator();

        //Act
        int result = calc.Add(calculation);

        //Assets
        assertEquals(expected, result);
    }

    @Test
    public void Add_AddsNumbersUsingCustomDelimiter_WhenStringIsValid() throws Exception {
        //Arrange
        String calculation = "//;\n1;2";
        int expected = 3;
        Calculator calc = new Calculator();

        //Act
        int result = calc.Add(calculation);

        //Assets
        assertEquals(expected, result);
    }

    @Test
    public void Add_AddsNumbersUsingCustomDelimiter_WhenStringIsValid_ForBigInput() throws Exception {
        //Arrange
        String calculation = "//;\n1;2;1\n3,4,5";
        int expected = 16;
        Calculator calc = new Calculator();

        //Act
        int result = calc.Add(calculation);

        //Assets
        assertEquals(expected, result);
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void Add_ShouldThrowAnException_WhenNegativeNumbersAreUsed_ForOneNegativeNumber() throws Exception {
        //Arrange
        String calculation = "1,2,-1";
        String negativeNumbers = "-1";
        Calculator calc = new Calculator();

        exception.expect(Exception.class);
        exception.expectMessage("Negatives not allowed: " + negativeNumbers);
        //Act
        calc.Add(calculation);
    }

    @Test
    public void Add_ShouldThrowAnException_WhenNegativeNumbersAreUsed_ForThreeNegativeNumber() throws Exception {
        //Arrange
        String calculation = "1,2,-1\n-5,-123";
        String negativeNumbers = "-1,-5,-123";
        Calculator calc = new Calculator();

        exception.expect(Exception.class);
        exception.expectMessage("Negatives not allowed: " + negativeNumbers);
        //Act
        calc.Add(calculation);
    }
}