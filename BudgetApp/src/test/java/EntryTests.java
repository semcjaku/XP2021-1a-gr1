import org.junit.Test;

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
    public void EntryConstructorWithAmountAndCyclicDateTest() {
        // Arrange
        int amount = 515;
        int cyclic = 7;
        Entry entry = new Entry(amount, cyclic);

        // Assert
        assertEquals(entry.getAmount(), amount);
        assertEquals(entry.getCyclicDay(), cyclic);
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
        assertEquals(entry.getCyclicDay(), cyclic);
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
}
