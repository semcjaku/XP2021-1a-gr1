package model;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WalletListTests {

    @Test
    public void WalletListGetWalletsTest() {
        // Arrange
        WalletList walletList = new WalletList();
        List<Wallet> wallets2 = new LinkedList<>();

        // Act
        List<Wallet> wallets = walletList.getWallets();

        // Assert
        assertEquals(wallets2 , wallets);
    }

    @Test
    public void WalletListGetWalletsShouldIgnoreArchivedTest() {
        // Arrange
        WalletList walletList = new WalletList();
        walletList.addWallet("wallet1","user1");
        walletList.addWallet("wallet2","user1");
        walletList.archiveWallet("wallet1");

        // Act
        List<Wallet> wallets = walletList.getWallets();

        // Assert
        assertEquals(wallets.size(),1);
        assertEquals(wallets.get(0).getName(),"wallet2" );
    }

    @Test
    public void WalletListGetLengthTest() {
        // Arrange
        WalletList walletList = new WalletList();
        walletList.addWallet("wallet1","user1");

        // Act
        int returnedLength = walletList.getLength();

        // Assert
        assertEquals(walletList.getWallets().size(), returnedLength);
    }


    @Test
    public void WalletListAddWalletTest() {
        // Arrange
        WalletList walletList = new WalletList();
        walletList.addWallet("wallet1","user1");
        int sizeBeforeAdd = walletList.getLength();

        // Act
        walletList.addWallet("wallet2","user1");

        // Assert
        assertEquals(sizeBeforeAdd, walletList.getLength()-1);
        assertEquals("wallet2",walletList.getWalletByName("wallet2").getName());
    }

    @Test
    public void WalletListGetWalletNamesTest() {
        // Arrange
        WalletList walletList = new WalletList();
        walletList.addWallet("wallet1","user1");
        walletList.addWallet("wallet2","user1");

        // Act
        String names = walletList.getOrderedWalletsString();

        // Assert
        assertEquals("1. wallet1\n2. wallet2", names);
    }

    @Test
    public void WalletListGetUserWalletsTest() {
        // Arrange
        WalletList walletList = new WalletList();
        walletList.addWallet("wallet1","user1");
        walletList.addWallet("wallet2","user2");
        walletList.addWallet("wallet3","user1");
        walletList.addWallet("wallet4","user3");
        walletList.addWallet("wallet5","user3");

        // Act
        List<Wallet> walletsUser1 = walletList.getUserWallets("user1");
        List<Wallet> walletsUser2 = walletList.getUserWallets("user2");
        List<Wallet> walletsUser3 = walletList.getUserWallets("user3");


        // Assert
        assertEquals(walletsUser1.size(), 2);
        assertEquals(walletsUser2.size(), 1);
        assertEquals(walletsUser3.size(), 2);
        assertEquals(walletsUser1.get(0).getName(),"wallet1");
        assertEquals(walletsUser1.get(1).getName(),"wallet3");
        assertEquals(walletsUser2.get(0).getName(),"wallet2");
        assertEquals(walletsUser3.get(0).getName(),"wallet4");
        assertEquals(walletsUser3.get(1).getName(),"wallet5");
    }

    @Test
    public void WalletListCheckIfWalletExistsTest() {
        // Arrange
        WalletList walletList = new WalletList();
        walletList.addWallet("walletExist","user1");

        // Act
        boolean exists1 = walletList.checkIfWalletExists("walletExist");
        boolean exists2 = walletList.checkIfWalletExists("walletNotExist");

        // Assert
        assertEquals(exists1, true);
        assertEquals(exists2, false);
    }

    @Test
    public void WalletListAddEntryTest() {
        // Arrange
        WalletList walletList = new WalletList();
        walletList.addWallet("wallet","user1");
        Entry entry = new Entry(123);
        Entry entry2 = new Entry(10);

        // Assert
        assertEquals(walletList.getEntryList("wallet").length(), 0);

        // Act
        walletList.addEntry("wallet",entry);

        // Assert
        assertEquals(walletList.getEntryList("wallet").length(), 1);

        // Act
        walletList.addEntry("wallet",entry2);

        // Assert
        assertEquals(walletList.getEntryList("wallet").length(), 2);
    }
    @Test
    public void WalletListAddCategoryTest() {
        // Arrange
        WalletList walletList = new WalletList();
        walletList.addWallet("wallet","user1");

        // Act
        walletList.addCategory("wallet","newCat");

        // Assert
        List<String> categories = walletList.getCategoryList("wallet").getCategories();
        assertEquals(true, categories.contains("newCat"));
    }

    @Test
    public void WalletListHasCategoryTest() {
        // Arrange
        WalletList walletList = new WalletList();
        walletList.addWallet("wallet","user1");
        walletList.addCategory("wallet","newCat");

        // Act
        boolean hasCat1 = walletList.hasCategory("wallet","newCat");
        boolean hasCat2 = walletList.hasCategory("wallet","noCat");

        // Assert
        assertEquals(hasCat1, true);
        assertEquals(hasCat2, false);
    }

    @Test
    public void WalletListGetCategoryListTest() {
        // Arrange
        WalletList walletList = new WalletList();
        walletList.addWallet("wallet","user1");
        walletList.addCategory("wallet","newCat");
        walletList.addCategory("wallet","newCat2");
//        List<String> categoryListRef = List.of("newCat","newCat2");

        // Act
        CategoryList categoryList = walletList.getCategoryList("wallet");

        // Assert
        assertEquals(categoryList.getCategories().containsAll(List.of("newCat","newCat2")) , true);
        assertEquals(categoryList.getCategories().size() >= 2 , true);

    }

    @Test
    public void WalletListGetUserWalletsNamesTest() {
        // Arrange
        List<String> walletNamesRef = List.of("wallet1","wallet2");

        WalletList walletList = new WalletList();
        walletList.addWallet("wallet1","user1");
        walletList.addWallet("wallet2","user1");
        walletList.addWallet("wallet3","user2");

        // Act
        List<String> walletsNames = walletList.getUserWalletsNames("user1");

        // Assert
        assertEquals(walletNamesRef.toString(), walletsNames.toString());
    }

    @Test
    public void WalletListGetWalletByNameTest() {
        // Arrange
        WalletList walletList = new WalletList();
        walletList.addWallet("wallet1","user1");

        // Act
        Wallet wallet = walletList.getWalletByName("wallet1");

        // Assert
        assertEquals(wallet.getName(), "wallet1");
    }

    @Test
    public void WalletListGetEntryListTest() {
        // Arrange
        WalletList walletList = new WalletList();
        walletList.addWallet("wallet","user1");
        Entry entry = new Entry(123);
        Entry entry2 = new Entry(10);
        walletList.addEntry("wallet",entry);
        walletList.addEntry("wallet",entry2);

        // Act
        EntryList entryList = walletList.getEntryList("wallet");

        // Assert
        assertEquals(entryList.length(), 2);
        assertEquals(entryList.getEntry(0).getAmount(), 123);
        assertEquals(entryList.getEntry(1).getAmount(), 10);
    }

    @Test
    public void WalletListRenameTest() {
        // Arrange
        WalletList walletList = new WalletList();
        walletList.addWallet("old wallet","user1");

        // Act
        walletList.rename("old wallet","new wallet");

        // Assert
        assertEquals(walletList.getUserWalletsNames("user1").contains("old wallet"), false);
        assertEquals(walletList.getUserWalletsNames("user1").contains("new wallet"), true);

    }
}
