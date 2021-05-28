package menu;

import exception.InvalidInputException;
import menu.AbstractMenu;
import menu.Menu;
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
                "1.Add model.Entry\n" +
                "2.Add Cyclic Entry\n" +
                "3.Add Category\n" +
                "4.Show model.Entry List\n" +
                "5.Show Category List\n" +
                "0.Exit\n" +
                "Please select 0-5!", result);
    }

    @Test
    public void MenuReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        Menu menu = new Menu();
        String input = "15";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menu.read(input);

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
        menu.read(input);

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
        menu.read(input);

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
        menu.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuReadShouldTrimInputTest() throws Exception {
        // Arrange
        Menu menu = new Menu();
        String input = "1  ";

        // Act
        int result = menu.read(input);

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void MenuReadShouldReturnErrorWhenNotANumberTest() throws Exception {
        // Arrange
        Menu menu = new Menu();
        String input = "Hello";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input not a number");

        // Act
        menu.read(input);

        // Assert
        // throw exception
    }
}
