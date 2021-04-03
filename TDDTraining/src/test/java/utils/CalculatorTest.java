package utils;

import utils.Calculator;
import exceptions.NegativeNumbersNotAllowedException;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {


    @Test
    public void add_addsUpToTwoNumber_whenStringIsValid() throws NegativeNumbersNotAllowedException {

        // given
        Calculator myCalculator = new Calculator();

        String numbers1 = "";
        String numbers2 = "1";
        String numbers3 = "1,2";

        //when
        int result1 = myCalculator.add(numbers1);
        int result2 = myCalculator.add(numbers2);
        int result3 = myCalculator.add(numbers3);

        //done
        assertEquals(0, result1);
        assertEquals(1, result2);
        assertEquals(3, result3);

    }


    @Test
    public void add_addsUpToAnyNumbers_whenStringIsValid() throws NegativeNumbersNotAllowedException {

        // given
        Calculator myCalculator = new Calculator();
        String numbers1 = "1,2,3";
        String numbers2 = "10,90,10,20";

        //when
        int result1 = myCalculator.add(numbers1);
        int result2 = myCalculator.add(numbers2);

        //done
        assertEquals(6, result1);
        assertEquals(130, result2);
    }


    @Test
    public void add_addsNumbersUsingNewLineDelimiter_whenStringIsValid() throws NegativeNumbersNotAllowedException {

        // given
        Calculator myCalculator = new Calculator();
        String numbers1 = "1\n2,3";
        String numbers2 = "10\n90,10\n20";

        //when
        int result1 = myCalculator.add(numbers1);
        int result2 = myCalculator.add(numbers2);

        //done
        assertEquals(6, result1);
        assertEquals(130, result2);
    }

    @Test
    public void add_addsNumbersUsingCustomDelimiter_whenStringIsValid() throws NegativeNumbersNotAllowedException {

        // given
        Calculator myCalculator = new Calculator();
        String numbers1 = "//;\n1;2";
        String numbers2 = "//;\n1;2;4";

        //when
        int result1 = myCalculator.add(numbers1);
        int result2 = myCalculator.add(numbers2);

        //done
        assertEquals(3, result1);
        assertEquals(7, result2);
    }

    @Test(expected = NegativeNumbersNotAllowedException.class)
    public void add_shouldThrowAnException_whenNegativeNumbersAreUsed1() throws NegativeNumbersNotAllowedException {

        // given
        Calculator myCalculator = new Calculator();
        String numbers1 = "1,2,-1";

        //when
        try {
            int result1 = myCalculator.add(numbers1);
        } catch (Exception e){

            // done
            assertEquals("Negatives not allowed: -1", e.getMessage());
            throw e;
        }

    }

    @Test(expected = NegativeNumbersNotAllowedException.class)
    public void add_shouldThrowAnException_whenNegativeNumbersAreUsed2() throws NegativeNumbersNotAllowedException {

        // given
        Calculator myCalculator = new Calculator();
        String numbers1 = "//;\n1;-2;-4";

        //when
        try {
            int result1 = myCalculator.add(numbers1);
        } catch (Exception e){

            // done
            assertEquals("Negatives not allowed: -2,-4", e.getMessage());
            throw e;
        }

    }
}