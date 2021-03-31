import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTests {
    @Test public void Add_AddsUpToTwoNumbers_WhenStringIsValid(){
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

    @Test public void Add_AddsUpToAnyNumber_WhenStringIsValid(){
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
}
