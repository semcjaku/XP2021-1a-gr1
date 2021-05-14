import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
                "1.Add Category\n" +
                "Please select 1!", result);
    }

    @Test
    public void MenuCategoryReadShouldReturnErrorWhenOverRangeTest() throws Exception {
        // Arrange
        AbstractMenu menuCategory = new MenuCategory();
        String input = "5";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input number out of range");

        // Act
        menuCategory.read(input);

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
        menuCategory.read(input);

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
        menuCategory.read(input);

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
        menuCategory.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuCategoryReadShouldTrimInputTest() throws Exception {
        // Arrange
        AbstractMenu menuCategory = new MenuCategory();
        String input = "1  ";

        // Act
        int result = menuCategory.read(input);

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void MenuCategoryReadShouldReturnErrorWhenNotANumberTest() throws Exception {
        // Arrange
        AbstractMenu menuCategory = new MenuCategory();
        String input = "Hello";

        exception.expect(InvalidInputException.class);
        exception.expectMessage("Input not a number");

        // Act
        menuCategory.read(input);

        // Assert
        // throw exception
    }

    @Test
    public void MenuCategoryGetCyclicDayInputShowTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("category1".getBytes());
        MenuCategory menuCategory = new MenuCategory(in);

        // Act
        String result = menuCategory.getNameInputShow();

        // Assert
        assertEquals("category1", result);
    }

    @Test
    public void MenuCategoryShowInputsByChoiceFirstTest() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("category1".getBytes());
        MenuCategory menuCategory = new MenuCategory(in);

        // Act
        String result = menuCategory.showInputsByChoice(1);

        // Assert
        assertEquals("category1", result);
    }
}
