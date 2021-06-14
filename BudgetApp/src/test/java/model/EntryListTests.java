package model;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EntryListTests {

    @Test
    public void EntryListAddEntryTest(){
        // Arrange
        EntryList entryList = new EntryList();
        Entry entry = new Entry(150);

        List<Entry> testEntries = new LinkedList<>();
        testEntries.add(entry);

        // Act
        entryList.add(entry);

        // Assert
        assertEquals(entryList.asList(), testEntries);
    }

    @Test
    public void EntryListRemoveEntryTest(){
        // Arrange
        EntryList entryList = new EntryList();
        Entry entry = new Entry(150);
        Entry entry2 = new Entry(3);

        List<Entry> testEntries = new LinkedList<>();
        testEntries.add(entry2);

        // Act
        entryList.add(entry);
        entryList.add(entry2);
        entryList.removeAt(0);

        // Assert
        assertEquals(entryList.asList(), testEntries);
    }

    @Test
    public void EntryListLengthNotEmptyTest(){
        // Arrange
        EntryList entryList = new EntryList();
        Entry entry = new Entry(150);
        Entry entry2 = new Entry(3);

        int expected = 2;

        // Act
        entryList.add(entry);
        entryList.add(entry2);

        // Assert
        assertEquals(entryList.getLength(), expected);
    }

    @Test
    public void EntryListLengthEmptyTest(){
        // Arrange
        EntryList entryList = new EntryList();

        int expected = 0;

        // Assert
        assertEquals(entryList.getLength(), expected);
    }

    @Test
    public void EntryListConstructorTest() {
        // Arrange
        EntryList entryList = new EntryList();

        // Assert
        assertEquals(entryList.asList(), new LinkedList<>());
    }

    @Test
    public void EntryListConstructorWithEntryListTest() {
        // Arrange
        List<Entry> testEntries = new LinkedList<>();
        Entry entry = new Entry(150);
        testEntries.add(entry);
        EntryList entryList = new EntryList(testEntries);

        // Assert
        assertEquals(entryList.asList(), testEntries);
    }

    @Test
    public void EntryListGetOrderedEntriesStringTest() {
        // Arrange
        EntryList entryList = new EntryList();
        Entry entry = new Entry(150);
        Entry entry2 = new Entry(3);

        // Act
        entryList.add(entry);
        entryList.add(entry2);

        // Assert
        assertEquals(entryList.getOrderedEntriesString(), "1. " + entry + "\n" + "2. " + entry2 + "\n");
    }

    @Test
    public void EntryListToStringTest() {
        // Arrange
        int amount = 515;
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        Entry entry = new Entry(amount, testList);
        EntryList entryList = new EntryList();
        entryList.add(entry);

        // Assert
        assertEquals(entryList.toString(), "EntryList{" +
                "entries=" + "[" +
                entry +
                "]" +
                '}');
    }

    @Test
    public void EntryListGetEntryTest() {
        // Arrange
        int amount = 515;
        int amount2 = 112;
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        Entry entry = new Entry(amount, testList);
        Entry entry2 = new Entry(amount2);
        EntryList entryList = new EntryList();
        entryList.add(entry);
        entryList.add(entry2);

        // Assert
        assertEquals(entryList.getAt(1), new Entry(amount2));
    }

    @Test
    public void EntryListSetEntriesTest() {
        // Arrange
        int amount = 515;
        int amount2 = 112;
        List<String> catList = new LinkedList<>() {};
        catList.add("food");
        catList.add("rent");
        Entry entry = new Entry(amount, catList);
        Entry entry2 = new Entry(amount2);
        List<Entry> testList = new LinkedList<>();
        testList.add(entry);
        testList.add(entry2);

        EntryList entryList = new EntryList();
        entryList.setEntries(testList);

        // Assert
        assertEquals(entryList.asList(), testList);
    }
}
