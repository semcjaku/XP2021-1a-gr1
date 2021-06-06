package service;

import model.Entry;
import model.EntryList;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SerializerServiceTests {

    @Test
    public void SerializerServiceGeneralTest() {
        // Arrange
        File file = new File(Path.of("").toAbsolutePath() + "/data/testEntryList.ser");
        file.deleteOnExit();
        EntryList entryList = new EntryList();
        Entry entry = new Entry(150);
        Entry entry2 = new Entry(3);

        entryList.addEntry(entry);
        entryList.addEntry(entry2);

        LinkedList<Entry> testList1 = new LinkedList<>();
        LinkedList<Entry> testList2 = new LinkedList<>();
        testList2.add(entry);
        testList2.add(entry2);

        // Act
        SerializerService serializerService = new SerializerService();
        serializerService.writeObjectToFile(Path.of("").toAbsolutePath() + "/data/testEntryList.ser", entryList);

        entryList.removeEntry(0);
        entryList.removeEntry(0);

        // Assert
        assertEquals(entryList.getEntries(),testList1);

        // Act 2
        entryList = (EntryList) serializerService.readObjectFromFile(Path.of("").toAbsolutePath() + "/data/testEntryList.ser");

        // Assert 2
        assertEquals(entryList.getEntries(), testList2);
    }
}
