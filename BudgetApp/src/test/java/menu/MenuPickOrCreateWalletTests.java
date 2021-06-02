package menu;

import org.junit.Test;
import service.WalletService;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class MenuPickOrCreateWalletTests {
    @Test
    public void MenuPickOrCreateWalletShowShouldShowMenuTest() {
        // Arrange
        AbstractMenu menuPickOrCreateWallet = new MenuPickOrCreateWallet();

        // Act
        String result = menuPickOrCreateWallet.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nMENU PICK OR CREATE WALLET\n" +
                "1.Create wallet\n" +
                "2.Pick Wallet", result);
    }

    @Test
    public void MenuPickOrCreateWalletExecuteActionsOneCreateWalletShouldCreateWalletTest() {
        // Arrange
        WalletService walletService = new WalletService();
        ByteArrayInputStream in = new ByteArrayInputStream(("NewWallet").getBytes());
        MenuPickOrCreateWallet menuPickOrCreateWallet = new MenuPickOrCreateWallet(in);

        // Act
        String walletName = menuPickOrCreateWallet.executeActions(1,"user1",walletService);

        // Assert
        assertTrue(walletService.checkIfWalletExists(walletName));
    }

    @Test
    public void MenuPickOrCreateWalletExecuteActionsOneCreateWalletWhenNameAlreadyExistsShouldCreateWalletTest() {
        // Arrange
        WalletService walletService = new WalletService();
        ByteArrayInputStream in = new ByteArrayInputStream(("NewWallet" + System.getProperty("line.separator")
                                                            + "NewWallet" + System.getProperty("line.separator")
                                                            +"NewerWallet").getBytes());
        MenuPickOrCreateWallet menuPickOrCreateWallet = new MenuPickOrCreateWallet(in);

        // Act
        String walletName = menuPickOrCreateWallet.executeActions(1,"user1",walletService);
         walletName = menuPickOrCreateWallet.executeActions(1,"user1",walletService);

        // Assert
        assertTrue(walletService.checkIfWalletExists(walletName));
    }

    @Test
    public void MenuPickOrCreateWalletExecuteActionsTwoPickWalletShouldPickWalletTest() {
        // Arrange
        WalletService walletService = new WalletService();
        ByteArrayInputStream in = new ByteArrayInputStream(("4" + System.getProperty("line.separator") // walletnumber
                                                            + "5" + System.getProperty("line.separator")
                                                            + "1").getBytes());
        MenuPickOrCreateWallet menuPickOrCreateWallet = new MenuPickOrCreateWallet(in);

        // Act
        String walletName = menuPickOrCreateWallet.executeActions(1,"user1",walletService);
         walletName = menuPickOrCreateWallet.executeActions(2,"user1",walletService);

        // Assert
        assertTrue(walletService.checkIfWalletExists(walletName));
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
