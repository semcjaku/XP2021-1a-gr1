package scheduling;

import model.cyclic.CyclicEntryPrototype;
import model.Entry;
import model.EntryList;
import model.cyclic.CyclicPrototypeList;
import model.cyclic.IntervalCyclicEntryPrototype;
import model.cyclic.MonthlyCyclicEntryPrototype;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SchedulerTests {
    @Test
    public void SchedulerConstructorTest() {
        // Arrange
        EntryList entryList = new EntryList();
        CyclicPrototypeList cyclicEntryPrototypes = new CyclicPrototypeList();

        // Act
        Scheduler scheduler = new Scheduler(entryList, cyclicEntryPrototypes);

        // Assert
        assertEquals(entryList, scheduler.getEntryList());
    }

    @Test
    public void SchedulerAddPeriodicEntriesWhenPeriodicEntriesToDuplicateArePresentTest() {
        // Arrange
        int numberOfDays = 5;
        LocalDate firstEntryAddDate = LocalDate.now().minusDays(numberOfDays);
        Entry firstEntry = new Entry(1, new ArrayList<>(), firstEntryAddDate);
        CyclicEntryPrototype firstPrototype = new IntervalCyclicEntryPrototype(firstEntry, numberOfDays);


        int dayOfMonth = LocalDate.now().getDayOfMonth();
        LocalDate secondEntryAddDate = LocalDate.now().minusMonths(1);
        Entry secondEntry = new Entry(2, new ArrayList<>(), secondEntryAddDate);
        CyclicEntryPrototype secondPrototype = new MonthlyCyclicEntryPrototype(secondEntry, dayOfMonth);

        EntryList entries = new EntryList();
        entries.addEntry(firstEntry);
        entries.addEntry(secondEntry);

        CyclicPrototypeList prototypes = new CyclicPrototypeList();
        prototypes.addPrototype(firstPrototype);
        prototypes.addPrototype(secondPrototype);

        Scheduler scheduler = new Scheduler(entries, prototypes);

        // Act
        scheduler.addPeriodicEntries();

        // Assert
        assertEquals(4, entries.getEntries().size());
        assertTrue(entries.getEntries().contains(firstEntry.cloneAt(LocalDate.now())));
        assertTrue(entries.getEntries().contains(secondEntry.cloneAt(LocalDate.now())));
    }

    @Test
    public void SchedulerAddPeriodicEntriesWhenPeriodicEntriesArePresentButShouldNotBeDuplicatedTodayTest() {
        // Arrange
        int numberOfDays = 5;
        LocalDate firstEntryAddDate = LocalDate.now().minusDays(numberOfDays - 1);
        Entry firstEntry = new Entry(1, new ArrayList<>(), firstEntryAddDate);
        CyclicEntryPrototype firstPrototype = new IntervalCyclicEntryPrototype(firstEntry, numberOfDays);


        int dayOfMonth = LocalDate.now().getDayOfMonth();
        LocalDate secondEntryAddDate = LocalDate.now().minusMonths(1).plusDays(1);
        Entry secondEntry = new Entry(2, new ArrayList<>(), secondEntryAddDate);
        CyclicEntryPrototype secondPrototype = new MonthlyCyclicEntryPrototype(secondEntry, dayOfMonth + 1);

        EntryList entries = new EntryList();
        entries.addEntry(firstEntry);
        entries.addEntry(secondEntry);

        CyclicPrototypeList prototypes = new CyclicPrototypeList();
        prototypes.addPrototype(firstPrototype);
        prototypes.addPrototype(secondPrototype);

        Scheduler scheduler = new Scheduler(entries, prototypes);

        // Act
        scheduler.addPeriodicEntries();

        // Assert
        assertEquals(2, entries.getEntries().size());
        assertFalse(entries.getEntries().contains(firstEntry.cloneAt(LocalDate.now())));
        assertFalse(entries.getEntries().contains(secondEntry.cloneAt(LocalDate.now())));
    }

    @Test
    public void SchedulerAddPeriodicEntriesNothingIsAddedWhenEntriesDuplicatedTodayAreAlreadyPresentTest() {
        // Arrange
        int numberOfDays = 5;
        LocalDate firstEntryAddDate = LocalDate.now().minusDays(numberOfDays);
        Entry firstEntry = new Entry(1, new ArrayList<>(), firstEntryAddDate);
        CyclicEntryPrototype firstPrototype = new IntervalCyclicEntryPrototype(firstEntry, numberOfDays);


        int dayOfMonth = LocalDate.now().getDayOfMonth();
        LocalDate secondEntryAddDate = LocalDate.now().minusMonths(1);
        Entry secondEntry = new Entry(2, new ArrayList<>(), secondEntryAddDate);
        CyclicEntryPrototype secondPrototype = new MonthlyCyclicEntryPrototype(secondEntry, dayOfMonth);

        EntryList entries = new EntryList();
        entries.addEntry(firstEntry);
        entries.addEntry(secondEntry);

        CyclicPrototypeList prototypes = new CyclicPrototypeList();
        prototypes.addPrototype(firstPrototype);
        prototypes.addPrototype(secondPrototype);

        Scheduler scheduler = new Scheduler(entries, prototypes);
        scheduler.addPeriodicEntries();

        // Act
        scheduler.addPeriodicEntries();

        // Assert
        assertEquals(4, entries.getEntries().size());
        assertTrue(entries.getEntries().contains(firstEntry.cloneAt(LocalDate.now())));
        assertTrue(entries.getEntries().contains(secondEntry.cloneAt(LocalDate.now())));
    }

    @Test
    public void SchedulerAddPeriodicEntriesEntriesAreAddedOnMonthEndEdgeCaseTest() {
        // eg. 31th of march should be treated as 28th in February
        // but how to test it when scheduler relies internally on LocalDate.now()?
    }
}
