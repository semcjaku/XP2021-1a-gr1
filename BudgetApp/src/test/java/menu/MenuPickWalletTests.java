package menu;

import model.Wallet;
import model.WalletList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.WalletService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MenuPickWalletTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void MenuPickWalletShowShouldShowMenuTest() {
        // Arrange
        WalletList walletList = new WalletList();
        walletList.addWallet("Second wallet","user1");
        AbstractMenu menuPickWallet = new MenuPickWallet(walletList);

        // Act
        String result = menuPickWallet.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nPICK WALLET:\n" +
                walletList.getOrderedWalletsString(), result);
    }

    @Test
    public void MenuPickWalletReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        MenuPickWallet menuPickWallet = getMenuForTest();
        String input = String.valueOf(menuPickWallet.getMaxInputNumber() + 1);

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuPickWallet.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuPickWalletReadShouldReturnErrorWhenBelowRangeTest() throws Exception {
        // Arrange
        MenuPickWallet menuPickWallet = getMenuForTest();
        String input = String.valueOf(menuPickWallet.getMinInputNumber() - 1);

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuPickWallet.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuPickWalletReadShouldReturnErrorWhenNullTest() throws Exception {
        // Arrange
        MenuPickWallet menuPickWallet = getMenuForTest();
        String input = null;

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuPickWallet.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuPickWalletReadShouldReturnErrorWhenEmptyTest() throws Exception {
        // Arrange
        MenuPickWallet menuPickWallet = getMenuForTest();
        String input = "";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuPickWallet.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuPickWalletReadShouldTrimInputTest() throws Exception {
        // Arrange
        MenuPickWallet menuPickWallet = getMenuForTest();
        String input = "1  ";

        // Act
        menuPickWallet.validateChoice(input);

        // Assert

    }

    @Test
    public void MenuPickWalletReadShouldReturnErrorWhenNotANumberTest() throws Exception {
        // Arrange
        MenuPickWallet menuPickWallet = getMenuForTest();
        String input = "Hello";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input not a number");

        // Act
        menuPickWallet.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuPickWalletExecuteActionsTest() {
        // Arrange
        WalletService walletService = new WalletService();
        walletService.addWallet("First wallet", "user1");
        walletService.addWallet("Second wallet", "user1");

        MenuPickWallet menuPickWallet = new MenuPickWallet(walletService.getWallets());

        // Act
        String chosenWalletName = menuPickWallet.executeActions(2);

        // Assert
        assertEquals("Second wallet", chosenWalletName);
    }

    private MenuPickWallet getMenuForTest() {
        WalletList walletList = new WalletList();
        walletList.addWallet("Second wallet","user1");
        return new MenuPickWallet(walletList);
    }
}
