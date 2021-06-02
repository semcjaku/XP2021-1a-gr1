package menu;

import model.Wallet;
import model.WalletList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.WalletService;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuManageWallets.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuManageWalletsReadShouldReturnErrorWhenEmptyTest() throws Exception {
        // Arrange
        MenuManageWallets menuManageWallets = getMenuForTest();
        String input = "";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuManageWallets.validateChoice(input);

        // Assert
        // throw exception
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

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input not a number");

        // Act
        menuManageWallets.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuManageWalletsExecuteActionsFirstChoiceAddWalletTest() throws InvalidInputException {
        // Arrange
        WalletService walletService = new WalletService();
        String newWalletName = "MyWallet";
        ByteArrayInputStream in = new ByteArrayInputStream(newWalletName.getBytes());

        MenuManageWallets menuManageWallets = new MenuManageWallets(new Scanner(in));

        Wallet newWallet = new Wallet(newWalletName, "user1");

        // Act
        menuManageWallets.executeActions(1,"user1",walletService);

        // Assert
        assertEquals(newWallet.getName(), walletService.getWalletByName(newWalletName).getName());
    }

    @Test
    public void MenuManageWalletsExecuteActionsFirstChoiceAddWalletWithSpaceInNameTest() throws InvalidInputException {
        // Arrange
        WalletService walletService = new WalletService();
        String newWalletName = "My Wallet";
        ByteArrayInputStream in = new ByteArrayInputStream(newWalletName.getBytes());

        MenuManageWallets menuManageWallets = new MenuManageWallets(new Scanner(in));

        Wallet newWallet = new Wallet(newWalletName, "user1");

        // Act
        menuManageWallets.executeActions(1,"user1",walletService);

        // Assert
        assertEquals(newWallet.getName(), walletService.getWalletByName(newWalletName).getName());
    }

    @Test
    public void MenuManageWalletsExecuteActionsSecondChoiceArchiveWalletTest() throws InvalidInputException {
        // Arrange
        WalletService walletService = new WalletService();
        walletService.addWallet("Wallet","user1");

        ByteArrayInputStream in = new ByteArrayInputStream("Wallet".getBytes());

        MenuManageWallets menuManageWallets = new MenuManageWallets(new Scanner(in));

        // Act
        menuManageWallets.executeActions(2,"user1",walletService);

        // Assert
        assertEquals(true, walletService.getWalletByName("Wallet").isArchived());
    }

    @Test
    public void MenuManageWalletsExecuteActionsThirdChoiceRenameWalletTest() throws InvalidInputException {
        // Arrange
        String oldWalletName = "OldWallet";
        String newWalletName = "Newwallet";

        WalletService walletService = new WalletService();
        walletService.addWallet(oldWalletName,"user1");
        ByteArrayInputStream in = new ByteArrayInputStream((oldWalletName + System.getProperty("line.separator") + newWalletName).getBytes());

        MenuManageWallets menuManageWallets = new MenuManageWallets(new Scanner(in));

        // Act
        menuManageWallets.executeActions(3,"user1", walletService);

        // Assert
        assertEquals(newWalletName, walletService.getWalletByName(newWalletName).getName());
    }

    private MenuManageWallets getMenuForTest() {
        WalletList walletList = new WalletList();
        walletList.addWallet("Second wallet","user1");
        return new MenuManageWallets(walletList);
    }
}
