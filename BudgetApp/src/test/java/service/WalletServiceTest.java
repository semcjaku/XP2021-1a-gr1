package service;

import model.Entry;
import model.IntervalCyclicEntryPrototype;
import model.MonthlyCyclicEntryPrototype;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class WalletServiceTest {
    @Test
    public void WalletServiceConstructorShouldCreateEmptyListTest() {
        // Arrange

        // Act
        WalletService walletService = new WalletService(new Scanner(System.in));

        // Assert
        assertNotNull(walletService.getWallets());
        assertEquals(0, walletService.getWallets().getLength());
    }

    @Test
    public void WalletServiceRemoveEntryShouldRemoveEntryTest() {
        //Arrange
//        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator") + newWalletName).getBytes());
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        WalletService walletService = new WalletService(new Scanner(in));
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");
        Entry entry = new Entry(123);
        walletService.addEntry("wallet",entry);

        Scanner scanner = new Scanner(in);

        // Act
        walletService.hndRemoveEntry();

        // Assert
        assertEquals(walletService.getEntryList("wallet").getLength(),0);
    }

    @Test
    public void WalletServiceRemoveEntryWrongInputShouldNotRemoveEntryTest() {
        //Arrange
//        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator") + newWalletName).getBytes());
        ByteArrayInputStream in = new ByteArrayInputStream(("10"+ System.getProperty("line.separator") +"1").getBytes());
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setCurrentWalletName("wallet");
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        Entry entry = new Entry(123);
        walletService.addEntry("wallet",entry);


        // Act
        walletService.hndRemoveEntry();

        // Assert
        assertEquals(1,walletService.getEntryList("wallet").getLength());
    }

    @Test
    public void WalletServiceModifyEntryChangeAmounShouldModifyEntryTest() {
        //Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                + "1" + System.getProperty("line.separator")    // Change amount
                + "12" + System.getProperty("line.separator") + "0") // new amount // exit
                .getBytes());
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setCurrentWalletName("wallet");
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        Entry entry = new Entry(123);
        walletService.addEntry("wallet",entry);


        // Act
        walletService.hndModifyEntry();

        // Assert
        assertEquals(entry.getAmount(),12);
    }

    @Test
    public void WalletServiceModifyEntryChangeCategoriesShouldModifyEntryTest() {
        //Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                + "2" + System.getProperty("line.separator")    // Change categories
                + "food,drinks" + System.getProperty("line.separator") + "0") // new categories // exit
                .getBytes());
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setCurrentWalletName("wallet");
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        Entry entry = new Entry(123);
        walletService.addEntry("wallet",entry);


        // Act
        walletService.hndModifyEntry();

        // Assert
        assertEquals(entry.getCategories().toString(),"[food, drinks]");

        // Arrange
        in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                + "2" + System.getProperty("line.separator")    // Change categories
                + "alko" + System.getProperty("line.separator") + "0") // changed categories // exit
                .getBytes());
        scanner = new Scanner(in);

        walletService.setScanner(scanner);

        // Act
        walletService.hndModifyEntry();

        // Assert
        assertEquals(entry.getCategories().toString(),"[alko]");
    }

    @Test
    public void WalletServiceModifyEntryAddCategoriesShouldModifyEntryTest() {
        //Arrange

        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                + "3" + System.getProperty("line.separator")    // Add categories
                + "fun" + System.getProperty("line.separator") + "0") // new categories // exit
                .getBytes());
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setCurrentWalletName("wallet");
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        Entry entry = new Entry(123);
        walletService.addEntry("wallet",entry);

        // Act
        walletService.hndModifyEntry();

        // Assert
        assertEquals(entry.getCategories().toString(),"[fun]");

        // Arrange
        in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                + "3" + System.getProperty("line.separator")    // add categories
                + "alko" + System.getProperty("line.separator") + "0") // added categories // exit
                .getBytes());
        scanner = new Scanner(in);
        walletService.setScanner(scanner);

        // Act
        walletService.hndModifyEntry();

        // Assert
        assertEquals(entry.getCategories().toString(),"[fun, alko]");
    }

    @Test
    public void WalletServiceModifyEntryAddCategoriesRepeatedCategoryShouldNotModifyEntryTest() {
        //Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                + "3" + System.getProperty("line.separator")    // Add categories
                + "fun" + System.getProperty("line.separator") + "0") // new categories // exit
                .getBytes());
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");
        Entry entry = new Entry(123);
        walletService.addEntry("wallet",entry);


        // Act
        walletService.hndModifyEntry();

        // Assert
        assertEquals(entry.getCategories().toString(),"[fun]");

        // Arrange
        in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                + "3" + System.getProperty("line.separator")    // add categories
                + "fun" + System.getProperty("line.separator") + "0") // added categories // exit
                .getBytes());
        scanner = new Scanner(in);
        walletService.setScanner(scanner);


        // Act
        walletService.hndModifyEntry();

        // Assert
        assertEquals(entry.getCategories().toString(),"[fun]");
    }

    @Test
    public void WalletServiceModifyEntryRemoveCategoriesShouldModifyEntryTest() {
        //Arrange
        ByteArrayInputStream in = new ByteArrayInputStream((
                  "1" + System.getProperty("line.separator")      // Entry no.1
                + "3" + System.getProperty("line.separator")    // Add categories
                + "fun" + System.getProperty("line.separator")  // new categories
                + "0" + System.getProperty("line.separator")    // exit
                + "1" + System.getProperty("line.separator")    // Entry no.1
                + "4" + System.getProperty("line.separator")    // remove categories
                + "fun" + System.getProperty("line.separator")  // name to remove
                + "0"
        )
                .getBytes());
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");
        Entry entry = new Entry(123);
        walletService.addEntry("wallet",entry);

        // Act
        walletService.hndModifyEntry();
        walletService.hndModifyEntry();

        // Assert
        assertEquals(entry.getCategories().toString(),"[]");
    }


    @Test
    public void WalletServiceModifyEntryRemoveCategoriesInvalidCategoryShouldNotModifyEntryTest() {
        //Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry no.1
                + "3" + System.getProperty("line.separator")    // Add categories
                + "fun" + System.getProperty("line.separator")
                + "4" + System.getProperty("line.separator")    // remove categories
                + "bocian" + System.getProperty("line.separator") +"0") // remoevd categories
                .getBytes());
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");
        Entry entry = new Entry(123);
        walletService.addEntry("wallet",entry);

        // Act
        walletService.hndModifyEntry();

        // Assert
        assertEquals(walletService.getEntryList("wallet").getAt(0).getCategories().toString(),"[fun]");
    }

    @Test
    public void WalletServiceModifyEntryRemoveCategoriesInvalidEntryChoiceShouldComeBackToMainMenuTest() {
        //Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("2" + System.getProperty("line.separator")      // Entry no.1
                + "6" + System.getProperty("line.separator")) .getBytes());    // main menu modify entry
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");
        Entry entry = new Entry(123, List.of("fun"));
        walletService.addEntry("wallet",entry);

        // Act
        walletService.hndModifyEntry();

        // Assert
        assertEquals(walletService.getEntryList("wallet").getAt(0).getCategories().toString(),"[fun]");
    }
    @Test
    public void WalletServiceHndModifyEntryRemoveCategoriesInvalidEntryChoiceNotANumberShouldComeBackToMainMenuTest() {
        //Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("a" + System.getProperty("line.separator")      // Entry no.1
                + "6" + System.getProperty("line.separator")) .getBytes());    // main menu modify entry
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");
        Entry entry = new Entry(123, List.of("fun"));
        walletService.addEntry("wallet",entry);

        // Act
        walletService.hndModifyEntry();

        // Assert
        assertEquals(walletService.getEntryList("wallet").getAt(0).getCategories().toString(),"[fun]");
    }

    @Test
    public void WalletServiceHndAddEntryWithAmountShouldAddEntryTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Entry menu - Add entry
                + "6" + System.getProperty("line.separator")).getBytes());    // amount
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");

        // Act
        walletService.hndAddEntry();

        // Assert
        assertEquals(walletService.getEntryList("wallet").getAt(0).getAmount(),6);
    }

    @Test
    public void WalletServiceHndAddEntryWithAmountAndOneCategoryShouldAddEntryTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("2" + System.getProperty("line.separator")      // Entry menu - Add entry with amount and category list
                                                          + "6" + System.getProperty("line.separator")      // amount
                                                          + "fun" + System.getProperty("line.separator")).getBytes());    // category
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");

        // Act
        walletService.hndAddEntry();

        // Assert
        assertEquals(walletService.getEntryList("wallet").getAt(0).getAmount(),6);
        assertEquals(walletService.getEntryList("wallet").getAt(0).getCategories().toString(),"[fun]");
    }

    @Test
    public void WalletServiceHndAddEntryWithAmountAndManyCategoriesShouldAddEntryTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("2" + System.getProperty("line.separator")      // Entry menu - Add entry with amount and category list
                                                          + "6" + System.getProperty("line.separator")      // amount
                                                          + "fun;food;folks" + System.getProperty("line.separator")).getBytes());    // category
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");

        // Act
        walletService.hndAddEntry();

        // Assert
        assertEquals(walletService.getEntryList("wallet").getAt(0).getAmount(),6);
        assertEquals(walletService.getEntryList("wallet").getAt(0).getCategories().toString(),"[fun, food, folks]");
    }

    @Test
    public void WalletServiceHndAddCyclicEntryWithAmountAndIntervalShouldAddEntryTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Cyclic Entry menu - repeated at const
                + "1" + System.getProperty("line.separator")                    // menu entry - add entry with amount
                + "10" + System.getProperty("line.separator")                   // amount
                + "5" + System.getProperty("line.separator")).getBytes());      // cyclic intervals in days
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");

        // Act
        walletService.hndAddCyclicEntry();

        // Assert
        assertEquals(walletService.getCyclicPrototypes("wallet").getAt(0).getPrototypeEntry().getAmount(),10);
        assertEquals(((IntervalCyclicEntryPrototype)walletService.getCyclicPrototypes("wallet").asList().get(0)).getCyclicParameter() ,5);
    }

    @Test
    public void WalletServiceHndAddCyclicEntryWithAmountAndMonthDayShouldAddEntryTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("2" + System.getProperty("line.separator")      // Cyclic Entry menu - repeated monthly
                + "1" + System.getProperty("line.separator")                    // menu entry - add entry with amount
                + "10" + System.getProperty("line.separator")                   // amount
                + "5" + System.getProperty("line.separator")).getBytes());      // cyclic intervals in days
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");

        // Act
        walletService.hndAddCyclicEntry();

        // Assert
        assertEquals(walletService.getCyclicPrototypes("wallet").getAt(0).getPrototypeEntry().getAmount(),10);
        assertEquals(((MonthlyCyclicEntryPrototype)walletService.getCyclicPrototypes("wallet").asList().get(0)).getCyclicParameter() ,5);
    }

    @Test
    public void WalletServiceHndRemoveCyclicEntryShouldRemoveEntryTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("2" + System.getProperty("line.separator")      // Cyclic Entry menu - repeated monthly
                + "1" + System.getProperty("line.separator")                    // menu entry - add entry with amount
                + "10" + System.getProperty("line.separator")                   // amount
                + "5" + System.getProperty("line.separator")                    // month day
                + "1").getBytes());      // cyclic intervals in days            // cyclic entry to be removed
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");

        // Act
        walletService.hndAddCyclicEntry();
        walletService.hndRemoveCyclicEntry();

        // Assert
        assertEquals(walletService.getCyclicPrototypes("wallet").getLength(),0);
//        assertEquals(((MonthlyCyclicEntryPrototype)walletService.getCyclicPrototypes("wallet").getPrototypes().get(0)).getCyclicParameter() ,5);
    }

    @Test
    public void WalletServiceHndRemoveCyclicEntryNothingToRemoveShouldNotRemoveEntryTest() {
        Scanner scanner = new Scanner(System.in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");

        // Act
        walletService.hndRemoveCyclicEntry();

        // Assert
        assertEquals(walletService.getCyclicPrototypes("wallet").getLength(),0);
    }

    @Test
    public void WalletServiceHndModifyCyclicEntryShouldModifyEntryTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator")      // Cyclic Entry menu - repeated at const
                + "1" + System.getProperty("line.separator")                    // menu entry - add entry with amount
                + "10" + System.getProperty("line.separator")                   // amount
                + "5" + System.getProperty("line.separator")                // cyclic intervals in days
                + "1" + System.getProperty("line.separator")                 // number of entry to modify
                + "1" + System.getProperty("line.separator")                 // modify entry prototype
                + "1"+ System.getProperty("line.separator")                 // change amount
                + "20"+ System.getProperty("line.separator") + "0").getBytes());    // new amount
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");

        // Act
        walletService.hndAddCyclicEntry();
        walletService.hndModifyCyclicEntry();

        // Assert
        assertEquals(walletService.getCyclicPrototypes("wallet").getAt(0).getPrototypeEntry().getAmount(),20);

    }

    @Test
    public void WalletServiceHndSwitchWalletShouldSwitchWalletTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("2" + System.getProperty("line.separator") ).getBytes());      // second wallet
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.addWallet("wallet2","user1");
        walletService.setCurrentWalletName("wallet");

        // Act
        walletService.hndSwitchWallet();

        // Assert
        assertEquals(walletService.getCurrentWalletName(),"wallet2");
    }

    @Test
    public void WalletServiceHndSwitchWalletInvalidFirstTryShouldSwitchWalletTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("3" + System.getProperty("line.separator") // third wallet - invalid - doesn't exist
                                                          + "2" + System.getProperty("line.separator")).getBytes());   // second wallet
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.addWallet("wallet2","user1");
        walletService.setCurrentWalletName("wallet");

        // Act
        walletService.hndSwitchWallet();

        // Assert
        assertEquals(walletService.getCurrentWalletName(),"wallet2");
    }

    @Test
    public void WalletServiceHndAddCategoryShouldAddCategoryTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator") // category menu - add category
                                                          + "new category" + System.getProperty("line.separator")).getBytes());   // new category name
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");

        // Act
        walletService.hndAddCategory();

        // Assert
        assertTrue(walletService.getWallets().getWallets().get(0).getCategoryList().toString().contains("new category"));
    }

    @Test
    public void WalletServiceHndManageWalletsAddWalletShouldAddWalletTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator") // add wallet
                                                          + "my new wallet").getBytes());      // new wallet name
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.addWallet("wallet2","user1");
        walletService.setCurrentWalletName("wallet");

        // Act
        walletService.hndManageWallets();

        // Assert
        assertEquals(walletService.getCurrentWalletName(),"my new wallet");
    }

    @Test
    public void WalletServiceHndManageWalletsAddWalletRepeatedNameInFirstTryShouldAddWalletTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.getProperty("line.separator") // add wallet
                                                          + "wallet" + System.getProperty("line.separator") // repeated wallet name
                                                          + "my new wallet").getBytes());      // new wallet name
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");

        // Act
        walletService.hndManageWallets();

        // Assert
        assertEquals(walletService.getCurrentWalletName(),"my new wallet");
    }

    @Test
    public void WalletServiceHndManageWalletsAddUserToWalletShouldAddUser() {
        ByteArrayInputStream in = new ByteArrayInputStream(("5" + System.getProperty("line.separator") // add user to wallet
                + "addedUser Name").getBytes());      // added user name
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");

        // Act
        walletService.hndManageWallets();

        // Assert
        assertTrue(walletService.getWalletByName(walletService.getCurrentWalletName()).getSharedUsersEmails().contains("addedUser Name"));
    }

    @Test
    public void  WalletServiceHasUserAccessCheckCurrentUserShouldReturnTrueTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("").getBytes());      // username to check
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");

        // Act
        boolean hasAccess = walletService.hasUserAccess("user1");

        // Assert
        assertTrue(hasAccess);
    }

    @Test
    public void  WalletServiceHasUserAccessCheckDifferentUserShouldReturnTrueTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("").getBytes());
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user2");
        walletService.setCurrentWalletName("wallet");

        // Act
        boolean hasAccess = walletService.hasUserAccess("user2");

        // Assert
        assertTrue(hasAccess);
    }

    @Test
    public void WalletServiceHasUserAccessShouldReturnFalseTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("").getBytes());
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");

        // Act
        boolean hasAccess = walletService.hasUserAccess("user to check");

        // Assert
        assertFalse(hasAccess);
    }

    @Test
    public void WalletServiceHasUserAccessSHaredUserShouldReturnTrueTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("5" + System.getProperty("line.separator") // add user to wallet
            + "shared user").getBytes());      // added user name
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("wallet","user1");
        walletService.setCurrentWalletName("wallet");
        walletService.hndManageWallets();

        // Act
        boolean hasAccess = walletService.hasUserAccess("shared user");

        // Assert
        assertTrue(hasAccess);
    }
}
