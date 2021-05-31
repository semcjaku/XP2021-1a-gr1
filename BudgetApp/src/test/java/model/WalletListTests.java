package model;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WalletListTests {
    private final String defaultWalletName = "Default wallet";

    @Test
    public void WalletListGetWalletsTest() {
        // Arrange
        WalletList walletList = new WalletList();

        // Act
        List<Wallet> wallets = walletList.getWallets();

        // Assert
        assertEquals(List.of(new Wallet(defaultWalletName)), wallets);
    }

    @Test
    public void WalletListGetWalletsShouldIgnoreArchivedTest() {
        // Arrange
        WalletList walletList = new WalletList();
        Wallet secondWallet = new Wallet("Some name");
        secondWallet.markAsArchived();

        // Act
        List<Wallet> wallets = walletList.getWallets();

        // Assert
        assertEquals(List.of(new Wallet(defaultWalletName)), wallets);
    }

    @Test
    public void WalletListGetLengthTest() {
        // Arrange
        WalletList walletList = new WalletList();
        Wallet secondWallet = new Wallet("Some name");
        walletList.addWallet(secondWallet);

        // Act
        int returnedLength = walletList.getLength();

        // Assert
        assertEquals(walletList.getWallets().size(), returnedLength);
    }


    @Test
    public void WalletListAddWalletTest() {
        // Arrange
        WalletList walletList = new WalletList();
        Wallet secondWallet = new Wallet("Some name");

        List<Wallet> referenceWalletList = new LinkedList<>();
        referenceWalletList.add(new Wallet(defaultWalletName));
        referenceWalletList.add(secondWallet);

        // Act
        walletList.addWallet(secondWallet);
        List<Wallet> wallets = walletList.getWallets();

        // Assert
        assertEquals(referenceWalletList, wallets);
    }

    @Test
    public void WalletListGetWalletNames() {
        // Arrange
        WalletList walletList = new WalletList();
        walletList.addWallet(new Wallet("Some name"));

        // Act
        String names = walletList.getOrderedWalletsString();

        // Assert
        assertEquals("1. Default wallet\n2. Some name", names);
    }
}
