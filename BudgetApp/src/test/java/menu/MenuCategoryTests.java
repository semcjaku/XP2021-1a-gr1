package menu;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.WalletService;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MenuCategoryTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void MenuCategoryShowShouldShowMenuTest() {
        // Arrange
        AbstractMenu menuCategory = new MenuCategory(System.in);

        // Act
        String result = menuCategory.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nMENU CATEGORY\n" +
                "1.Add Category\n" +
                "0.Exit", result);
    }

    @Test
    public void MenuCategoryReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        AbstractMenu menuCategory = new MenuCategory(System.in);
        String input = "5";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuCategory.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuCategoryReadShouldReturnErrorWhenBelowRangeTest() throws Exception {
        // Arrange
        AbstractMenu menuCategory = new MenuCategory(System.in);
        String input = "-1";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuCategory.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuCategoryReadShouldReturnErrorWhenNullTest() throws Exception {
        // Arrange
        AbstractMenu menuCategory = new MenuCategory(System.in);
        String input = null;

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuCategory.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuCategoryReadShouldReturnErrorWhenEmptyTest() throws Exception {
        // Arrange
        AbstractMenu menuCategory = new MenuCategory(System.in);
        String input = "";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Empty input");

        // Act
        menuCategory.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuCategoryReadShouldTrimInputTest() throws Exception {
        // Arrange
        AbstractMenu menuCategory = new MenuCategory(System.in);
        String input = "1  ";

        // Act
        menuCategory.validateChoice(input);

        // Assert

    }

    @Test
    public void MenuCategoryReadShouldReturnErrorWhenNotANumberTest() throws Exception {
        // Arrange
        AbstractMenu menuCategory = new MenuCategory(System.in);
        String input = "Hello";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input not a number");

        // Act
        menuCategory.validateChoice(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuCategoryGetCyclicDayInputShowTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("category1".getBytes());
        MenuCategory menuCategory = new MenuCategory(in);

        // Act
//        String result = menuCategory.getNameInputShow();

        // Assert
//        assertEquals("category1", result);
    }

    @Test
    public void MenuCategoryExecuteActionsOneTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("category".getBytes());
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("Wallet","user1");
        walletService.setCurrentWalletName("Wallet");

        MenuCategory menuCategory = new MenuCategory(walletService);

        // Act
        menuCategory.executeChoice(1);

        // Assert
        assertTrue(walletService.getCategoryList("Wallet").getCategories().contains("category"));
    }

    @Test
    public void MenuCategoryExecuteChoiceOptionOneRepeatedCategoryTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream(("category"+ System.getProperty("line.separator")+"category"+ System.getProperty("line.separator") + "category2").getBytes());
        Scanner scanner = new Scanner(in);
        WalletService walletService = new WalletService(scanner);
        walletService.setLoggedInUser("user1");
        walletService.addWallet("Wallet","user1");
        walletService.setCurrentWalletName("Wallet");
        MenuCategory menuCategory = new MenuCategory(walletService);

        // Act
        menuCategory.executeChoice(1);
        menuCategory.executeChoice(1);

        // Assert
        assertTrue(walletService.getCategoryList("Wallet").getCategories().contains("category"));
    }

}
