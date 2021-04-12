package xp.stringCalculatorTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import xp.StringCalculator;

import static org.junit.Assert.*;

public class StringCalculatorNOfBits1Tests {
    private StringCalculator calculator;

    @Before
    public void setup() {
        calculator = new StringCalculator();
    }

    @Test
    public void noOfBits1_countsBits_onProperNumberProvided() {
        String input = "7";

        int result = calculator.noOfBits1(input);

        assertEquals(3, result);
    }

    @Test
    public void noOfBits1_countsBits_onAnotherProperNumberProvided() {
        String input = "128";

        int result = calculator.noOfBits1(input);

        assertEquals(1, result);
    }

    @Test
    public void noOfBits1_returnsZero_onEmptyStringProvided() {
        String input = "";

        int result = calculator.noOfBits1(input);

        assertEquals(0, result);
    }

    @Test
    public void nOfBits1_throwsException_onTooBigNumberProvided() {
        String input = "256";

        ThrowingRunnable attempt = () -> calculator.noOfBits1(input);

        assertThrows(IllegalArgumentException.class, attempt);
    }

    @Test
    public void nOfBits1_throwsException_onTooLittleNumberProvided() {
        String input = "-1";

        ThrowingRunnable attempt = () -> calculator.noOfBits1(input);

        assertThrows(IllegalArgumentException.class, attempt);
    }

    @Test
    public void noOfBits1_countsBits_onManyNumberProvided() {
        String input = "7;4;67";

        int result = calculator.noOfBits1(input);

        assertEquals(3+1+3, result);
    }

    @Test
    public void noOfBits1_countsBits_onSpaceUsedAsDelimiter() {
        String input = "7;4 67 4;9";

        int result = calculator.noOfBits1(input);

        assertEquals(3+1+3+1+2, result);
    }

    @Test
    public void noOfBits1_countsBits_onMultipleWhiteCharactersUsedAsDelimiter() {
        String input = "7;4 67  4;9\n5\t \n128";

        int result = calculator.noOfBits1(input);

        assertEquals(3+1+3+1+2+2+1, result);
    }

    @Test
    public void noOfBits1_throwsException_onIncorrectDelimiterUsed() {
        String input = "2;6 7,8";

        ThrowingRunnable attempt = () -> calculator.noOfBits1(input);

        assertThrows(IllegalArgumentException.class, attempt);
    }

    @Test
    public void noOfBits1_countsBits_onHexNumbersProvided() {
        String input = "7;$ff;5;$11";

        int result = calculator.noOfBits1(input);

        assertEquals(3+8+2+2, result);
    }
}