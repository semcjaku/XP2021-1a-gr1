package scheduling;

import model.Entry;
import model.EntryList;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.HOURS;

public class Scheduler {
    private final EntryList entryList;
    private final ScheduledExecutorService scheduler;


    public Scheduler(EntryList entryList) {
        this.entryList = entryList;
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
        List<Entry> newEntries = new LinkedList<>();

        entryList
                .getEntries()
                .stream()
                .filter(Entry::isCyclic)
                .filter(this::shouldEntryBeDuplicatedToday)
                .forEach(entry -> newEntries.add(entry.createNewCyclicInstance()));

        newEntries.forEach(entryList::addEntry);
    }

    public EntryList getEntryList() {
        return entryList;
    }

    private boolean shouldEntryBeDuplicatedToday(Entry entry) {
        if (entry.isCyclicDuplicateOf() != null) {
            return false;
        }
        if (entry.getCyclicIntervalInDays() > 0) {
            return entry.getDate().plusDays(entry.getCyclicIntervalInDays()).equals(LocalDate.now());
        }
        else {
            return !entry.getDate().equals(LocalDate.now())
            && (entry.getCyclicDayOfMonth() == LocalDate.now().getDayOfMonth()
                    || (
                            entry.getCyclicDayOfMonth() > LocalDate.now().lengthOfMonth()
                    && LocalDate.now().getDayOfMonth() == LocalDate.now().lengthOfMonth())
            )
                    && entryList.getEntries().stream().noneMatch(e -> e.isCyclicDuplicateOf() == entry);
        }
    }
}
