package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WalletTests {
    @Test
    public void WalletGetEntryListTest() {
        // Arrange
        Wallet wallet = new Wallet("wallet1","user1");

        // Act
        ListManager<Entry> entryList = wallet.getEntryList();

        // Assert
        assertEquals(new EntryList(), entryList);
    }

    @Test
    public void WalletGetCyclicPrototypesListTest() {
        // Arrange
        Wallet wallet = new Wallet("wallet1","user1");

        // Act
        CyclicPrototypeList cyclicPrototypes = wallet.getCyclicPrototypes();

        // Assert
        assertEquals(new CyclicPrototypeList(), cyclicPrototypes);
    }

    @Test
    public void WalletGetCategoryListTest() {
        // Arrange
        Wallet wallet = new Wallet("wallet1","user1");

        // Act
        CategoryList categoryList = wallet.getCategoryList();

        // Assert
        assertEquals(new CategoryList(), categoryList);
    }


    @Test
    public void WalletGivenNameTest() {
        // Arrange
        String name = "wallet1";
        Wallet wallet = new Wallet("wallet1","user1");

        // Act
        String retrievedName = wallet.getName();

        // Assert
        assertEquals(name, retrievedName);
    }

    @Test
    public void WalletSetNameTest() {
        // Arrange
        String name = "wallet2";
        Wallet wallet = new Wallet("wallet1","user1");

        // Act
        wallet.setName(name);
        String retrievedName = wallet.getName();

        // Assert
        assertEquals(name, retrievedName);
    }

    @Test
    public void WalletMarkAsArchivedTest() {
        // Arrange
        Wallet wallet = new Wallet("wallet1","user1");

        // Act
        wallet.markAsArchived();

        // Assert
        assertTrue(wallet.isArchived());
    }

    @Test
    public void WalletAddEntryTest() {
        // Arrange
        Wallet wallet = new Wallet("wallet","user1");
        Entry entry = new Entry(123);

        // Act
        wallet.addEntry(entry);

        // Assert
        assertEquals(wallet.getEntryList().getAt(0).getAmount(),123);
    }

    @Test
    public void WalletAddCategoryTest() {
        // Arrange
        Wallet wallet = new Wallet("wallet","user1");

        // Act
        wallet.addCategory("newCat");

        // Assert
        assertTrue(wallet.getCategoryList().getCategories().contains("newCat"));
    }


}