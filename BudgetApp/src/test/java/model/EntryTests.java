package model;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EntryTests {

    @Test
    public void EntryAddCategoryTest(){
        // Arrange
        int amount = 515;
        Entry entry = new Entry(amount);

        String category = "food";
        List<String> testList = new LinkedList<>() {};

        // Act
        List<String> categories = entry.getCategories();
        assertEquals(Collections.emptyList(), categories);

        testList.add(category);
        entry.addCategory(category);

        // Assert
        assertEquals(testList, entry.getCategories());
        assertEquals(amount, entry.getAmount());
    }

    @Test
    public void EntryConstructorWithAmountTest() {
        // Arrange
        int amount = 515;
        Entry entry = new Entry(amount);

        // Assert
        assertEquals(entry.getAmount(), amount);
        assertEquals("admin",entry.getCreatorEmail());
    }


    @Test
    public void EntryConstructorWithAmountAndCreatorTest() {
        // Arrange
        int amount = 515;
        Entry entry = new Entry("user1",amount);

        // Assert
        assertEquals(entry.getAmount(), amount);
        assertEquals("user1",entry.getCreatorEmail());
    }

    @Test
    public void EntryConstructorWithAmountAndCategoriesTest() {
        // Arrange
        int amount = 515;
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        Entry entry = new Entry(amount, testList);

        // Assert
        assertEquals(entry.getAmount(), amount);
        assertEquals(entry.getCategories(), testList);
        assertEquals("admin",entry.getCreatorEmail());
    }

    @Test
    public void EntryConstructorWithAmountAndCategoriesAndCreatorTest() {
        // Arrange
        int amount = 515;
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        Entry entry = new Entry("user1", amount, testList);

        // Assert
        assertEquals(entry.getAmount(), amount);
        assertEquals(entry.getCategories(), testList);
        assertEquals("user1",entry.getCreatorEmail());
    }

    @Test
    public void EntryConstructorWithAmountAndCategoriesAndDateGiven() {
        // Arrange
        int amount = 515;
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        LocalDate date = LocalDate.MIN;
        Entry entry = new Entry(amount, testList, date);

        // Assert
        assertEquals(entry.getAmount(), amount);
        assertEquals(entry.getCategories(), testList);
        assertEquals(date, entry.getDate());
        assertEquals("admin",entry.getCreatorEmail());
    }

    @Test
    public void EntryToStringDefaultCreatorTest() {
        // Arrange
        int amount = 515;
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        Entry entry = new Entry(amount, testList);

        // Assert
        assertEquals(entry.toString(), "Entry{" +
                "amount=" + amount +
                ", categories=" + testList +
                ", created by=admin" +
                '}');
    }

    @Test
    public void EntryToStringTest() {
        // Arrange
        int amount = 515;
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        Entry entry = new Entry("user1",amount, testList);

        // Assert
        assertEquals(entry.toString(), "Entry{" +
                "amount=" + amount +
                ", categories=" + testList +
                ", created by=user1" +
                '}');
    }

    @Test
    public void EntryCloneAtTest() {
        // Arrange
        int amount = 555;
        List<String> categories = List.of("cat1");
        LocalDate oldDate = LocalDate.MIN;
        Entry entry = new Entry(amount, categories, oldDate);
    }

    @Test
    public void EntrySetAmountTest() {
        // Arrange
        int amount = 555;
        int newAmount = 12;
        Entry entry = new Entry(amount);

        // Act
        entry.setAmount(newAmount);

        // Assert
        assertEquals(entry.getAmount(), 12);
    }

    @Test
    public void EntryRemoveCategoryTest(){
        // Arrange
        int amount = 515;
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        Entry entry = new Entry(amount, testList);

        //Act
        entry.removeCategory("food");
        testList.remove("food");

        // Assert
        assertEquals(entry.getCategories(), testList);
    }

    @Test
    public void EntrySetCategoriesTest(){
        // Arrange
        int amount = 515;
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        Entry entry = new Entry(amount);

        //Act
        entry.setCategories(testList);

        // Assert
        assertEquals(entry.getCategories(), testList);
    }
}
