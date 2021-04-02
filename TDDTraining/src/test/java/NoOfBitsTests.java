import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class NoOfBitsTests {

    private Object[] parametersToTestCountNoOfBits1WhenNumberIsValid() {
        return new Object[]{
                new Object[]{""    , 0},
                new Object[]{"0"   , 0},
                new Object[]{"1"   , 1},
                new Object[]{"10"  , 2},
                new Object[]{"255" , 8},
        };
    }

    private Object[] parametersToTestCountWhenRangeExceeded() {
        return new Object[]{
                new Object[]{"-1"   , "Range 0-255 exceeded"},
                new Object[]{"256"  , "Range 0-255 exceeded"},
                new Object[]{"1000" , "Range 0-255 exceeded"},
                new Object[]{"-10"  , "Range 0-255 exceeded"}
        };
    }

    private Object[] parametersToTestCountNoOfBits1WhenNumbersIsValid() {
        return new Object[]{
                new Object[]{"0;0"      , 0},
                new Object[]{"1;0"      , 1},
                new Object[]{"1;10"     , 3},
                new Object[]{"10;255;0" , 10}
        };
    }

    private Object[] parametersToTestCountManyWhenRangeExceeded() {
        return new Object[]{
                new Object[]{"-1;0"  , "Range 0-255 exceeded"},
                new Object[]{"1;256" , "Range 0-255 exceeded"}
        };
    }

    private Object[] parametersToTestCountNoOfBits1WhenWhiteSpacesAreDelimiters() {
        return new Object[]{
                new Object[]{"0       0;0"         , 0},
                new Object[]{"1;0\n1"              , 2},
                new Object[]{"1\t10\t2"            , 4},
                new Object[]{"10;255 0\n1\t2"      , 12},
                new Object[]{"10;255  0\n\n1\t\t2" , 12}
        };
    }

    private Object[] parametersToTestNoOfBits1WhenFormatIsWrong() {
        return new Object[]{
                new Object[]{"1,0"    , "Wrong format"},
                new Object[]{"-;1;25" , "Wrong format"},
                new Object[]{";1;254" , "Wrong format"},
                new Object[]{"1;255;" , "Wrong format"},
                new Object[]{"-0;255" , "Wrong format"},
                new Object[]{"00;255" , "Wrong format"}
        };
    }

    private Object[] parametersToTestCountNoOfBits1WhenHexadecimalNumbers() {
        return new Object[]{
                new Object[]{"$a4"              , 3}, //164
                new Object[]{"10;$a4\n$ff 253" , 20} // 10 164 255
        };
    }

    @Test
    @Parameters(method = "parametersToTestCountNoOfBits1WhenNumberIsValid")
    public void CountNoOfBits1_WhenNumberIsValid(String number, int expected) throws RangeExceededException, FormatException {
        BitsCounter bitsCounter = new BitsCounter();
        Assert.assertEquals(bitsCounter.noOfBits1(number), expected);
    }

    @Test
    @Parameters(method = "parametersToTestCountWhenRangeExceeded")
    public void CountNoOfBits1_ShouldThrowAnException_WhenRangeIsExceeded(String number, String expected) throws FormatException {
        BitsCounter bitsCounter = new BitsCounter();
        try{
            bitsCounter.noOfBits1(number);
            Assert.fail("Expected exception to be thrown");
        } catch(RangeExceededException e) {
            Assert.assertEquals(expected, e.getMessage());
        }
    }

    @Test
    @Parameters(method = "parametersToTestCountNoOfBits1WhenNumbersIsValid")
    public void CountNoOfBits1_WhenNumbersIsValid(String numbers, int expected) throws RangeExceededException, FormatException {
        BitsCounter bitsCounter = new BitsCounter();
        Assert.assertEquals(bitsCounter.noOfBits1(numbers), expected);
    }

    @Test
    @Parameters(method = "parametersToTestCountManyWhenRangeExceeded")
    public void CountManyNoOfBits1_ShouldThrowAnException_WhenRangeIsExceeded(String numbers, String expected) throws FormatException{
        BitsCounter bitsCounter = new BitsCounter();
        try{
            bitsCounter.noOfBits1(numbers);
            Assert.fail("Expected exception to be thrown");
        } catch(RangeExceededException e) {
            Assert.assertEquals(expected, e.getMessage());
        }
    }

    @Test
    @Parameters(method = "parametersToTestCountNoOfBits1WhenWhiteSpacesAreDelimiters")
    public void CountNoOfBits1_WhenWhiteSpacesAreDelimiters(String numbers, int expected) throws RangeExceededException, FormatException {
        BitsCounter bitsCounter = new BitsCounter();
        Assert.assertEquals(bitsCounter.noOfBits1(numbers), expected);
    }

    @Test
    @Parameters(method = "parametersToTestNoOfBits1WhenFormatIsWrong")
    public void CountNoOfBits1_ShouldThrowAnException_WhenFormatIsWrong(String numbers, String expected) throws RangeExceededException {
        BitsCounter bitsCounter = new BitsCounter();
        try{
            bitsCounter.noOfBits1(numbers);
            Assert.fail("Expected exception to be thrown");
        } catch(FormatException e) {
            Assert.assertEquals(expected, e.getMessage());
        }
    }

    @Test
    @Parameters(method = "parametersToTestCountNoOfBits1WhenHexadecimalNumbers")
    public void CountNoOfBits1_WhenHexadecimalNumbers(String numbers, int expected) throws RangeExceededException, FormatException {
        BitsCounter bitsCounter = new BitsCounter();
        Assert.assertEquals(bitsCounter.noOfBits1(numbers), expected);
    }

}
