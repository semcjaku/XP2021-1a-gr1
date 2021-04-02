import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class StringCalculatorTests {

    private Object[] parametersToTestAddUpTOTwoNumber() {
        return new Object[]{
                new Object[]{""   , 0},
                new Object[]{"1"  , 1},
                new Object[]{"1,2", 3}
        };
    }

    private Object[] parametersToTestAddUpTOAnyNumber() {
        return new Object[]{
                new Object[]{"1,2,3"       , 6},
                new Object[]{"10,90,10,20" , 130}
        };
    }

    private Object[] parametersToTestAddUsingNewLineDelimiter() {
        return new Object[]{
                new Object[]{"1\n2\n3"       , 6},
                new Object[]{"1\n2,3"        , 6},
                new Object[]{"10\n90,10\n20" , 130}
        };
    }

    private Object[] parametersToTestAddUsingCustomDelimiter() {
        return new Object[]{
                new Object[]{"//;\n1;2"   , 3},
                new Object[]{"//;\n1;2;4" , 7},
                new Object[]{"//;\n"      , 0}
        };
    }

    private Object[] parametersToTestAddNegativeNumbers() {
        return new Object[]{
                new Object[]{"//;\n-1;2" , "-1"},
                new Object[]{"1,-2,-3"    , "-2,-3"}
        };
    }

    @Test
    @Parameters(method = "parametersToTestAddUpTOTwoNumber")
    public void Add_AddsUpToTwoNumber_WhenStringIsValid(String numbers, int expected) throws NegativesNotAllowedException {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.Add(numbers), expected);
    }

    @Test
    @Parameters(method = "parametersToTestAddUpTOAnyNumber")
    public void Add_AddsUpToAnyNumber_WhenStringIsValid(String numbers, int expected) throws NegativesNotAllowedException {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.Add(numbers), expected);
    }

    @Test
    @Parameters(method = "parametersToTestAddUsingNewLineDelimiter")
    public void Add_AddsNumbersUsingNewLineDelimiter_WhenStringIsValid(String numbers, int expected) throws NegativesNotAllowedException {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.Add(numbers), expected);
    }

    @Test
    @Parameters(method = "parametersToTestAddUsingCustomDelimiter")
    public void Add_AddsNumbersUsingCustomDelimiter_WhenStringIsValid(String numbers, int expected) throws NegativesNotAllowedException
    {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.Add(numbers), expected);
    }

    @Test
    @Parameters(method = "parametersToTestAddNegativeNumbers")
    public void Add_ShouldThrowAnException_WhenNegativeNumbersAreUsed(String numbers, String negativeNumbers)
    {
        Calculator calculator = new Calculator();
        try {
            calculator.Add(numbers);
            Assert.fail("Expected exception to be thrown"); // nie wyrzucilo wyjatku a powinno
        } catch (NegativesNotAllowedException e) {
            Assert.assertEquals("Negatives not allowed: "+negativeNumbers, e.getMessage());
        }

    }
}
