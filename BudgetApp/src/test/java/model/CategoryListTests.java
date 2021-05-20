package model;

import model.CategoryList;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CategoryListTests {

    @Test
    public void CategoryListAddCategoryTest(){
        // Arrange
        CategoryList categoryList = new CategoryList();
        String category = "Custom1";

        List<String> testCategories = new LinkedList<>(Arrays.asList("clothes", "entertainment", "food", "friendly exchange", "home", "paycheck", "taxes"));
        testCategories.add(category);
        java.util.Collections.sort(testCategories);

        // Act
        categoryList.addCategory(category);

        // Assert
        assertEquals(testCategories, categoryList.getCategories());
    }

    @Test
    public void CategoryListConstructorTest() {
        // Arrange
        CategoryList categoryList = new CategoryList();
        List<String> testCategories = new LinkedList<>(Arrays.asList("clothes", "entertainment", "food", "friendly exchange", "home", "paycheck", "taxes"));
        java.util.Collections.sort(testCategories);

        // Assert
        assertEquals(testCategories, categoryList.getCategories());
    }


    @Test
    public void CategoryListToStringTest() {
        // Arrange
        String category1 = "Custom1";
        String category2 = "Custom2";
        CategoryList categoryList = new CategoryList();
        categoryList.addCategory(category1);
        categoryList.addCategory(category2);

        // Assert
        assertEquals("model.CategoryList{" +
                "categories=" + "[Custom1, Custom2, clothes, entertainment, food, friendly exchange, home, paycheck, taxes]}", categoryList.toString());
    }

}
