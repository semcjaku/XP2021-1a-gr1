import org.junit.Test;
import exceptions.NumberExceedsBoundariesException;
import exceptions.InvalidFormatException;

import static org.junit.Assert.assertEquals;

public class BitCounterTests {

    @Test
    public void noOfBits1_CheckIfReturnsProperlyForOneNumber()
            throws NumberExceedsBoundariesException, InvalidFormatException {
        BitCounter bitCounter = new BitCounter();
        String number = "241"; // 11110001

        int result = bitCounter.noOfBits1(number);

        assertEquals(5, result);
    }

    @Test
    public void noOfBits1_CheckIfNumberIsValid()
            throws NumberExceedsBoundariesException, InvalidFormatException {
        BitCounter bitCounter = new BitCounter();
        String number = "";

        int result = bitCounter.noOfBits1(number);

        assertEquals(0, result);
    }


    @Test(expected = NumberExceedsBoundariesException.class)
    public void noOfBits1_CheckIfGivenNumbersDoesntExceedBoundaries()
            throws NumberExceedsBoundariesException, InvalidFormatException {
        BitCounter bitCounter = new BitCounter();
        String number = "1224";

        try {
            int result = bitCounter.noOfBits1(number);
        } catch (NumberExceedsBoundariesException e) {
            assertEquals("Number " + number + " exceeds one of 8 bit number boundaries!", e.getMessage());
            throw e;
        }
    }

    @Test
    public void noOfBits1_CheckIfReturnsProperlyForManyNumbers()
            throws NumberExceedsBoundariesException, InvalidFormatException {
        BitCounter bitCounter = new BitCounter();
        String number = "241;3;4;7"; // 11110001, 11, 100, 111 -> 11

        int result = bitCounter.noOfBits1(number);

        assertEquals(11, result);
    }

    @Test
    public void noOfBits1_CheckIfCountsWithWhitespaceAsDelimiter()
            throws NumberExceedsBoundariesException, InvalidFormatException {
        BitCounter bitCounter = new BitCounter();
        String number = "241;3;4 7"; // 11110001, 11, 100, 111 -> 11

        int result = bitCounter.noOfBits1(number);

        assertEquals(11, result);
    }

    @Test
    public void noOfBits1_CheckIfCountsWithAnyWhitespaceAsDelimiter()
            throws NumberExceedsBoundariesException, InvalidFormatException {
        BitCounter bitCounter = new BitCounter();
        String number = "241\t\t\t\t\t3         4\n7"; // 11110001, 11, 100, 111 -> 11

        int result = bitCounter.noOfBits1(number);

        assertEquals(11, result);
    }

    @Test(expected = InvalidFormatException.class)
    public void noOfBits1_CheckIfFormatisValid()
            throws NumberExceedsBoundariesException, InvalidFormatException {
        BitCounter bitCounter = new BitCounter();
        String number = "241--3?4'7";

        try {
            int result = bitCounter.noOfBits1(number);
        } catch (InvalidFormatException e) {
            assertEquals("The format of given string is invalid!", e.getMessage());
            throw e;
        }
    }

    @Test
    public void noOfBits1_CheckIfCountsProperlyWithHexAndDecNumbersMixed()
            throws NumberExceedsBoundariesException, InvalidFormatException {
        BitCounter bitCounter = new BitCounter();
        String number = "4;$f"; // 100, 1111 -> 5

        int result = bitCounter.noOfBits1(number);

        assertEquals(5, result);
    }
}
