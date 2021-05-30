import exceptions.NegativeNumbersNotAllowedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class CalculatorTests {
    @Test
    public void Add_AddsUpToTwoNumbers_WhenStringIsValid() throws NegativeNumbersNotAllowedException {
        // Arrange
        String calculation1 = "";
        String calculation2 = "1";
        String calculation3 ="1,2";

        int expected1 = 0;
        int expected2 = 1;
        int expected3 = 3;

        Calculator sut = new Calculator();

        // Act
        int result1 = sut.Add(calculation1);
        int result2 = sut.Add(calculation2);
        int result3 = sut.Add(calculation3);

        // Assert
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
    }

    @Test
    public void Add_AddsUpToAnyNumber_WhenStringIsValid() throws NegativeNumbersNotAllowedException {
        // Arrange
        String calculation1 = "1,2,3";
        String calculation2 = "10,90,10,20";

        int expected1 = 6;
        int expected2 = 130;

        Calculator sut = new Calculator();

        // Act
        int result1 = sut.Add(calculation1);
        int result2 = sut.Add(calculation2);

        // Assert
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
    }

    @Test
    public void Add_AddsNumbersUsingNewLineDelimiter_WhenStringIsValid() throws NegativeNumbersNotAllowedException {
        // Arrange
        String calculation1 = "1\n2,3";
        String calculation2 = "10\n90,10\n20";

        int expected1 = 6;
        int expected2 = 130;

        Calculator sut = new Calculator();

        // Act
        int result1 = sut.Add(calculation1);
        int result2 = sut.Add(calculation2);

        // Assert
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
    }

    @Test
    public void Add_AddsNumbersUsingCustomDelimiter_WhenStringIsValid() throws NegativeNumbersNotAllowedException {
        // Arrange
        String calculation1 = "//;\n1;2";
        String calculation2 = "//;\n1;2;4";

        int expected1 = 3;
        int expected2 = 7;

        Calculator sut = new Calculator();

        // Act
        int result1 = sut.Add(calculation1);
        int result2 = sut.Add(calculation2);

        // Assert
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
    }

    @Test
    public void Add_AddsNumbersUsingCustomLengthDelimiter_WhenStringIsValid() throws NegativeNumbersNotAllowedException {
        // Arrange
        String calculation1 = "//***\n1***2";
        String calculation2 = "//***\n1***2***4";

        int expected1 = 3;
        int expected2 = 7;

        Calculator sut = new Calculator();

        // Act
        int result1 = sut.Add(calculation1);
        int result2 = sut.Add(calculation2);

        // Assert
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
    }

    @Test
    public void Add_IgnoresNumbersBiggerThan1000_WhenStringIsValid() throws NegativeNumbersNotAllowedException {
        // Arrange
        String calculation1 = "1\n2077,3";
        String calculation2 = "10\n90,1200\n20";

        int expected1 = 4;
        int expected2 = 120;

        Calculator sut = new Calculator();

        // Act
        int result1 = sut.Add(calculation1);
        int result2 = sut.Add(calculation2);

        // Assert
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void Add_ShouldThrowAnException_WhenNegativeNumbersAreUsedSimple() throws NegativeNumbersNotAllowedException {
        // Arrange
        String calculation = "1,2,-1";

        String expected = "-1";

        Calculator sut = new Calculator();

        // Expect
        exceptionRule.expect(NegativeNumbersNotAllowedException.class);
        exceptionRule.expectMessage("Negatives not allowed: " + expected);

        // Act
        sut.Add(calculation);
    }

    @Test
    public void Add_ShouldThrowAnException_WhenNegativeNumbersAreUsedComplex() throws NegativeNumbersNotAllowedException {
        // Arrange
        String calculation = "//;\n1;-2;-4";

        String expected = "-2,-4";

        Calculator sut = new Calculator();

        // Expect
        exceptionRule.expect(NegativeNumbersNotAllowedException.class);
        exceptionRule.expectMessage("Negatives not allowed: " + expected);

        // Act
        sut.Add(calculation);
    }
}
