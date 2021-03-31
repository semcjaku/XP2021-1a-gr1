import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;

public class Tests {

    @Test
    public void Add_AddsUpToTwoNumber_WhenStringIsValid_ForEmptyString() {
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
    public void Add_AddsUpToTwoNumber_WhenStringIsValid_For1() {
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
    public void Add_AddsUpToTwoNumber_WhenStringIsValid_For12() {
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
    public void Add_AddsUpToAnyNumber_WhenStringIsValid_ForThreeNumbers() {
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
    public void Add_AddsUpToAnyNumber_WhenStringIsValid_ForFourNumber() {
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
    public void Add_AddsNumbersUsingNewLineDelimiter_WhenStringIsValid_ForOneNewLine() {
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
    public void Add_AddsNumbersUsingNewLineDelimiter_WhenStringIsValid_ForTwoNewLines() {
        //Arrange
        String calculation = "10\n90,10\n20";
        int expected = 130;
        Calculator calc = new Calculator();

        //Act
        int result = calc.Add(calculation);

        //Assets
        assertEquals(expected, result);
    }
}
