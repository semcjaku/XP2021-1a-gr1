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
}
