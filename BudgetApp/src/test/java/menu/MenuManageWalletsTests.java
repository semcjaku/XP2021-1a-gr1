package menu;

import model.Wallet;
import model.WalletList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
                "0. RETURN\n" +
                "Please select 0-3!", result);
    }

    @Test
    public void MenuManageWalletsReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        MenuManageWallets menuManageWallets = getMenuForTest();
        String input = String.valueOf(menuManageWallets.getMaxInputNumber() + 1);

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuManageWallets.read(input);

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
        menuManageWallets.read(input);

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
        menuManageWallets.read(input);

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
        menuManageWallets.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuManageWalletsReadShouldTrimInputTest() throws Exception {
        // Arrange
        MenuManageWallets menuManageWallets = getMenuForTest();
        String input = "1  ";

        // Act
        int result = menuManageWallets.read(input);

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void MenuManageWalletsReadShouldReturnErrorWhenNotANumberTest() throws Exception {
        // Arrange
        MenuManageWallets menuManageWallets = getMenuForTest();
        String input = "Hello";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input not a number");

        // Act
        menuManageWallets.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuManageWalletsExecuteActionsFirstChoiceTest() throws InvalidInputException {
        // Arrange
        String newWalletName = "My wallet";
        ByteArrayInputStream in = new ByteArrayInputStream(newWalletName.getBytes());

        WalletList walletList = new WalletList();
        MenuManageWallets menuManageWallets = new MenuManageWallets(new Scanner(in), walletList);

        Wallet newWallet = new Wallet(newWalletName);

        // Act
        menuManageWallets.showInputsByChoice(1);

        // Assert
        assertEquals(newWallet, walletList.getWallets().get(walletList.getLength() - 1));
    }

    @Test
    public void MenuManageWalletsExecuteActionsSecondChoiceTest() throws InvalidInputException {
        // Arrange
        int walletIndex = 2;
        ByteArrayInputStream in = new ByteArrayInputStream(String.valueOf(walletIndex).getBytes());

        WalletList walletList = new WalletList();
        walletList.addWallet(new Wallet("Second wallet"));
        MenuManageWallets menuManageWallets = new MenuManageWallets(new Scanner(in), walletList);

        // Act
        menuManageWallets.showInputsByChoice(2);

        // Assert
        assertEquals(1, walletList.getLength());
    }

    @Test
    public void MenuManageWalletsExecuteActionsThirdChoiceTest() throws InvalidInputException {
        // Arrange
        String newWalletName = "My wallet";
        int walletIndex = 1;
        ByteArrayInputStream in = new ByteArrayInputStream((walletIndex + System.getProperty("line.separator") + newWalletName).getBytes());

        WalletList walletList = new WalletList();
        MenuManageWallets menuManageWallets = new MenuManageWallets(new Scanner(in), walletList);

        // Act
        menuManageWallets.showInputsByChoice(3);

        // Assert
        assertEquals(newWalletName, walletList.getWallets().get(walletList.getLength() - 1).getName());
    }

    private MenuManageWallets getMenuForTest() {
        WalletList walletList = new WalletList();
        walletList.addWallet(new Wallet("Second wallet"));
        return new MenuManageWallets(walletList);
    }
}
