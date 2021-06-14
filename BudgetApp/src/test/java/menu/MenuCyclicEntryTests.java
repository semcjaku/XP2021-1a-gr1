package menu;

import model.Entry;
import model.CyclicEntryPrototype;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.WalletService;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MenuCyclicEntryTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void MenuCyclicEntryShowShouldShowMenuTest() {
        // Arrange
        AbstractMenu menuCyclicEntry = new MenuCyclicEntry(System.in);

        // Act
        String result = menuCyclicEntry.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nMENU CYCLIC ENTRY\n" +
                "1.Add Cyclic Entry repeated at constant interval\n" +
                "2.Add Cyclic Entry repeated monthly\n" +
                "0.Exit", result);
    }

    @Test
    public void MenuCyclicEntryReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("15".getBytes());
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(in);
        String input = "15";

        // Expect
        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuCyclicEntry.validateChoice(input);

    }

    @Test
    public void MenuCyclicEntryReadShouldReturnErrorWhenBelowRangeTest() throws Exception {
        // Arrange
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(System.in);
        String input = "-1";

        // Expect
        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuCyclicEntry.validateChoice(input);
    }

    @Test
    public void MenuCyclicEntryReadShouldReturnErrorWhenNullTest() throws Exception {
        // Arrange
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(System.in);
        String input = null;

        // Expect
        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuCyclicEntry.validateChoice(input);
    }

    @Test
    public void MenuCyclicEntryReadShouldReturnErrorWhenEmptyTest() throws Exception {
        // Arrange
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(System.in);
        String input = "";

        // Expect
        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuCyclicEntry.validateChoice(input);
    }

    @Test
    public void MenuCyclicEntryReadShouldTrimInputTest() throws Exception {
        // Arrange
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(System.in);
        String input = "1  ";

        // Act
        menuCyclicEntry.validateChoice(input);

    }

    @Test
    public void MenuCyclicEntryReadShouldReturnErrorWhenNotANumberTest() throws Exception {
        // Arrange
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(System.in);
        String input = "Hello";

        // Expect
        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input not a number");

        // Act
        menuCyclicEntry.validateChoice(input);
    }

    @Test
    public void MenuCyclicEntryGetCyclicIntervalInDaysInputShowTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("12".getBytes());
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(in);

        // Act
        int result = menuCyclicEntry.getCyclicIntervalInDaysFromUser();

        // Assert
        assertEquals(12, result);
    }

    @Test
    public void MenuCyclicEntryGetCyclicDayOfMonthInputShowTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("12".getBytes());
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(in);

        // Act
        int result = menuCyclicEntry.getCyclicDayOfMonthFromUser();

        // Assert
        assertEquals(12, result);
    }

    @Test
    public void MenuCyclicEntryGetEntryFromUserTest() throws InvalidInputException {
        // Arrange
        int amount = 5;
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator") + "5").getBytes());
        WalletService walletService = new WalletService(new Scanner(in));
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(walletService);

        // Act
        Entry result = menuCyclicEntry.getEntryFromUser();

        // Assert
        assertEquals(new Entry("user1",amount), result);
    }

    @Test
    public void MenuCyclicEntryExecuteChoiceOptionOneTest(){
        // Arrange
        int amount = 3;
        int interval = 5;
        ByteArrayInputStream in = new ByteArrayInputStream(
                ("1" + System.getProperty("line.separator") + amount + System.getProperty("line.separator") + interval).getBytes());
        WalletService walletService = new WalletService(new Scanner(in));
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");

        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(walletService);

        // Act
        menuCyclicEntry.executeChoice(1);

        // Assert
        CyclicEntryPrototype result = walletService.getCyclicPrototypes("wallet").getPrototypes().get(0);
        assertEquals(amount, result.getPrototypeEntry().getAmount());
        assertTrue(result.toString().contains("intervalInDays=" + interval));
    }

    @Test
    public void MenuCyclicEntryExecuteChoiceOptionTwoTest() {
        // Arrange
        int amount = 3;
        int dayOfMonth = 5;
        ByteArrayInputStream in = new ByteArrayInputStream(
                ("1" + System.getProperty("line.separator") + amount + System.getProperty("line.separator") + dayOfMonth).getBytes());
        WalletService walletService = new WalletService(new Scanner(in));
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(walletService);

        // Act
        menuCyclicEntry.executeChoice(2);

        // Assert
        CyclicEntryPrototype result = walletService.getCyclicPrototypes("wallet").getPrototypes().get(0);
        assertEquals(amount, result.getPrototypeEntry().getAmount());
        assertTrue(result.toString().contains("dayOfMonth=" + dayOfMonth));
    }
}
