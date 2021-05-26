package model;

import exception.BothIntervalAndDayOfMonthSpecifiedException;
import exception.ImproperDayOfMonthException;
import model.Entry;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;

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
    public void EntryConstructorWithAmountAndCyclicDateTest() {
        // Arrange
        int amount = 515;
        int cyclic = 7;
        Entry entry = new Entry(amount, cyclic);

        // Assert
        assertEquals(entry.getAmount(), amount);
        assertEquals(entry.getCyclicIntervalInDays(), cyclic);
    }

    @Test
    public void EntryConstructorWithEverythingTest() {
        // Arrange
        int amount = 515;
        int cyclic = 7;
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        Entry entry = new Entry(amount, testList, cyclic);

        // Assert
        assertEquals(entry.getAmount(), amount);
        assertEquals(entry.getCyclicIntervalInDays(), cyclic);
        assertEquals(entry.getCategories(), testList);
    }

    @Test
    public void EntryToStringTest() {
        // Arrange
        int amount = 515;
        int cyclic = 7;
        int cyclicDayOfMonth = 0;
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        Entry entry = new Entry(amount, testList, cyclic);

        // Assert
        assertEquals(entry.toString(), "model.Entry{" +
                "amount=" + amount +
                ", categories=" + testList +
                ", cyclicIntervalInDays=" + cyclic +
                ", cyclicDayOfMonth=" + cyclicDayOfMonth +
                '}');
    }
    @Test
    public void EntryConstructorWithProperDayOfMonthTest() {
        // Arrange
        int dayOfMonth = 14;
        int amount = 515;
        List<String> categories = new ArrayList<>();
        int cyclicDay = 0;

        // Act
        Entry entry = new Entry(amount, categories, cyclicDay, dayOfMonth);

        // Assert
        assertEquals(dayOfMonth, entry.getCyclicDayOfMonth());
    }

    @Test
    public void EntryConstructorWithImproperDayOfMonthTest() {
        // Arrange
        int dayOfMonth = 35;
        int amount = 515;
        List<String> categories = new ArrayList<>();
        int cyclicDay = 0;

        ThrowingRunnable construction = () -> {
            Entry entry = new Entry(amount, categories, cyclicDay, dayOfMonth);
        };

        // Assert
        assertThrows(ImproperDayOfMonthException.class, construction);
    }

    @Test
    public void EntryIsCyclicWhenIntervalInDaysSpecifiedTest() {
        // Arrange
        int amount = 555;
        int cyclicIntervalInDays = 5;
        Entry entry = new Entry(amount, cyclicIntervalInDays);

        // Assert
        assertTrue(entry.isCyclic());
    }

    @Test
    public void EntryIsCyclicWhenCyclicDayOfMonthSpecifiedTest() {
        // Arrange
        int amount = 555;
        int cyclicDayOfMonth = 5;
        Entry entry = new Entry(amount, cyclicDayOfMonth);

        // Assert
        assertTrue(entry.isCyclic());
    }

    @Test
    public void EntryIsCyclicWhenEntryIsNotCyclicTest() {
        // Arrange
        int amount = 555;
        Entry entry = new Entry(amount);

        // Assert
        assertFalse(entry.isCyclic());
    }

    @Test
    public void EntryCreateNewCyclicInstanceTest() {
        // Arrange
        int amount = 555;
        int cyclicDayOfMonth = 5;
        Entry entry = new Entry(amount, cyclicDayOfMonth);

        // Act
        Entry newEntry = entry.createNewCyclicInstance();

        // Assert
        assertEquals(entry.getAmount(), newEntry.getAmount());
        assertEquals(entry.getCyclicIntervalInDays(), newEntry.getCyclicIntervalInDays());
        assertEquals(entry.getCyclicDayOfMonth(), newEntry.getCyclicDayOfMonth());
        assertEquals(entry.getCategories(), newEntry.getCategories());
    }

    @Test
    public void EntryConstructorWithDateGivenTest() {
        // Arrange
        int amount = 0;

        // Act
        Entry entry = new Entry(amount);

        // Assert
        assertEquals(LocalDate.now(), entry.getDate());
    }

    @Test
    public void EntryConstructorThrowsExceptionWhenBothIntervalAndDayOfMonthSpecifiedTest() {
        // Arrange
        int dayOfMonth = 24;
        int amount = 515;
        List<String> categories = new ArrayList<>();
        int cyclicDay = 12;

        ThrowingRunnable construction = () -> {
            Entry entry = new Entry(amount, categories, cyclicDay, dayOfMonth);
        };

        // Assert
        assertThrows(BothIntervalAndDayOfMonthSpecifiedException.class, construction);
        }

    @Test
    public void EntrySetCyclicDayOfMonthWhenCorrectDayTest() {
        // Arrange
        int day = 26;
        int amount = 555;
        Entry entry = new Entry(amount);

        // Act
        entry.setCyclicDayOfMonth(day);

        // Assert
        assertTrue(entry.isCyclic());
        assertEquals(entry.getCyclicDayOfMonth(), 26);
    }

    @Test
    public void EntrySetCyclicDayOfMonthThrowsExceptionWhenIncorrectDayTest() {
        // Arrange
        int day = 66;
        int amount = 555;
        Entry entry = new Entry(amount);

        // Act
        ThrowingRunnable construction = () -> {
            entry.setCyclicDayOfMonth(day);
        };

        // Assert
        assertThrows(ImproperDayOfMonthException.class, construction);
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
    public void EntrySetCyclicIntervalInDaysTest() {
        // Arrange
        int amount = 555;
        int cyclicInterval = 12;
        Entry entry = new Entry(amount);

        // Act
        entry.setCyclicIntervalInDays(cyclicInterval);

        // Assert
        assertTrue(entry.isCyclic());
        assertEquals(entry.getCyclicIntervalInDays(), 12);
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
