package menu;

import model.Wallet;
import model.WalletList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.WalletService;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MenuManageWalletsTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void MenuManageWalletsShowShouldShowMenuTest() {
        // Arrange
        AbstractMenu menuManageWallets = getMenuForTest();

        // Act
        String result = menuManageWallets.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nMENU MANAGE WALLETS\n" +
                "1. Add wallet\n" +
                "2. Archive wallet\n" +
                "3. Rename wallet\n" +
                "4. Show logged in user wallets\n" +
                "0. Exit", result);
    }

    @Test
    public void MenuManageWalletsReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        MenuManageWallets menuManageWallets = getMenuForTest();
        String input = String.valueOf(menuManageWallets.getMaxInputNumber() + 1);

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuManageWallets.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuManageWalletsReadShouldReturnErrorWhenBelowRangeTest() throws Exception {
        // Arrange
        MenuManageWallets menuManageWallets = getMenuForTest();
        String input = String.valueOf(menuManageWallets.getMinInputNumber() - 1);

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuManageWallets.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuManageWalletsReadShouldReturnErrorWhenNullTest() throws Exception {
        // Arrange
        MenuManageWallets menuManageWallets = getMenuForTest();
        String input = null;

        // Expect
        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuManageWallets.validateChoice(input);
    }

    @Test
    public void MenuManageWalletsReadShouldReturnErrorWhenEmptyTest() throws Exception {
        // Arrange
        MenuManageWallets menuManageWallets = getMenuForTest();
        String input = "";

        // Expect
        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuManageWallets.validateChoice(input);
    }

    @Test
    public void MenuManageWalletsReadShouldTrimInputTest() throws Exception {
        // Arrange
        MenuManageWallets menuManageWallets = getMenuForTest();
        String input = "1  ";

        // Act
        menuManageWallets.validateChoice(input);

        // Assert
    }

    @Test
    public void MenuManageWalletsReadShouldReturnErrorWhenNotANumberTest() throws Exception {
        // Arrange
        MenuManageWallets menuManageWallets = getMenuForTest();
        String input = "Hello";

        // Except
        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input not a number");

        // Act
        menuManageWallets.validateChoice(input);
    }

    @Test
    public void MenuManageWalletsExecuteChoiceOptionOneAddWalletTest() throws InvalidInputException {
        // Arrange
        String newWalletName = "MyWallet";
        ByteArrayInputStream in = new ByteArrayInputStream(newWalletName.getBytes());
        WalletService walletService = new WalletService(new Scanner(in));

        MenuManageWallets menuManageWallets = new MenuManageWallets(walletService);

        // Act
        menuManageWallets.executeChoice(1);

        // Assert
        assertEquals(newWalletName, walletService.getWalletByName(newWalletName).getName());
    }

    @Test
    public void MenuManageWalletsExecuteChoiceOptionOneAddWalletWithSpaceInNameTest() throws InvalidInputException {
        // Arrange
        String newWalletName = "My Wallet";
        ByteArrayInputStream in = new ByteArrayInputStream(newWalletName.getBytes());
        WalletService walletService = new WalletService(new Scanner(in));

        MenuManageWallets menuManageWallets = new MenuManageWallets(walletService);

        // Act
        menuManageWallets.executeChoice(1);

        // Assert
        assertEquals(newWalletName, walletService.getWalletByName(newWalletName).getName());
    }

    @Test
    public void MenuManageWalletsExecuteChoiceOptionTwoArchiveWalletTest() throws InvalidInputException {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("Wallet".getBytes());
        WalletService walletService = new WalletService(new Scanner(in));
        walletService.addWallet("Wallet","user1");

        MenuManageWallets menuManageWallets = new MenuManageWallets(walletService);

        // Act
        menuManageWallets.executeChoice(2);

        // Assert
        assertTrue(walletService.getWalletByName("Wallet").isArchived());
    }

    @Test
    public void MenuManageWalletsExecuteChoiceOptionThreeRenameWalletTest() throws InvalidInputException {
        // Arrange
        String oldWalletName = "OldWallet";
        String newWalletName = "Newwallet";

        ByteArrayInputStream in = new ByteArrayInputStream((oldWalletName + System.getProperty("line.separator") + newWalletName).getBytes());
        WalletService walletService = new WalletService(new Scanner(in));
        walletService.addWallet(oldWalletName,"user1");

        MenuManageWallets menuManageWallets = new MenuManageWallets(walletService);

        // Act
        menuManageWallets.executeChoice(3);

        // Assert
        assertEquals(newWalletName, walletService.getWalletByName(newWalletName).getName());
    }

    private MenuManageWallets getMenuForTest() {
//        WalletList walletList = new WalletList();
//        walletList.addWallet("Second wallet","user1");
//        return new MenuManageWallets(walletList);
        return new MenuManageWallets(System.in);
    }
}
