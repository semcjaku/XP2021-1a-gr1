package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WalletTests {
    @Test
    public void WalletGetEntryListTest() {
        // Arrange
        Wallet wallet = new Wallet();

        // Act
        EntryList entryList = wallet.getEntryList();

        // Assert
        assertEquals(new EntryList(), entryList);
    }

    @Test
    public void WalletGetCyclicPrototypesListTest() {
        // Arrange
        Wallet wallet = new Wallet();

        // Act
        CyclicPrototypeList cyclicPrototypes = wallet.getCyclicPrototypes();

        // Assert
        assertEquals(new CyclicPrototypeList(), cyclicPrototypes);
    }

    @Test
    public void WalletGetCategoryListTest() {
        // Arrange
        Wallet wallet = new Wallet();

        // Act
        CategoryList categoryList = wallet.getCategoryList();

        // Assert
        assertEquals(new CategoryList(), categoryList);
    }

    @Test
    public void WalletDefaultNameTest() {
        // Arrange
        Wallet wallet = new Wallet();

        // Act
        String name = wallet.getName();

        // Assert
        assertEquals("A wallet", name);
    }

    @Test
    public void WalletGivenNameTest() {
        // Arrange
        String name = "non-default name";
        Wallet wallet = new Wallet(name);

        // Act
        String retrievedName = wallet.getName();

        // Assert
        assertEquals(name, retrievedName);
    }

    @Test
    public void WalletSetNameTest() {
        // Arrange
        String name = "non-default name";
        Wallet wallet = new Wallet();

        // Act
        wallet.setName(name);
        String retrievedName = wallet.getName();

        // Assert
        assertEquals(name, retrievedName);
    }

    @Test
    public void WalletMarkAsArchivedTest() {
        // Arrange
        Wallet wallet = new Wallet();

        // Act
        wallet.markAsArchived();

        // Assert
        assertTrue(wallet.isArchived());
    }
}