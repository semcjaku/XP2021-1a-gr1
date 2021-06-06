package menu;

import model.WalletList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.WalletService;

import java.io.SequenceInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MenuPickWalletTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void MenuPickWalletShowShouldShowMenuTest() {
        // Arrange
//        WalletList walletList = new WalletList();
//        walletList.addWallet("Second wallet","user1");
//        AbstractMenu menuPickWallet = new MenuPickWallet(System.in);

        WalletService walletService = new WalletService(new Scanner(System.in));
        walletService.setLoggedInUser("user1");
        walletService.addWallet("Wallet","user1");
        walletService.setCurrentWalletName("Wallet");
        MenuPickWallet menuPickWallet = new MenuPickWallet(walletService);

        // Act
        String result = menuPickWallet.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nPICK WALLET:\n" +
                walletService.getWallets().getOrderedWalletsString(), result);
    }

    @Test
    public void MenuPickWalletReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        WalletService walletService = new WalletService(new Scanner(System.in));
        walletService.setLoggedInUser("user1");
        walletService.addWallet("Wallet","user1");
        walletService.setCurrentWalletName("Wallet");
        MenuPickWallet menuPickWallet = new MenuPickWallet(walletService);
        String input = String.valueOf(menuPickWallet.getMaxInputNumber() + 1);

        // Expect
        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuPickWallet.validateChoice(input);
    }

    @Test
    public void MenuPickWalletReadShouldReturnErrorWhenBelowRangeTest() throws Exception {
        // Arrange
        WalletService walletService = new WalletService(new Scanner(System.in));
        walletService.setLoggedInUser("user1");
        walletService.addWallet("Wallet","user1");
        walletService.setCurrentWalletName("Wallet");
        MenuPickWallet menuPickWallet = new MenuPickWallet(walletService);
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
        WalletService walletService = new WalletService(new Scanner(System.in));
        walletService.setLoggedInUser("user1");
        walletService.addWallet("Wallet","user1");
        walletService.setCurrentWalletName("Wallet");
        MenuPickWallet menuPickWallet = new MenuPickWallet(walletService);
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
        WalletService walletService = new WalletService(new Scanner(System.in));
        walletService.setLoggedInUser("user1");
        walletService.addWallet("Wallet","user1");
        walletService.setCurrentWalletName("Wallet");
        MenuPickWallet menuPickWallet = new MenuPickWallet(walletService);
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
        WalletService walletService = new WalletService(new Scanner(System.in));
        walletService.setLoggedInUser("user1");
        walletService.addWallet("Wallet","user1");
        walletService.setCurrentWalletName("Wallet");
        MenuPickWallet menuPickWallet = new MenuPickWallet(walletService);
        String input = "1  ";

        // Act
        menuPickWallet.validateChoice(input);
    }

    @Test
    public void MenuPickWalletReadShouldReturnErrorWhenNotANumberTest() throws Exception {
        // Arrange
        WalletService walletService = new WalletService(new Scanner(System.in));
        walletService.setLoggedInUser("user1");
        walletService.addWallet("Wallet","user1");
        walletService.setCurrentWalletName("Wallet");
        MenuPickWallet menuPickWallet = new MenuPickWallet(walletService);
        String input = "Hello";

        // Expect
        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input not a number");

        // Act
        menuPickWallet.validateChoice(input);
    }

    @Test
    public void MenuPickWalletExecuteChoiceTest() {
        // Arrange
        WalletService walletService = new WalletService(new Scanner(System.in));
        walletService.addWallet("First wallet", "user1");
        walletService.addWallet("Second wallet", "user1");

        MenuPickWallet menuPickWallet = new MenuPickWallet(walletService);

        // Act
        menuPickWallet.executeChoice(2);

        // Assert
        assertEquals("Second wallet", walletService.getCurrentWalletName());
    }

}
