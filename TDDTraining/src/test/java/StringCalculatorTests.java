import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTests {

    private Calculator calculator;

    // https://www.baeldung.com/junit-before-beforeclass-beforeeach-beforeall
    @Before
    public void initialize(){
        Calculator calculator = new Calculator();
    }

    @Test
//    public void Add_AddsUpToTwoNumber_WhenStringIsValid(String calculation, int expected) {
    public void Add_AddsUpNoNumber_WhenStringIsValid() {

        String calculation = "";
        int result = calculator.Add(calculation);

//        Assert.assertThat(result, CoreMatchers.is(expected));
        Assert.assertEquals(result, 0);
    }

    @Test
    public void Add_AddsUpOneNumber_WhenStringIsValid() {

        String calculation = "1";
        int result = calculator.Add(calculation);

        Assert.assertEquals(result, 1);
    }

    @Test
    public void Add_AddsUpTwoNumbers_WhenStringIsValid() {

        String calculation = "1,2";
        int result = calculator.Add(calculation);

        Assert.assertEquals(result, 3);
    }
}
