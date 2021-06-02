package menu;

import model.Entry;
import model.CyclicEntryPrototype;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class MenuCyclicEntryTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void MenuCyclicEntryShowShouldShowMenuTest() {
        // Arrange
        AbstractMenu menuCyclicEntry = new MenuCyclicEntry();

        // Act
        String result = menuCyclicEntry.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nMENU CYCLIC ENTRY\n" +
                "1.Add Cyclic Entry repeated at constant interval\n" +
                "2.Add Cyclic Entry repeated monthly", result);
    }

    @Test
    public void MenuCyclicEntryReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry();
        String input = "15";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuCyclicEntry.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuCyclicEntryReadShouldReturnErrorWhenBelowRangeTest() throws Exception {
        // Arrange
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry();
        String input = "-1";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuCyclicEntry.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuCyclicEntryReadShouldReturnErrorWhenNullTest() throws Exception {
        // Arrange
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry();
        String input = null;

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuCyclicEntry.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuCyclicEntryReadShouldReturnErrorWhenEmptyTest() throws Exception {
        // Arrange
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry();
        String input = "";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuCyclicEntry.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuCyclicEntryReadShouldTrimInputTest() throws Exception {
        // Arrange
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry();
        String input = "1  ";

        // Act
        menuCyclicEntry.validateChoice(input);

        // Assert

    }

    @Test
    public void MenuCyclicEntryReadShouldReturnErrorWhenNotANumberTest() throws Exception {
        // Arrange
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry();
        String input = "Hello";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input not a number");

        // Act
        menuCyclicEntry.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuCyclicEntryGetCyclicIntervalInDaysInputShowTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("12".getBytes());
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(in);

        // Act
        int result = menuCyclicEntry.getCyclicIntervalInDaysInputShow();

        // Assert
        assertEquals(12, result);
    }

    @Test
    public void MenuCyclicEntryGetCyclicDayOfMonthInputShowTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("12".getBytes());
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(in);

        // Act
        int result = menuCyclicEntry.getCyclicDayOfMonthInputShow();

        // Assert
        assertEquals(12, result);
    }

    @Test
    public void MenuCyclicEntryGetEntryInputShowTest() throws InvalidInputException {
        // Arrange
        int amount = 5;
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator") + "5").getBytes());
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(in);

        // Act
        Entry result = menuCyclicEntry.getEntryInputShow();

        // Assert
        assertEquals(new Entry(amount), result);
    }

    @Test
    public void MenuCyclicEntryShowInputsByChoiceFirstTest() throws InvalidInputException {
        // Arrange
        int amount = 3;
        int interval = 5;
        ByteArrayInputStream in = new ByteArrayInputStream(
                ("1" + System.getProperty("line.separator") + amount + System.getProperty("line.separator") + interval).getBytes());
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(in);

        // Act
        CyclicEntryPrototype result = menuCyclicEntry.showInputsByChoice(1);

        // Assert
        assertEquals(amount, result.getPrototypeEntry().getAmount());
        assertTrue(result.toString().contains("intervalInDays=" + interval));
    }

    @Test
    public void MenuCyclicEntryShowInputsByChoiceSecondTest() throws InvalidInputException {
        // Arrange
        int amount = 3;
        int dayOfMonth = 5;
        ByteArrayInputStream in = new ByteArrayInputStream(
                ("1" + System.getProperty("line.separator") + amount + System.getProperty("line.separator") + dayOfMonth).getBytes());
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(in);

        // Act
        CyclicEntryPrototype result = menuCyclicEntry.showInputsByChoice(2);

        // Assert
        assertEquals(amount, result.getPrototypeEntry().getAmount());
        assertTrue(result.toString().contains("dayOfMonth=" + dayOfMonth));
    }
}
