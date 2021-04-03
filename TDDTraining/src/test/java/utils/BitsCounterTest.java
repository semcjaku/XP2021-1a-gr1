package utils;

import exceptions.InvalidFormatException;
import exceptions.NumberOutOfRangeException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BitsCounterTest {

    @Test
    public void noOfBits1_fromOneNumber_whenStringIsValid() throws Exception {

        // given
        BitsCounter bitsCounter = new BitsCounter();

        String number1 = "";
        String number2 = "1";
        String number3 = "2";
        String number4 = "33";

        //when
        int result1 = bitsCounter.noOfBits1(number1);
        int result2 = bitsCounter.noOfBits1(number2);
        int result3 = bitsCounter.noOfBits1(number3);
        int result4 = bitsCounter.noOfBits1(number4);

        //done
        assertEquals(0, result1);
        assertEquals(1, result2);
        assertEquals(1, result3);
        assertEquals(2, result4);

    }

    @Test(expected = NumberOutOfRangeException.class)
    public void noOfBits1_fromOneNumber_whenStringIsOutOfBound() throws Exception {

        // given
        BitsCounter bitsCounter = new BitsCounter();
        String number1 = "-1";

        //when
        try {
            int result1 = bitsCounter.noOfBits1(number1);
        } catch (Exception e) {

            // done
            assertEquals("Numbers out of range: -1", e.getMessage());
            throw e;
        }

    }

    @Test(expected = NumberOutOfRangeException.class)
    public void noOfBits1_fromOneNumber_whenStringIsOutOfBound2() throws Exception {

        // given
        BitsCounter bitsCounter = new BitsCounter();
        String number1 = "256";

        //when
        try {
            int result1 = bitsCounter.noOfBits1(number1);
        } catch (Exception e) {

            // done
            assertEquals("Numbers out of range: 256", e.getMessage());
            throw e;
        }
    }


    @Test
    public void noOfBits1_fromMultipleNumbersSeparatedBySemicolon_whenStringIsValid() throws Exception {

        // given
        BitsCounter bitsCounter = new BitsCounter();

        String numbers1 = "1;2";
        String numbers2 = "1;2;4;8;15";

        //when
        int result1 = bitsCounter.noOfBits1(numbers1);
        int result2 = bitsCounter.noOfBits1(numbers2);

        //done
        assertEquals(2, result1);
        assertEquals(8, result2);
    }

    @Test
    public void noOfBits1_fromMultipleNumbersSeparatedBySemicolonOrSpace_whenStringIsValid() throws Exception {

        // given
        BitsCounter bitsCounter = new BitsCounter();

        String numbers1 = "0 0 1";
        String numbers2 = "1 2;4 8;15";

        //when
        int result1 = bitsCounter.noOfBits1(numbers1);
        int result2 = bitsCounter.noOfBits1(numbers2);

        //done
        assertEquals(1, result1);
        assertEquals(8, result2);
    }

    @Test
    public void noOfBits1_fromMultipleNumbersSeparatedBySemicolonOrWhiteSpace_whenStringIsValid() throws Exception {

        // given
        BitsCounter bitsCounter = new BitsCounter();

        String numbers1 = "0 0\n1";
        String numbers2 = "1    2;4 8\t15";

        //when
        int result1 = bitsCounter.noOfBits1(numbers1);
        int result2 = bitsCounter.noOfBits1(numbers2);

        //done
        assertEquals(1, result1);
        assertEquals(8, result2);
    }

    @Test(expected = InvalidFormatException.class)
    public void noOfBits1_fromMultipleNumbersSeparatedBySemicolonOrWhiteSpace_whenStringIsInValid() throws Exception {

        // given
        BitsCounter bitsCounter = new BitsCounter();
        String number1 = "0 0\n1|2";

        //when
        try {
            int result1 = bitsCounter.noOfBits1(number1);
        } catch (InvalidFormatException e) {

            // done
            assertEquals("Invalid characters provided", e.getMessage());
            throw e;
        }

    }

    @Test
    public void noOfBits1_fromMultipleNumbersWithHex_whenStringIsValid() throws Exception {

        // given
        BitsCounter bitsCounter = new BitsCounter();

        String numbers1 = "$1";
        String numbers2 = "10 $a4";
        String numbers3 = "10 $a4;$ff 253";

        //when
        int result1 = bitsCounter.noOfBits1(numbers1);
        int result2 = bitsCounter.noOfBits1(numbers2);
        int result3 = bitsCounter.noOfBits1(numbers3);

        //done
        assertEquals(1, result1);
        assertEquals(5, result2);
        assertEquals(20, result3);
    }

}