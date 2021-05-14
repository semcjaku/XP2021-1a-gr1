import org.junit.*;
import org.junit.rules.ExpectedException;
import java.io.ByteArrayInputStream;
import java.util.List;
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

        MenuEntry menuEntry = new MenuEntry(in);

        // Act
        int result = menuEntry.getAmountInputShow();

        // Assert
        assertEquals(20, result);
    }

    @Test
    public void MenuEntryGetCategoryInputShowTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("aa;bb".getBytes());
        MenuEntry menuEntry = new MenuEntry(in);

        // Act
        List<String> result = menuEntry.getCategoryInputShow();

        // Assert
        assertEquals(2, result.size());
        assertEquals("aa", result.get(0));
        assertEquals("bb", result.get(1));
    }

    @Test
    public void MenuEntryGetCyclicDayInputShowTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("12".getBytes());
        MenuEntry menuEntry = new MenuEntry(in);

        // Act
        int result = menuEntry.getCyclicDayInputShow();

        // Assert
        assertEquals(12, result);
    }

    @Test
    public void MenuEntryShowInputsByChoiceFirstTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("12".getBytes());
        MenuEntry menuEntry = new MenuEntry(in);

        // Act
        Entry result = menuEntry.showInputsByChoice(1);

        // Assert
        assertEquals(12, result.getAmount());
    }
}
