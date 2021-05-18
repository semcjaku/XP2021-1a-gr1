import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
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
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        Entry entry = new Entry(amount, testList, cyclic);

        // Assert
        assertEquals(entry.toString(), "Entry{" +
                "amount=" + amount +
                ", categories=" + testList +
                ", cyclicDay=" + cyclic +
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
    public void EntryIsCyclicWhenIntervalInDaysSpecified() {
        // Arrange
        int amount = 555;
        int cyclicIntervalInDays = 5;
        Entry entry = new Entry(amount, cyclicIntervalInDays);

        // Assert
        assertTrue(entry.isCyclic());
    }

    @Test
    public void EntryIsCyclicWhenCyclicDayOfMonthSpecified() {
        // Arrange
        int amount = 555;
        int cyclicDayOfMonth = 5;
        Entry entry = new Entry(amount, cyclicDayOfMonth);

        // Assert
        assertTrue(entry.isCyclic());
    }

    @Test
    public void EntryIsCyclicWhenEntryIsNotCyclic() {
        // Arrange
        int amount = 555;
        Entry entry = new Entry(amount);

        // Assert
        assertFalse(entry.isCyclic());
    }

    @Test
    public void EntryCreateNewCyclicInstance() {
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
    public void EntryConstructorWithDateGiven() {
        // Arrange
        int amount = 0;

        // Act
        Entry entry = new Entry(amount);

        // Assert
        assertEquals(LocalDate.now(), entry.getDate());
    }

    @Test
    public void EntryConstructorThrowsExceptionWhenBothIntervalAndDayOfMonthSpecified() {
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
}
