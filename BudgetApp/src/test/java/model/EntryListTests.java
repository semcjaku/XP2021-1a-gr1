package model;

import model.Entry;
import model.EntryList;
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
        entryList.addEntry(entry);

        // Assert
        assertEquals(entryList.getEntries(), testEntries);
    }

    @Test
    public void EntryListConstructorTest() {
        // Arrange
        EntryList entryList = new EntryList();

        // Assert
        assertEquals(entryList.getEntries(), new LinkedList<>());
    }

    @Test
    public void EntryListConstructorWithEntryListTest() {
        // Arrange
        List<Entry> testEntries = new LinkedList<>();
        Entry entry = new Entry(150);
        testEntries.add(entry);
        EntryList entryList = new EntryList(testEntries);

        // Assert
        assertEquals(entryList.getEntries(), testEntries);
    }

    @Test
    public void EntryListToStringTest() {
        // Arrange
        int amount = 515;
        int cyclic = 7;
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        Entry entry = new Entry(amount, testList, cyclic);
        EntryList entryList = new EntryList();
        entryList.addEntry(entry);

        // Assert
        assertEquals(entryList.toString(), "model.EntryList{" +
                "entries=" + "[" +
                entry +
                "]" +
                '}');
    }

}
