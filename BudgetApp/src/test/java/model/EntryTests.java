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
    }

    @Test
    public void EntryToStringTest() {
        // Arrange
        int amount = 515;
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        Entry entry = new Entry(amount, testList);

        // Assert
        assertEquals(entry.toString(), "model.Entry{" +
                "amount=" + amount +
                ", categories=" + testList +
                '}');
    }

    @Test
    public void EntryCloneAtTest() {
        // Arrange
        int amount = 555;
        List<String> categories = List.of("cat1");
        LocalDate oldDate = LocalDate.MIN;
        Entry entry = new Entry(amount, categories, oldDate);

        // Act
        LocalDate newDate = LocalDate.MIN.plusDays(1);
        Entry newEntry = entry.cloneAt(newDate);

        // Assert
        assertEquals(entry.getAmount(), newEntry.getAmount());
        assertEquals(entry.getCategories(), newEntry.getCategories());
        assertEquals(newDate, newEntry.getDate());
    }
}
