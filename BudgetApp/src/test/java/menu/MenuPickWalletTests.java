package menu;

import model.Wallet;
import model.WalletList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MenuPickWalletTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void MenuPickWalletShowShouldShowMenuTest() {
        // Arrange
        WalletList walletList = new WalletList();
        walletList.addWallet(new Wallet("Second wallet"));
        AbstractMenu menuPickWallet = new MenuPickWallet(walletList);

        // Act
        String result = menuPickWallet.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nPICK WALLET:\n" +
                walletList.getOrderedWalletsString() +
                "\nPlease select 1-" + walletList.getLength() + "!", result);
    }

    @Test
    public void MenuPickWalletReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        MenuPickWallet menuPickWallet = getMenuForTest();
        String input = String.valueOf(menuPickWallet.getMaxInputNumber() + 1);

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuPickWallet.read(input);

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
        menuPickWallet.read(input);

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
        menuPickWallet.read(input);

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
        menuPickWallet.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuPickWalletReadShouldTrimInputTest() throws Exception {
        // Arrange
        MenuPickWallet menuPickWallet = getMenuForTest();
        String input = "1  ";

        // Act
        int result = menuPickWallet.read(input);

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void MenuPickWalletReadShouldReturnErrorWhenNotANumberTest() throws Exception {
        // Arrange
        MenuPickWallet menuPickWallet = getMenuForTest();
        String input = "Hello";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input not a number");

        // Act
        menuPickWallet.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuPickWalletExecuteActionsTest() {
        // Arrange
        WalletList walletList = new WalletList();
        Wallet secondWallet = new Wallet("Second wallet");
        walletList.addWallet(secondWallet);
        MenuPickWallet menuPickWallet = new MenuPickWallet(walletList);

        // Act
        Wallet chosenWallet = menuPickWallet.executeActions(2);

        // Assert
        assertEquals(secondWallet, chosenWallet);
    }

    private MenuPickWallet getMenuForTest() {
        WalletList walletList = new WalletList();
        walletList.addWallet(new Wallet("Second wallet"));
        return new MenuPickWallet(walletList);
    }
}
