package menu;

import org.junit.Test;
import service.WalletService;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MenuPickOrCreateWalletTests {
    @Test
    public void MenuPickOrCreateWalletShowShouldShowMenuTest() {
        // Arrange
        AbstractMenu menuPickOrCreateWallet = new MenuPickOrCreateWallet(System.in);

        // Act
        String result = menuPickOrCreateWallet.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nMENU PICK OR CREATE WALLET\n" +
                "1.Create wallet\n" +
                "2.Pick wallet\n" +
                "0.Exit", result);
    }

    @Test
    public void MenuPickOrCreateWalletExecuteChoiceOptionOneCreateWalletShouldCreateWalletTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("New Wallet").getBytes());
        WalletService walletService = new WalletService(new Scanner(in));
        walletService.setLoggedInUser("user1");
        MenuPickOrCreateWallet menuPickOrCreateWallet = new MenuPickOrCreateWallet(walletService);

        // Act
        menuPickOrCreateWallet.executeChoice(1);

        // Assert
        assertTrue(walletService.checkIfWalletExists("New Wallet"));
    }

    @Test
    public void MenuPickOrCreateWalletExecuteChoiceOptionOneCreateWalletWhenNameAlreadyExistsShouldCreateWalletTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("NewWallet" + System.getProperty("line.separator")
                + "NewWallet" + System.getProperty("line.separator")
                + "NewerWallet").getBytes());
        WalletService walletService = new WalletService(new Scanner(in));
        MenuPickOrCreateWallet menuPickOrCreateWallet = new MenuPickOrCreateWallet(walletService);

        // Act
        menuPickOrCreateWallet.executeChoice(1);
        menuPickOrCreateWallet.executeChoice(1);

        // Assert
        assertTrue(walletService.checkIfWalletExists("NewerWallet"));
    }

    @Test
    public void MenuPickOrCreateWalletExecuteChoiceOptionTwoPickWalletShouldPickWalletTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("firstWallet" + System.getProperty("line.separator")
                + "secondWallet" + System.getProperty("line.separator")
                + "1").getBytes());
        WalletService walletService = new WalletService(new Scanner(in));
        walletService.setLoggedInUser("user1");
        MenuPickOrCreateWallet menuPickOrCreateWallet = new MenuPickOrCreateWallet(walletService);

        // Act
        menuPickOrCreateWallet.executeChoice(1); // create first wallet
        menuPickOrCreateWallet.executeChoice(1); // create second wallet
        menuPickOrCreateWallet.executeChoice(2); // pick first wallet

        // Assert
        assertEquals(walletService.getCurrentWalletName(),"firstWallet");
    }

    @Test
    public void MenuPickOrCreateWalletGetWalletNameFromUserShouldReturnNameTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.getProperty("line.separator")
                + "\t" + System.getProperty("line.separator")
                + "newName").getBytes());
        MenuPickOrCreateWallet menuPickOrCreateWallet = new MenuPickOrCreateWallet(in);

        // Act
        String newWalletName = menuPickOrCreateWallet.getWalletNameFromUser();

        // Assert
        assertEquals("newName",newWalletName);
    }

}
