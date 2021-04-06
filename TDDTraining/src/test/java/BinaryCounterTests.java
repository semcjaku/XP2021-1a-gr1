import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryCounterTests {
    @Test
    public void noOfBits1_ReturnZero_WhenEmptyString() {
        //Arrange
        String input = "";
        int expected = 0;

        BinaryCounter binaryCounter = new BinaryCounter();

        //Act
        int result = binaryCounter.noOfBits1(input);

        //Assets
        assertEquals(expected, result);
    }

    @Test
    public void noOfBits1_ReturnZero_WhenZero() {
        //Arrange
        String input = "0";
        int expected = 0;

        BinaryCounter binaryCounter = new BinaryCounter();

        //Act
        int result = binaryCounter.noOfBits1(input);

        //Assets
        assertEquals(expected, result);
    }

    @Test
    public void noOfBits1_ReturnEight_When255() {
        //Arrange
        String input = "255";
        int expected = 8;

        BinaryCounter binaryCounter = new BinaryCounter();

        //Act
        int result = binaryCounter.noOfBits1(input);

        //Assets
        assertEquals(expected, result);
    }
}
