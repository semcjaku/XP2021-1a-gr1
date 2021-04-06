import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class BinaryCounterTests {
    @Test
    public void noOfBits1_ReturnZero_WhenEmptyString() throws Exception {
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
    public void noOfBits1_ReturnZero_WhenZero() throws Exception {
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
    public void noOfBits1_ReturnEight_When255() throws Exception {
        //Arrange
        String input = "255";
        int expected = 8;

        BinaryCounter binaryCounter = new BinaryCounter();

        //Act
        int result = binaryCounter.noOfBits1(input);

        //Assets
        assertEquals(expected, result);
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void noOfBits1_ShouldThrowAnException_WhenInputBelowRange() throws Exception {
        //Arrange
        String input = "-1";
        BinaryCounter binaryCounter = new BinaryCounter();

        exception.expect(Exception.class);
        exception.expectMessage("Number out of range 0-255: " + input);

        //Act
        binaryCounter.noOfBits1(input);
    }

    @Test
    public void noOfBits1_ShouldThrowAnException_WhenInputAboveRange() throws Exception {
        //Arrange
        String input = "256";
        BinaryCounter binaryCounter = new BinaryCounter();

        exception.expect(Exception.class);
        exception.expectMessage("Number out of range 0-255: " + input);

        //Act
        binaryCounter.noOfBits1(input);
    }

    @Test
    public void noOfBits1_ShouldGetManyNumberAsInput() throws Exception {
        //Arrange
        String input = "1;1;2;4;255";
        int expected = 12;

        BinaryCounter binaryCounter = new BinaryCounter();

        //Act
        int result = binaryCounter.noOfBits1(input);

        //Assets
        assertEquals(expected, result);
    }

    @Test
    public void noOfBits1_ShouldThrowException_WhenGetManyNumberAsInputWithIncorrect() throws Exception {
        //Arrange
        String input = "1;1;2;4;255;-3";
        BinaryCounter binaryCounter = new BinaryCounter();

        exception.expect(Exception.class);
        exception.expectMessage("Number out of range 0-255: " + input);

        //Act
        binaryCounter.noOfBits1(input);
    }

    @Test
    public void noOfBits1_ShouldGetManyNumberAsInput_WithDifferentDelimiters() throws Exception {
        //Arrange
        String input = "1;1 2;4 255";
        int expected = 12;

        BinaryCounter binaryCounter = new BinaryCounter();

        //Act
        int result = binaryCounter.noOfBits1(input);

        //Assets
        assertEquals(expected, result);
    }

    @Test
    public void noOfBits1_ShouldGetManyNumberAsInput_WithDifferentWhiteDelimiters() throws Exception {
        //Arrange
        String input = "1;1 2;4 255;1   1\n1;1  1        1      \n   \n         1";
        int expected = 19;

        BinaryCounter binaryCounter = new BinaryCounter();

        //Act
        int result = binaryCounter.noOfBits1(input);

        //Assets
        assertEquals(expected, result);
    }

    @Test
    public void noOfBits1_ShouldThrowException_WhenIncorrectDelimiter_Short() throws Exception {
        //Arrange
        String input = "1;1 ,3\n5";// 2;4 255;1,   1\n1;1  1        1      \n   \n         1";
        BinaryCounter binaryCounter = new BinaryCounter();

        exception.expect(Exception.class);
        exception.expectMessage("Incorrect delimiter in : " + input);

        //Act
        binaryCounter.noOfBits1(input);
    }

    @Test
    public void noOfBits1_ShouldThrowException_WhenIncorrectDelimiter_Long() throws Exception {
        //Arrange
        String input = "1;1 ,3\n5\n\n\n     2;4 255;1,   1\n1;1  1        1      \n   \n         1";
        BinaryCounter binaryCounter = new BinaryCounter();

        exception.expect(Exception.class);
        exception.expectMessage("Incorrect delimiter in : " + input);

        //Act
        binaryCounter.noOfBits1(input);
    }
}
