package menu;

import model.Entry;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.WalletService;

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
        AbstractMenu menuEntry = new MenuEntry(System.in);

        // Act
        String result = menuEntry.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nMENU ENTRY\n" +
                "1.Add Entry with amount\n" +
                "2.Add Entry with amount and category list\n" +
                "0.Exit", result);
    }

    @Test
    public void MenuEntryReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry(System.in);
        String input = "15";

        // Expect
        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuEntry.validateChoice(input);
    }

    @Test
    public void MenuEntryReadShouldReturnErrorWhenBelowRangeTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry(System.in);
        String input = "-1";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuEntry.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuEntryReadShouldReturnErrorWhenNullTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry(System.in);
        String input = null;

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuEntry.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuEntryReadShouldReturnErrorWhenEmptyTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry(System.in);
        String input = "";

        // Expect
        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuEntry.validateChoice(input);
    }

    @Test
    public void MenuEntryReadShouldTrimInputTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry(System.in);
        String input = "1  ";

        // Act
        menuEntry.validateChoice(input);
    }

    @Test
    public void MenuEntryReadShouldReturnErrorWhenNotANumberTest() throws Exception {
        // Arrange
        MenuEntry menuEntry = new MenuEntry(System.in);
        String input = "Hello";

        // Expect
        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input not a number");

        // Act
        menuEntry.validateChoice(input);

    }

    @Test
    public void MenuEntryGetAmountInputShowTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("20".getBytes());

        MenuEntry menuEntry = new MenuEntry(new Scanner(in));

        // Act
        int result = menuEntry.getAmountFromUser();

        // Assert
        assertEquals(20, result);
    }

    @Test
    public void MenuEntryGetCategoryInputShowTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("aa;bb".getBytes());
        MenuEntry menuEntry = new MenuEntry(new Scanner(in));

        // Act
        List<String> result = menuEntry.getCategoriesFromUser();

        // Assert
        assertEquals(2, result.size());
        assertEquals("aa", result.get(0));
        assertEquals("bb", result.get(1));
    }

    @Test
    public void MenuEntryExecuteChoiceOptionOneTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("12".getBytes());
        WalletService walletService = new WalletService(new Scanner(in));
        walletService.setLoggedInUser("user1");
        walletService.addWallet("Wallet","user1");
        walletService.setCurrentWalletName("Wallet");
        MenuEntry menuEntry = new MenuEntry(walletService);

        // Act
        menuEntry.executeChoice(1);

        // Assert
        Entry result = walletService.getEntryList("Wallet").getEntry(0);
        assertEquals(12, result.getAmount());
    }

    @Test
    public void MenuEntryExecuteChoiceOptionTwoTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("12" + System.getProperty("line.separator") + "Food").getBytes());
        WalletService walletService = new WalletService(new Scanner(in));
        walletService.setLoggedInUser("user1");
        walletService.addWallet("Wallet","user1");
        walletService.setCurrentWalletName("Wallet");
        MenuEntry menuEntry = new MenuEntry(walletService);

        // Act
        menuEntry.executeChoice(2);

        // Assert
        Entry result = walletService.getEntryList("Wallet").getEntry(0);
        assertEquals(12, result.getAmount());
        assertEquals(1, result.getCategories().size());
        assertEquals("Food", result.getCategories().get(0));
    }
}
