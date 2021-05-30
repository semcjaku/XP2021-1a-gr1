package scheduling;

import model.EntryList;
import model.CyclicEntryPrototype;
import model.CyclicPrototypeList;

import java.time.LocalDate;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.HOURS;

public class Scheduler {
    private final EntryList entryList;
    private final CyclicPrototypeList cyclicEntryPrototypes;

    private final ScheduledExecutorService scheduler;


    public Scheduler(EntryList entryList, CyclicPrototypeList cyclicEntryPrototypes) {
        this.entryList = entryList;
        this.cyclicEntryPrototypes = cyclicEntryPrototypes;
        scheduler = new ScheduledThreadPoolExecutor(1);
    }

    public void runScheduler() {
        final Runnable processPeriodicEntries = this::addPeriodicEntries;
        scheduler.scheduleAtFixedRate(processPeriodicEntries, 0, 24, HOURS);
    }

    public void stopScheduler() {
        scheduler.shutdown();
    }

    public void addPeriodicEntries() {
        cyclicEntryPrototypes
                .getPrototypes()
                .stream()
                .filter(CyclicEntryPrototype::shouldBeReplicatedToday)
                .map(prototype -> prototype.generateNewEntry(LocalDate.now()))
                .forEach(entryList::addEntry);
    }

    public EntryList getEntryList() {
        return entryList;
    }
}
