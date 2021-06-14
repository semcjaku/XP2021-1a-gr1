package menu;

import model.Config;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MenuConfigTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void MenuConfigShouldShowMenuTest() {
        // Arrange
        AbstractMenu menuConfig = new MenuConfig();

        // Act
        String result = menuConfig.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nMENU CONFIG\n" +
                "1.Open application\n" +
                "2.View config\n" +
                "3.Save new config", result);
    }

    @Test
    public void MenuConfigReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        MenuConfig menuConfig = new MenuConfig();
        String input = "15";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuConfig.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuConfigReadShouldReturnErrorWhenBelowRangeTest() throws Exception {
        // Arrange
        MenuConfig menuConfig = new MenuConfig();
        String input = "-1";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuConfig.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuConfigReadShouldReturnErrorWhenNullTest() throws Exception {
        // Arrange
        MenuConfig menuConfig = new MenuConfig();
        String input = null;

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuConfig.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuConfigReadShouldReturnErrorWhenEmptyTest() throws Exception {
        // Arrange
        MenuConfig menuConfig = new MenuConfig();
        String input = "";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuConfig.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuConfigReadShouldTrimInputTest() throws Exception {
        // Arrange
        MenuConfig menuConfig = new MenuConfig();
        String input = "1  ";

        // Act
        menuConfig.validateChoice(input);

        // Assert

    }

    @Test
    public void MenuConfigGetNewConfigTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("dbtesttest" + System.getProperty("line.separator") + "wallettesttest").getBytes());
        MenuConfig menuConfig = new MenuConfig(in);

        // Act
        Config result = menuConfig.getNewConfigFromUser();

        // Assert
        assertNotNull(result);
        assertEquals("dbtesttest", result.getUsersDbPath());
        assertEquals("wallettesttest", result.getWalletListPath());
    }


}
