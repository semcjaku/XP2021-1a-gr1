package menu;

import exception.InvalidInputException;
import model.Entry;
import model.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MenuModifyEntriesTests {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void MenuModifyEntriesShouldShowMenuTest() {
        // Arrange
        AbstractMenu menuMod = new MenuModifyEntries();

        // Act
        String result = menuMod.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nChoose an action:\n" +
                "1.Change amount\n" +
                "2.Change categories\n" +
                "3.Add category\n" +
                "4.Remove category\n" +
                "0.Return\n" +
                "Please select 0-4!", result);
    }

    @Test
    public void MenuModifyEntriesReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        MenuModifyEntries menuMod = new MenuModifyEntries();
        String input = "15";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuMod.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuModifyEntriesReadShouldReturnErrorWhenBelowRangeTest() throws Exception {
        // Arrange
        MenuModifyEntries menuMod = new MenuModifyEntries();
        String input = "-1";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuMod.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuModifyEntriesReadShouldReturnErrorWhenNullTest() throws Exception {
        // Arrange
        MenuModifyEntries menuMod = new MenuModifyEntries();
        String input = null;

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuMod.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuModifyEntriesReadShouldReturnErrorWhenEmptyTest() throws Exception {
        // Arrange
        MenuModifyEntries menuMod = new MenuModifyEntries();
        String input = "";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuMod.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuModifyEntriesReadShouldTrimInputTest() throws Exception {
        // Arrange
        MenuModifyEntries menuMod = new MenuModifyEntries();
        String input = "1  ";

        // Act
        int result = menuMod.read(input);

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void MenuModifyEntriesExecuteActionsChangeAmountTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("15").getBytes());
        MenuModifyEntries menuMod = new MenuModifyEntries(new Scanner(in));
        int amount = 2154;
        Entry entry = new Entry(amount);

        // Act
        menuMod.executeActions(1, entry);

        // Assert
        assertEquals(15, entry.getAmount());
    }

    @Test
    public void MenuModifyEntriesExecuteActionsChangeCategoriesTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("food, rent").getBytes());
        MenuModifyEntries menuMod = new MenuModifyEntries(new Scanner(in));
        int amount = 2154;
        Entry entry = new Entry(amount);
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");

        // Act
        menuMod.executeActions(2, entry);

        // Assert
        assertEquals(testList, entry.getCategories());
    }

    @Test
    public void MenuModifyEntriesExecuteActionsAddCategoryTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("vod").getBytes());
        MenuModifyEntries menuMod = new MenuModifyEntries(new Scanner(in));
        int amount = 2154;
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        Entry entry = new Entry(amount, testList);
        List<String> testList2 = new LinkedList<>() {};
        testList2.add("food");
        testList2.add("rent");
        testList2.add("vod");

        // Act
        menuMod.executeActions(3, entry);

        // Assert
        assertEquals(testList2, entry.getCategories());
    }

    @Test
    public void MenuModifyEntriesExecuteActionsRemoveCategoriesTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("food").getBytes());
        MenuModifyEntries menuMod = new MenuModifyEntries(new Scanner(in));
        int amount = 2154;
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        Entry entry = new Entry(amount, testList);
        List<String> testList2 = new LinkedList<>() {};
        testList2.add("rent");

        // Act
        menuMod.executeActions(4, entry);

        // Assert
        assertEquals(testList2, entry.getCategories());
    }
}
