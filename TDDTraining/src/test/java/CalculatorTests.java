import org.junit.Test;
import exceptions.NegativesNotAllowedException;

import static org.junit.Assert.assertEquals;


public class CalculatorTests {

    @Test
    public void add_AddsTwoNumber_WhenStringIsInvalid() throws NegativesNotAllowedException {
        Calculator calculator = new Calculator();
        String numbers = "";

        int result = calculator.add(numbers);

        assertEquals(0, result);
    }

    @Test
    public void add_AddsOneNumber_WhenStringIsValid() throws NegativesNotAllowedException {
        Calculator calculator = new Calculator();
        String numbers = "1";

        int result = calculator.add(numbers);

        assertEquals(1, result);
    }

    @Test
    public void add_AddsTwoNumber_WhenStringIsValid() throws NegativesNotAllowedException {
        Calculator calculator = new Calculator();
        String numbers = "1,2";

        int result = calculator.add(numbers);

        assertEquals(3, result);
    }

    @Test
    public void add_AddsTwoNumber_WhenStringHasWhiteSpaces() throws NegativesNotAllowedException {
        Calculator calculator = new Calculator();
        String numbers = "1, 2";

        int result = calculator.add(numbers);

        assertEquals(3, result);
    }

    @Test
    public void add_AddsManyNumber_WhenStringIsValid() throws NegativesNotAllowedException {
        Calculator calculator = new Calculator();
        String numbers = "1,2,3,4,5";

        int result = calculator.add(numbers);

        assertEquals(15, result);
    }

    @Test
    public void add_AddsManyNumber_WithNewLineDelimiter() throws NegativesNotAllowedException {
        Calculator calculator = new Calculator();
        String numbers = "1\n2,3";

        int result = calculator.add(numbers);

        assertEquals(6, result);
    }

    @Test
    public void add_AddsManyNumber_UsingCustomDelimiter() throws NegativesNotAllowedException {
        Calculator calculator = new Calculator();
        String numbers = "//;\n1;2";

        int result = calculator.add(numbers);

        assertEquals(3, result);
    }

    @Test(expected = NegativesNotAllowedException.class)
    public void add_ShouldThrowAnException_WhenNegativeNumbersAreUsed() throws NegativesNotAllowedException {
        Calculator calculator = new Calculator();
        String numbers = "1,2,-1";

        try {
            int result = calculator.add(numbers);
        } catch (NegativesNotAllowedException e) {
            assertEquals("Negatives not allowed: -1", e.getMessage());
            throw e;
        }
    }
    @Test(expected = NegativesNotAllowedException.class)
    public void add_ShouldThrowAnException_WhenNegativeNumbersAndCustomDelimiter() throws NegativesNotAllowedException {
        Calculator calculator = new Calculator();
        String numbers = "//;\n1;-2;-4";

        try {
            int result = calculator.add(numbers);
        } catch (NegativesNotAllowedException e) {
            assertEquals("Negatives not allowed: -2, -4", e.getMessage());
            throw e;
        }
    }
}
