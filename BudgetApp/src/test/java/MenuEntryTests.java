import mockit.Mocked;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MenuEntryTests {

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void MenuEntryShowShouldShowMenuTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry();

        // Act
        String result = menuEntry.show();

        // Assert
        assertNotNull(result);
        assertEquals("MENU ENTRY\n" +
                "1.Add Entry with amount\n" +
                "2.Add Entry with amount and category list\n" +
                "3.Add Entry with amount and cyclicDay\n" +
                "4.Add Entry with amount, cyclicDay and category list\n" +
                "Please select 1-4!", result);
    }

    @Test
    public void MenuEntryReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry();
        String input = "5";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        int result = menuEntry.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuEntryReadShouldReturnErrorWhenBelowRangeTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry();
        String input = "-1";

        // Assert
        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        int result = menuEntry.read(input);
    }

    @Test
    public void MenuEntryReadShouldReturnErrorWhenNullTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry();
        String input = null;

        // Assert
        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        int result = menuEntry.read(input);
    }

    @Test
    public void MenuEntryReadShouldReturnErrorWhenEmptyTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry();
        String input = "";

        // Assert
        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        int result = menuEntry.read(input);
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
        int result = menuEntry.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuEntryGetAmountInputShow() {
        // Arrange
        MenuEntry menuEntry = new MenuEntry();


        ByteArrayInputStream in = new ByteArrayInputStream("20".getBytes());

        int result = menuEntry.getAmountInputShow(in);

        // Assert
        assertEquals(20, result);

    }



    @Test
    public void MenuEntryGetCategoryInputShow() {
        // Arrange
        MenuEntry menuEntry = new MenuEntry();

        ByteArrayInputStream in = new ByteArrayInputStream("aa;bb".getBytes());

        // Act
        List<String> result = menuEntry.getCategoryInputShow(in);

        // Assert
        assertEquals(2, result.size());
        assertEquals("aa", result.get(0));
        assertEquals("bb", result.get(1));
    }

    @Test
    public void MenuEntryGetCyclicDayInputShow() {
        // Arrange
        MenuEntry menuEntry = new MenuEntry();

        ByteArrayInputStream in = new ByteArrayInputStream("12".getBytes());

        // Act
        int result = menuEntry.getCyclicDayInputShow(in);

        // Assert
        assertEquals(12, result);
    }
}
