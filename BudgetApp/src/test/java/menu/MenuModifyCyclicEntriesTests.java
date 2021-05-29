package menu;

import model.Entry;
import model.CyclicEntryPrototype;
import model.IntervalCyclicEntryPrototype;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MenuModifyCyclicEntriesTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void MenuModifyCyclicEntriesShouldShowMenuTest() {
        // Arrange
        AbstractMenu menuMod = new MenuModifyCyclicEntries();

        // Act
        String result = menuMod.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nChoose an action:\n" +
                "1.Modify entry prototype (amount, categories)\n" +
                "2.Change cyclic parameter\n" +
                "0.Return\n" +
                "Please select 0-2!", result);
    }

    @Test
    public void MenuModifyCyclicEntriesReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        MenuModifyCyclicEntries menuMod = new MenuModifyCyclicEntries();
        String input = "15";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuMod.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuModifyCyclicEntriesReadShouldReturnErrorWhenBelowRangeTest() throws Exception {
        // Arrange
        MenuModifyCyclicEntries menuMod = new MenuModifyCyclicEntries();
        String input = "-1";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuMod.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuModifyCyclicEntriesReadShouldReturnErrorWhenNullTest() throws Exception {
        // Arrange
        MenuModifyCyclicEntries menuMod = new MenuModifyCyclicEntries();
        String input = null;

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuMod.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuModifyCyclicEntriesReadShouldReturnErrorWhenEmptyTest() throws Exception {
        // Arrange
        MenuModifyCyclicEntries menuMod = new MenuModifyCyclicEntries();
        String input = "";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuMod.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuModifyCyclicEntriesReadShouldTrimInputTest() throws Exception {
        // Arrange
        MenuModifyCyclicEntries menuMod = new MenuModifyCyclicEntries();
        String input = "1  ";

        // Act
        int result = menuMod.read(input);

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void MenuModifyCyclicEntriesExecuteActionsChangeCyclicParameterTest() throws InvalidInputException {
        // Arrange
        int oldCyclicParameter = 22;
        int newCyclicParameter = 31;

        ByteArrayInputStream in = new ByteArrayInputStream(String.valueOf(newCyclicParameter).getBytes());
        MenuModifyCyclicEntries menuMod = new MenuModifyCyclicEntries(new Scanner(in));
        Entry mockEntry = new Entry(20);
        CyclicEntryPrototype prototype = new IntervalCyclicEntryPrototype(mockEntry, oldCyclicParameter);
        CyclicEntryPrototype testPrototype = new IntervalCyclicEntryPrototype(mockEntry, newCyclicParameter);

        // Act
        menuMod.executeActions(2, prototype);

        // Assert
        assertEquals(testPrototype, prototype);
    }

    @Test
    public void MenuModifyCyclicEntriesExecuteActionsModifyEntryChangeAmountTest() throws InvalidInputException {
        // Arrange
        int oldAmount = 45;
        int newAmount = 37;

        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator") + newAmount).getBytes());
        MenuModifyCyclicEntries menuMod = new MenuModifyCyclicEntries(new Scanner(in));
        CyclicEntryPrototype prototype = new IntervalCyclicEntryPrototype(new Entry(oldAmount), 5);

        // Act
        menuMod.executeActions(1, prototype);

        // Assert
        assertEquals(newAmount, prototype.getPrototypeEntry().getAmount());
    }
}
