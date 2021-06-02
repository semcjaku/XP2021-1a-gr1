package service;

import model.Entry;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WalletServiceTest {
    @Test
    public void WalletServiceConstructorShouldCreateEmptyListTest() {
        // Arrange

        // Act
        WalletService walletService = new WalletService();

        // Assert
        assertNotNull(walletService.getWallets());
        assertEquals(0, walletService.getWallets().getLength());
    }

    @Test
    public void WalletServiceRemoveEntryShouldRemoveEntryTest() {
        //Arrange
        WalletService walletService = new WalletService();
        walletService.addWallet("wallet","user1");
        Entry entry = new Entry(123);
        walletService.addEntry("wallet",entry);

//        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator") + newWalletName).getBytes());
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        Scanner scanner = new Scanner(in);

        // Act
        walletService.removeEntry("wallet",scanner);

        // Assert
        assertEquals(walletService.getEntryList("wallet").length(),0);
    }

    @Test
    public void WalletServiceRemoveEntryWrongInputShouldNotRemoveEntryTest() {
        //Arrange
        WalletService walletService = new WalletService();
        walletService.addWallet("wallet","user1");
        Entry entry = new Entry(123);
        walletService.addEntry("wallet",entry);

//        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator") + newWalletName).getBytes());
        ByteArrayInputStream in = new ByteArrayInputStream(("10"+ System.getProperty("line.separator") +"1").getBytes());
        Scanner scanner = new Scanner(in);

        // Act
        walletService.removeEntry("wallet",scanner);

        // Assert
        assertEquals(1,walletService.getEntryList("wallet").length());
    }

    @Test
    public void WalletServiceModifyEntryChangeAmounShouldModifyEntryTest() {
        //Arrange
        WalletService walletService = new WalletService();
        walletService.addWallet("wallet","user1");
        Entry entry = new Entry(123);
        walletService.addEntry("wallet",entry);

        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                                                            + "1" + System.getProperty("line.separator")    // Change amount
                                                            + "12" + System.getProperty("line.separator") + "0") // new amount // exit
                                                            .getBytes());
        Scanner scanner = new Scanner(in);

        // Act
        walletService.modifyEntry("wallet",scanner);

        // Assert
        assertEquals(entry.getAmount(),12);
    }

    @Test
    public void WalletServiceModifyEntryChangeCategoriesShouldModifyEntryTest() {
        //Arrange
        WalletService walletService = new WalletService();
        walletService.addWallet("wallet","user1");
        Entry entry = new Entry(123);
        walletService.addEntry("wallet",entry);

        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                                                            + "2" + System.getProperty("line.separator")    // Change categories
                                                            + "food,drinks" + System.getProperty("line.separator") + "0") // new categories // exit
                                                            .getBytes());
        Scanner scanner = new Scanner(in);

        // Act
        walletService.modifyEntry("wallet",scanner);

        // Assert
        assertEquals(entry.getCategories().toString(),"[food, drinks]");

        // Arrange
        in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                + "2" + System.getProperty("line.separator")    // Change categories
                + "alko" + System.getProperty("line.separator") + "0") // changed categories // exit
                .getBytes());
        scanner = new Scanner(in);

        // Act
        walletService.modifyEntry("wallet",scanner);

        // Assert
        assertEquals(entry.getCategories().toString(),"[alko]");
    }

    @Test
    public void WalletServiceModifyEntryAddCategoriesShouldModifyEntryTest() {
        //Arrange
        WalletService walletService = new WalletService();
        walletService.addWallet("wallet","user1");
        Entry entry = new Entry(123);
        walletService.addEntry("wallet",entry);

        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                                                            + "3" + System.getProperty("line.separator")    // Add categories
                                                            + "fun" + System.getProperty("line.separator") + "0") // new categories // exit
                                                            .getBytes());
        Scanner scanner = new Scanner(in);

        // Act
        walletService.modifyEntry("wallet",scanner);

        // Assert
        assertEquals(entry.getCategories().toString(),"[fun]");

        // Arrange
        in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                + "3" + System.getProperty("line.separator")    // add categories
                + "alko" + System.getProperty("line.separator") + "0") // added categories // exit
                .getBytes());
        scanner = new Scanner(in);

        // Act
        walletService.modifyEntry("wallet",scanner);

        // Assert
        assertEquals(entry.getCategories().toString(),"[fun, alko]");
    }

    @Test
    public void WalletServiceModifyEntryAddCategoriesRepetedCategoryShouldNotModifyEntryTest() {
        //Arrange
        WalletService walletService = new WalletService();
        walletService.addWallet("wallet","user1");
        Entry entry = new Entry(123);
        walletService.addEntry("wallet",entry);

        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                                                            + "3" + System.getProperty("line.separator")    // Add categories
                                                            + "fun" + System.getProperty("line.separator") + "0") // new categories // exit
                                                            .getBytes());
        Scanner scanner = new Scanner(in);

        // Act
        walletService.modifyEntry("wallet",scanner);

        // Assert
        assertEquals(entry.getCategories().toString(),"[fun]");

        // Arrange
        in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                + "3" + System.getProperty("line.separator")    // add categories
                + "fun" + System.getProperty("line.separator") + "0") // added categories // exit
                .getBytes());
        scanner = new Scanner(in);

        // Act
        walletService.modifyEntry("wallet",scanner);

        // Assert
        assertEquals(entry.getCategories().toString(),"[fun]");
    }

    @Test
    public void WalletServiceModifyEntryRemoveCategoriesShouldModifyEntryTest() {
        //Arrange
        WalletService walletService = new WalletService();
        walletService.addWallet("wallet","user1");
        Entry entry = new Entry(123);
        walletService.addEntry("wallet",entry);

        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                + "3" + System.getProperty("line.separator")    // Add categories
                + "fun" + System.getProperty("line.separator") + "0") // new categories // exit
                .getBytes());
        Scanner scanner = new Scanner(in);

        walletService.modifyEntry("wallet",scanner);


        in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                + "4" + System.getProperty("line.separator")    // remove categories
                + "fun" + System.getProperty("line.separator") + "0") // remoevd categories // exit
                .getBytes());
        scanner = new Scanner(in);

        // Act
        walletService.modifyEntry("wallet",scanner);

        // Assert
        assertEquals(entry.getCategories().toString(),"[]");
    }


    @Test
    public void WalletServiceModifyEntryRemoveCategoriesInvalidCategoryShouldNotModifyEntryTest() {
        //Arrange
        WalletService walletService = new WalletService();
        walletService.addWallet("wallet","user1");
        Entry entry = new Entry(123);
        walletService.addEntry("wallet",entry);

        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                + "3" + System.getProperty("line.separator")    // Add categories
                + "fun" + System.getProperty("line.separator") + "0") // new categories // exit
                .getBytes());
        Scanner scanner = new Scanner(in);

        walletService.modifyEntry("wallet",scanner);


        in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                + "4" + System.getProperty("line.separator")    // remove categories
                + "bocian" + System.getProperty("line.separator") + "0") // remoevd categories // exit
                .getBytes());
        scanner = new Scanner(in);

        // Act
        walletService.modifyEntry("wallet",scanner);

        // Assert
        assertEquals(entry.getCategories().toString(),"[fun]");
    }

}
