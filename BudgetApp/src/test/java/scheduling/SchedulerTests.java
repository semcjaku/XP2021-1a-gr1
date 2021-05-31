package scheduling;

import model.Wallet;
import model.WalletList;
import model.CyclicEntryPrototype;
import model.Entry;
import model.EntryList;
import model.CyclicPrototypeList;
import model.IntervalCyclicEntryPrototype;
import model.MonthlyCyclicEntryPrototype;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SchedulerTests {
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

        WalletList walletList = new WalletList();
        Wallet wallet = walletList.getWallets().get(0);

        EntryList entries = wallet.getEntryList();
        entries.addEntry(firstEntry);
        entries.addEntry(secondEntry);

        CyclicPrototypeList prototypes = wallet.getCyclicPrototypes();
        prototypes.addPrototype(firstPrototype);
        prototypes.addPrototype(secondPrototype);

        Scheduler scheduler = new Scheduler(walletList);

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

        int dayOfMonth = LocalDate.now().plusDays(1).getDayOfMonth();
        LocalDate secondEntryAddDate = LocalDate.now().plusDays(1).minusMonths(1);
        Entry secondEntry = new Entry(2, new ArrayList<>(), secondEntryAddDate);
        CyclicEntryPrototype secondPrototype = new MonthlyCyclicEntryPrototype(secondEntry, dayOfMonth);

        WalletList walletList = new WalletList();
        Wallet wallet = walletList.getWallets().get(0);

        EntryList entries = wallet.getEntryList();
        entries.addEntry(firstEntry);
        entries.addEntry(secondEntry);

        CyclicPrototypeList prototypes = wallet.getCyclicPrototypes();
        prototypes.addPrototype(firstPrototype);
        prototypes.addPrototype(secondPrototype);

        Scheduler scheduler = new Scheduler(walletList);

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

        WalletList walletList = new WalletList();
        Wallet wallet = walletList.getWallets().get(0);

        EntryList entries = wallet.getEntryList();
        entries.addEntry(firstEntry);
        entries.addEntry(secondEntry);

        CyclicPrototypeList prototypes = wallet.getCyclicPrototypes();
        prototypes.addPrototype(firstPrototype);
        prototypes.addPrototype(secondPrototype);

        Scheduler scheduler = new Scheduler(walletList);
        scheduler.addPeriodicEntries();

        // Act
        scheduler.addPeriodicEntries();

        // Assert
        assertEquals(4, entries.getEntries().size());
        assertTrue(entries.getEntries().contains(firstEntry.cloneAt(LocalDate.now())));
        assertTrue(entries.getEntries().contains(secondEntry.cloneAt(LocalDate.now())));
    }
}
