package menu;

import model.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MenuUserTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void MenuUserShouldShowMenuTest() {
        // Arrange
        AbstractMenu menuUser = new MenuUser();

        // Act
        String result = menuUser.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nMENU USER\n" +
                "1.Login\n" +
                "2.Register\n" +
                "0.Exit\n" +
                "Please select 0-1!", result);
    }

    @Test
    public void MenuUserReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        MenuUser menuUser = new MenuUser();
        String input = "15";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuUser.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuUserReadShouldReturnErrorWhenBelowRangeTest() throws Exception {
        // Arrange
        MenuUser menuUser = new MenuUser();
        String input = "-1";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuUser.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuUserReadShouldReturnErrorWhenNullTest() throws Exception {
        // Arrange
        MenuUser menuUser = new MenuUser();
        String input = null;

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuUser.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuUserReadShouldReturnErrorWhenEmptyTest() throws Exception {
        // Arrange
        MenuUser menuUser = new MenuUser();
        String input = "";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuUser.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuUserReadShouldTrimInputTest() throws Exception {
        // Arrange
        MenuUser menuUser = new MenuUser();
        String input = "1  ";

        // Act
        int result = menuUser.read(input);

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void MenuUserGetNameInputShowTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("name".getBytes());
        MenuUser menuUser = new MenuUser(in);

        // Act
        String result = menuUser.getEmailInputShow();

        // Assert
        assertEquals("name", result);
    }

    @Test
    public void MenuUserGetPasswordInputShowTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("pass".getBytes());
        MenuUser menuUser = new MenuUser(in);

        // Act
        String result = menuUser.getEmailInputShow();

        // Assert
        assertEquals("pass", result);
    }

    @Test
    public void MenuUserShowInputsByChoiceFirstTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("name" + System.getProperty("line.separator") + "pass").getBytes());
        MenuUser menuUser = new MenuUser(in);

        // Act
        User result = menuUser.showInputsByChoice(1);

        // Assert
        assertEquals("name", result.getEmail());
        assertEquals("pass", result.getPassword());
    }
}
