import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class BitsCounterTests {
    @Test
    public void noOfBits1_CountsBitsInOneNumber_WhenInputIsValid() throws Exception {
        // Arrange
        String number1 = "";
        String number2 = "0";
        String number3 ="7";
        String number4 ="230";

        int expected1 = 0;
        int expected2 = 0;
        int expected3 = 3;
        int expected4 = 5;

        BitsCounter bc = new BitsCounter();

        // Act
        int result1 = bc.noOfBits1(number1);
        int result2 = bc.noOfBits1(number2);
        int result3 = bc.noOfBits1(number3);
        int result4 = bc.noOfBits1(number4);

        // Assert
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
        assertEquals(expected4, result4);
    }

    @Test
    public void noOfBits1_CountsBitsInManyNumbersSeparatedBySemicolon_WhenInputIsValid() throws Exception {
        // Arrange
        String number1 = "";
        String number2 = "0;10";
        String number3 ="7;23;15";

        int expected1 = 0;
        int expected2 = 2;
        int expected3 = 11;

        BitsCounter bc = new BitsCounter();

        // Act
        int result1 = bc.noOfBits1(number1);
        int result2 = bc.noOfBits1(number2);
        int result3 = bc.noOfBits1(number3);

        // Assert
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
    }

    @Test
    public void noOfBits1_CountsBitsInManyNumbersSeparatedBySemicolonOrSpace_WhenInputIsValid() throws Exception {
        // Arrange
        String number1 = "2 15";
        String number2 = "0;10";
        String number3 ="7;23 15";

        int expected1 = 5;
        int expected2 = 2;
        int expected3 = 11;

        BitsCounter bc = new BitsCounter();

        // Act
        int result1 = bc.noOfBits1(number1);
        int result2 = bc.noOfBits1(number2);
        int result3 = bc.noOfBits1(number3);

        // Assert
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
    }

    @Test
    public void noOfBits1_CountsBitsInManyNumbersSeparatedBySemicolonOrWhitespaces_WhenInputIsValid() throws Exception {
        // Arrange
        String number1 = "2    15";
        String number2 = "0;10";
        String number3 ="7\t23\n15";

        int expected1 = 5;
        int expected2 = 2;
        int expected3 = 11;

        BitsCounter bc = new BitsCounter();

        // Act
        int result1 = bc.noOfBits1(number1);
        int result2 = bc.noOfBits1(number2);
        int result3 = bc.noOfBits1(number3);

        // Assert
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
    }

    @Test
    public void noOfBits1_CountsBitsInManyNumbersDecimalAndHexadecimal_WhenInputIsValid() throws Exception {
        // Arrange
        String number1 = "2;15";
        String number2 = "$a4;$cd";
        String number3 ="7;$12;15";

        int expected1 = 5;
        int expected2 = 8;
        int expected3 = 9;

        BitsCounter bc = new BitsCounter();

        // Act
        int result1 = bc.noOfBits1(number1);
        int result2 = bc.noOfBits1(number2);
        int result3 = bc.noOfBits1(number3);

        // Assert
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void noOfBits1_ShouldThrowAnException_WhenNumberAbove255() throws Exception {
        // Arrange
        String number = "400";

        String expected = "-1";

        BitsCounter bc = new BitsCounter();

        // Expect
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Allowed range is 0-255.");

        // Act
        bc.noOfBits1(number);
    }

    @Test
    public void noOfBits1_ShouldThrowAnException_WhenNumberBelow0() throws Exception {
        // Arrange
        String number = "-1";

        String expected = "-1";

        BitsCounter bc = new BitsCounter();

        // Expect
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Allowed range is 0-255.");

        // Act
        bc.noOfBits1(number);
    }

    @Test
    public void noOfBits1_ShouldThrowAnException_WhenInvalidCharacterInInput_Variant1() throws Exception {
        // Arrange
        String number = "21as3;4[[5";

        String expected = "-1";

        BitsCounter bc = new BitsCounter();

        // Expect
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Invalid character.");

        // Act
        bc.noOfBits1(number);
    }

    @Test
    public void noOfBits1_ShouldThrowAnException_WhenInvalidCharacterInInput_Variant2() throws Exception {
        // Arrange
        String number = "1,2,3";

        String expected = "-1";

        BitsCounter bc = new BitsCounter();

        // Expect
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Invalid character.");

        // Act
        bc.noOfBits1(number);
    }
}
