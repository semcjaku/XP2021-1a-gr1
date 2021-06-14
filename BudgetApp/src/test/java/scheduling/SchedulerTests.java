package scheduling;

import model.*;
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
        walletList.addWallet("wallet1","user1");
        Wallet wallet = walletList.getWallets().get(0);

        ListManager<Entry> entries = wallet.getEntryList();
        entries.add(firstEntry);
        entries.add(secondEntry);

        CyclicPrototypeList prototypes = wallet.getCyclicPrototypes();
        prototypes.add(firstPrototype);
        prototypes.add(secondPrototype);

        Scheduler scheduler = new Scheduler(walletList);

        // Act
        scheduler.addPeriodicEntries();

        // Assert
        assertEquals(4, entries.asList().size());
        assertTrue(entries.asList().contains(firstEntry.cloneAt(LocalDate.now())));
        assertTrue(entries.asList().contains(secondEntry.cloneAt(LocalDate.now())));
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
        walletList.addWallet("wallet1","user1");
        Wallet wallet = walletList.getWallets().get(0);

        ListManager<Entry> entries = wallet.getEntryList();
        entries.add(firstEntry);
        entries.add(secondEntry);

        CyclicPrototypeList prototypes = wallet.getCyclicPrototypes();
        prototypes.add(firstPrototype);
        prototypes.add(secondPrototype);

        Scheduler scheduler = new Scheduler(walletList);

        // Act
        scheduler.addPeriodicEntries();

        // Assert
        assertEquals(2, entries.asList().size());
        assertFalse(entries.asList().contains(firstEntry.cloneAt(LocalDate.now())));
        assertFalse(entries.asList().contains(secondEntry.cloneAt(LocalDate.now())));
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
        walletList.addWallet("wallet1","user1");
        Wallet wallet = walletList.getWallets().get(0);

        ListManager<Entry> entries = wallet.getEntryList();
        entries.add(firstEntry);
        entries.add(secondEntry);

        CyclicPrototypeList prototypes = wallet.getCyclicPrototypes();
        prototypes.add(firstPrototype);
        prototypes.add(secondPrototype);

        Scheduler scheduler = new Scheduler(walletList);
        scheduler.addPeriodicEntries();

        // Act
        scheduler.addPeriodicEntries();

        // Assert
        assertEquals(4, entries.asList().size());
        assertTrue(entries.asList().contains(firstEntry.cloneAt(LocalDate.now())));
        assertTrue(entries.asList().contains(secondEntry.cloneAt(LocalDate.now())));
    }
}
