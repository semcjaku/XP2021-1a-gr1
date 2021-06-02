package menu;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.WalletService;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class MenuCategoryTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void MenuCategoryShowShouldShowMenuTest() {
        // Arrange
        AbstractMenu menuCategory = new MenuCategory();

        // Act
        String result = menuCategory.show();

        // Assert
        assertNotNull(result);
        assertEquals("\nMENU CATEGORY\n" +
                "1.Add Category", result);
    }

    @Test
    public void MenuCategoryReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        AbstractMenu menuCategory = new MenuCategory();
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
        AbstractMenu menuCategory = new MenuCategory();
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
        AbstractMenu menuCategory = new MenuCategory();
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
        AbstractMenu menuCategory = new MenuCategory();
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
        AbstractMenu menuCategory = new MenuCategory();
        String input = "1  ";

        // Act
        menuCategory.validateChoice(input);

        // Assert

    }

    @Test
    public void MenuCategoryReadShouldReturnErrorWhenNotANumberTest() throws Exception {
        // Arrange
        AbstractMenu menuCategory = new MenuCategory();
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
        WalletService walletService = new WalletService();
        walletService.addWallet("Wallet","user1");

        ByteArrayInputStream in = new ByteArrayInputStream("category".getBytes());
        MenuCategory menuCategory = new MenuCategory(in);

        // Act
        menuCategory.executeActions(1,"Wallet",walletService);

        // Assert
        assertTrue(walletService.getCategoryList("Wallet").getCategories().contains("category"));
    }

    @Test
    public void MenuCategoryExecuteActionsOneRepeatedCategoryTest() {
        // Arrange
        WalletService walletService = new WalletService();
        walletService.addWallet("Wallet","user1");

        ByteArrayInputStream in = new ByteArrayInputStream("category".getBytes());
        MenuCategory menuCategory = new MenuCategory(in);
        menuCategory.executeActions(1,"Wallet",walletService);
        in = new ByteArrayInputStream(("category"+ System.getProperty("line.separator") + "category2").getBytes());
        menuCategory = new MenuCategory(in);

        // Act
        menuCategory.executeActions(1,"Wallet",walletService);

        // Assert
        assertTrue(walletService.getCategoryList("Wallet").getCategories().contains("category"));
    }

}
