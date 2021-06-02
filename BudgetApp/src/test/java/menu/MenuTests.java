package menu;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MenuTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void MenuShowShouldShowMenuTest() {
        // Arrange
        AbstractMenu menu = new Menu();

        // Act
        String result = menu.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nMENU\n" +
                "1.Add Entry\t\t\t" +
                "2.Add Cyclic Entry\t\t\t" +
                "3.Add Category\n" +
                "4.Remove Entry\t\t" +
                "5.Remove Cyclic Entry\n" +
                "6.Modify Entry\t\t" +
                "7.Modify Cyclic Entry\n" +
                "8.Show Entry List\t" +
                "9.Show Cyclic Entries List\t" +
                "10.Show Category List\n" +
                "11.Switch wallet\t" +
                "12.Manage wallets\t\t\t" +
                "13.Show user wallets\n" +
                "0.Exit", result);
    }

    @Test
    public void MenuReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        Menu menu = new Menu();
        String input = "15";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menu.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuReadShouldReturnErrorWhenBelowRangeTest() throws Exception {
        // Arrange
        Menu menu = new Menu();
        String input = "-1";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menu.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuReadShouldReturnErrorWhenNullTest() throws Exception {
        // Arrange
        Menu menu = new Menu();
        String input = null;

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menu.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuReadShouldReturnErrorWhenEmptyTest() throws Exception {
        // Arrange
        Menu menu = new Menu();
        String input = "";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menu.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuReadShouldTrimInputTest() throws Exception {
        // Arrange
        Menu menu = new Menu();
        String input = "1  ";

        // Act
        menu.validateChoice(input);

        // Assert

    }

    @Test
    public void MenuReadShouldReturnErrorWhenNotANumberTest() throws Exception {
        // Arrange
        Menu menu = new Menu();
        String input = "Hello";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input not a number");

        // Act
        menu.validateChoice(input);

        // Assert
        // throw exception
    }
}
