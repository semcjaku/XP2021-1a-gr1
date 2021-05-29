package menu;

import model.Entry;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MenuEntryTests {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void MenuEntryShowShouldShowMenuTest() {
        // Arrange
        AbstractMenu menuEntry = new MenuEntry();

        // Act
        String result = menuEntry.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nMENU ENTRY\n" +
                "1.Add model.Entry with amount\n" +
                "2.Add model.Entry with amount and category list\n" +
                "Please select 1-2!", result);
    }

    @Test
    public void MenuEntryReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry();
        String input = "15";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuEntry.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuEntryReadShouldReturnErrorWhenBelowRangeTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry();
        String input = "-1";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuEntry.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuEntryReadShouldReturnErrorWhenNullTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry();
        String input = null;

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuEntry.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuEntryReadShouldReturnErrorWhenEmptyTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry();
        String input = "";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuEntry.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuEntryReadShouldTrimInputTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry();
        String input = "1  ";

        // Act
        int result = menuEntry.read(input);

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void MenuEntryReadShouldReturnErrorWhenNotANumberTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry();
        String input = "Hello";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input not a number");

        // Act
        menuEntry.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuEntryGetAmountInputShowTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("20".getBytes());

        MenuEntry menuEntry = new MenuEntry(new Scanner(in));

        // Act
        int result = menuEntry.getAmountInputShow();

        // Assert
        assertEquals(20, result);
    }

    @Test
    public void MenuEntryGetCategoryInputShowTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("aa;bb".getBytes());
        MenuEntry menuEntry = new MenuEntry(new Scanner(in));

        // Act
        List<String> result = menuEntry.getCategoryInputShow();

        // Assert
        assertEquals(2, result.size());
        assertEquals("aa", result.get(0));
        assertEquals("bb", result.get(1));
    }

    @Test
    public void MenuEntryShowInputsByChoiceFirstTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("12".getBytes());
        MenuEntry menuEntry = new MenuEntry(new Scanner(in));

        // Act
        Entry result = menuEntry.showInputsByChoice(1);

        // Assert
        assertEquals(12, result.getAmount());
    }

    @Test
    public void MenuEntryShowInputsByChoiceSecondTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("12" + System.getProperty("line.separator") + "Food").getBytes());
        MenuEntry menuEntry = new MenuEntry(new Scanner(in));

        // Act
        Entry result = menuEntry.showInputsByChoice(2);

        // Assert
        assertEquals(12, result.getAmount());
        assertEquals(1, result.getCategories().size());
        assertEquals("Food", result.getCategories().get(0));
    }
}
