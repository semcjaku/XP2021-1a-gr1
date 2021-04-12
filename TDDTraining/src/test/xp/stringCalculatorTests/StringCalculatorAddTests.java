package xp.stringCalculatorTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import xp.StringCalculator;


import static org.junit.Assert.*;

public class StringCalculatorAddTests {
    private StringCalculator calculator;

    @Before
    public void setup() {
        calculator = new StringCalculator();
    }

    @Test
    public void add_numbersAreAdded_whenTwoProperNumbersAreGiven() {
        String input = "1, 2";

        int result = calculator.Add(input);

        assertEquals(3, result);
    }

    @Test
    public void add_numberIsReturrned_whenOneProperNumberIsGiven() {
        String input = "1";

        int result = calculator.Add(input);

        assertEquals(1, result);
    }

    @Test
    public void add_zeroIsReturned_whenEmptyStringIsGiven() {
        String input = "";

        int result = calculator.Add(input);

        assertEquals(0, result);
    }

    @Test
    public void add_numbersAreAdded_whenManyProperNumbersAreGiven() {
        String input = "1, 2, 5, 7, 15";

        int result = calculator.Add(input);

        assertEquals(30, result);
    }

    @Test
    public void add_numbersAreAdded_whenOnlyNewLinesAreUsedAsDelimiter() {
        String input = "1\n2\n5";

        int result = calculator.Add(input);

        assertEquals(8, result);
    }

    @Test
    public void add_numbersAreAdded_whenMixedDelimitersAreUsed() {
        String input = "1\n2,5\n7";

        int result = calculator.Add(input);

        assertEquals(15, result);
    }

    @Test
    public void add_numbersAreAdded_whenCustomDelimiterIsUsed() {
        String input = "//#\n1#5#4";

        int result = calculator.Add(input);

        assertEquals(10, result);
    }

    @Test
    public void add_exceptionIsThrown_whenNegativeNumbersAreGiven() {
        String input = "2,3,-4,5";

        ThrowingRunnable adding = () -> { calculator.Add(input); };

        assertThrows(IllegalArgumentException.class, adding);
    }

    @Test
    public void add_exceptionMessageContainsAllNegativeNumbers_whenNegativeNumbersAreGiven() {
        String input = "2,3,-4,5,-7,-2";
        String message = "";

        try {
            calculator.Add(input);
        }
        catch (IllegalArgumentException exception) {
            message = exception.getMessage();
        }

        assertTrue(message.contains("-4"));
        assertTrue(message.contains("-7"));
        assertTrue(message.contains("-2"));
    }
}
