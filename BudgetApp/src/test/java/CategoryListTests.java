import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CategoryListTests {

    @Test
    public void CategoryListAddCategoryTest(){
        // Arrange
        CategoryList categoryList = new CategoryList();
        String category = "Home";

        List<String> testCategories = new LinkedList<>();
        testCategories.add(category);

        // Act
        categoryList.addCategory(category);

        // Assert
        assertEquals(testCategories, categoryList.getCategories());
    }

    @Test
    public void CategoryListConstructorTest() {
        // Arrange
        CategoryList categoryList = new CategoryList();

        // Assert
        assertEquals(new LinkedList<>(), categoryList.getCategories());
    }


    @Test
    public void CategoryListToStringTest() {
        // Arrange
        String category1 = "Food";
        String category2 = "Rent";
        CategoryList categoryList = new CategoryList();
        categoryList.addCategory(category1);
        categoryList.addCategory(category2);

        // Assert
        assertEquals("CategoryList{" +
                "categories=" + "[Food, Rent]}", categoryList.toString());
    }

}
