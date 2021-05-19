package scheduling;

import model.Entry;
import model.EntryList;
import org.junit.Test;
import scheduling.Scheduler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SchedulerTests {
    @Test
    public void SchedulerConstructorTest() {
        // Arrange
        EntryList entryList = new EntryList();

        // Act
        Scheduler scheduler = new Scheduler(entryList);

        // Assert
        assertEquals(entryList, scheduler.getEntryList());
    }

    @Test
    public void SchedulerAddPeriodicEntriesWhenPeriodicEntriesToDuplicateArePresentTest() {
        // Arrange
        Entry firstEntry = new Entry(40, 5);
        firstEntry.setDate(LocalDate.now().minusDays(5));
        Entry secondEntry = new Entry(50);
        Entry thirdEntry = new Entry(60, new ArrayList<>(), 0,
                LocalDate.now().getDayOfMonth());
        thirdEntry.setDate(LocalDate.now().minusMonths(1));

        List<Entry> entries = new LinkedList<>();
        entries.add(firstEntry);
        entries.add(secondEntry);
        entries.add(thirdEntry);

        EntryList entryList = new EntryList(entries);
        Scheduler scheduler = new Scheduler(entryList);

        // Act
        scheduler.addPeriodicEntries();

        // Assert
        assertEquals(5, entryList.getEntries().size());
        assertTrue(entryList.getEntries().contains(firstEntry.createNewCyclicInstance()));
        assertTrue(entryList.getEntries().contains(thirdEntry.createNewCyclicInstance()));
    }

    @Test
    public void SchedulerAddPeriodicEntriesWhenPeriodicEntriesArePresentButShouldNotBeDuplicatedTodayTest() {
        // Arrange
        Entry firstEntry = new Entry(40, 5);
        firstEntry.setDate(LocalDate.now().minusDays(2));
        Entry secondEntry = new Entry(50);
        Entry thirdEntry = new Entry(60, new ArrayList<>(), 0,
                LocalDate.now().minusDays(2).getDayOfMonth());

        List<Entry> entries = new LinkedList<>();
        entries.add(firstEntry);
        entries.add(secondEntry);
        entries.add(thirdEntry);

        EntryList entryList = new EntryList(entries);
        Scheduler scheduler = new Scheduler(entryList);

        // Act
        scheduler.addPeriodicEntries();

        // Assert
        assertEquals(3, entryList.getEntries().size());
    }

    @Test
    public void SchedulerAddPeriodicEntriesNothingIsAddedWhenEntriesFromCurrentDayAreAlreadyPresentTest() {
        // Arrange
        Entry firstEntry = new Entry(40, 5);
        Entry secondEntry = new Entry(50);
        Entry thirdEntry = new Entry(60, new ArrayList<>(), 0, LocalDate.now().getDayOfMonth());

        List<Entry> entries = new LinkedList<>();
        entries.add(firstEntry);
        entries.add(secondEntry);
        entries.add(thirdEntry);

        EntryList entryList = new EntryList(entries);
        Scheduler scheduler = new Scheduler(entryList);

        // Act
        scheduler.addPeriodicEntries();

        // Assert
        assertEquals(3, entryList.getEntries().size());
    }

    @Test
    public void SchedulerAddPeriodicEntriesEntriesAreAddedOnMonthEndEdgeCaseTest() {
        // eg. 31th of march should be treated as 28th in February
        // but how to test it?
    }

    @Test
    public void SchedulerAddPeriodicEntriesMonthlyEntriesAreNotMultiplicatedInSubsequentCallsTest() {
        // Arrange
        Entry entry = new Entry(60, new ArrayList<>(), 0,
                LocalDate.now().getDayOfMonth());
        entry.setDate(LocalDate.now().minusMonths(1));

        List<Entry> entries = new LinkedList<>();
        entries.add(entry);

        EntryList entryList = new EntryList(entries);
        Scheduler scheduler = new Scheduler(entryList);

        scheduler.addPeriodicEntries();

        // Act
        scheduler.addPeriodicEntries();

        // Assert
        assertEquals(2, entryList.getEntries().size());
    }
}
