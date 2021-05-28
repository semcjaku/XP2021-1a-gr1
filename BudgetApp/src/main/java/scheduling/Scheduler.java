package scheduling;

import model.cyclic.CyclicEntryPrototype;
import model.EntryList;
import model.cyclic.CyclicPrototypeList;

import java.time.LocalDate;
import java.util.List;
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
