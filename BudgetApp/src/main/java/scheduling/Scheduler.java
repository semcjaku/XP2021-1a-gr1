package scheduling;

import model.EntryList;
import model.Wallet;
import model.WalletList;
import model.cyclic.CyclicEntryPrototype;
import model.cyclic.CyclicPrototypeList;

import java.time.LocalDate;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.HOURS;

public class Scheduler {
    private final WalletList walletList;

    private final ScheduledExecutorService scheduler;


    public Scheduler(WalletList walletList) {
        this.walletList = walletList;
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
        walletList
                .getWallets()
                .stream()
                .collect(Collectors.toMap(Wallet::getEntryList, Wallet::getCyclicPrototypes))
                .forEach((entryList, prototypes) -> prototypes
                                .getPrototypes()
                                .stream()
                                .filter(CyclicEntryPrototype::shouldBeReplicatedToday)
                                .map(prototype -> prototype.generateNewEntry(LocalDate.now()))
                                .forEach(entryList::addEntry)
                );
    }
}
