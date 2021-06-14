package menu;

import model.*;
import service.WalletService;

import java.io.InputStream;

public class MenuCyclicEntry extends AbstractMenu {

    private WalletService walletService;


//    public MenuCyclicEntry() {
//        super(System.in);
//    }

    public MenuCyclicEntry(InputStream inputStream) {
        super(inputStream);
    }

    public MenuCyclicEntry(WalletService walletService) {
        super(walletService.getScanner());
        this.walletService = walletService;
    }

    @Override
    public int getMinInputNumber() {
        return 1;
    }

    @Override
    public int getMaxInputNumber() {
        return 2;
    }

    @Override
    public String show() {
        return "\nMENU CYCLIC ENTRY\n" +
                "1.Add Cyclic Entry repeated at constant interval\n" +
                "2.Add Cyclic Entry repeated monthly\n" +
                "0.Exit";
    }

    public int getCyclicIntervalInDaysFromUser() {
        return getPositiveIntFromUser("...now, provide cyclic interval in days:");
//        return  getIntFromUser("...now, provide cyclic interval in days:");
    }

    public int getCyclicDayOfMonthFromUser() {
        int cyclicDOM = -1;
        do {
            try {
                cyclicDOM = getIntFromUser("...now, provide cyclic day of month:");
                validateDayOfMonth(cyclicDOM);
            } catch (IllegalDayOfMonthException e) {
                System.out.println(e.getMessage());
                cyclicDOM = -1;
            }
        } while (cyclicDOM == -1);
        return cyclicDOM;
    }

    private void validateDayOfMonth(int cyclicDayOfMonth) {
        if (cyclicDayOfMonth < 1 || cyclicDayOfMonth > 31) {
            throw new IllegalDayOfMonthException();
        }
    }

    public Entry getEntryFromUser()  {
        MenuEntry menuEntry = new MenuEntry(walletService);
        System.out.println("First, input entry itself...");
        System.out.println(menuEntry.show());
        int entryChoice = menuEntry.getChoiceFromUser();
        return menuEntry.getNewEntry(entryChoice);
    }

    public void executeChoice(int choice) {
        switch(choice) {
            case 0:
                break;
            case 1:
                hndAddCyclicEntryWithConstInterval();
                break;
            case 2:
                hndAddCyclicEntryWithMonthlyInterval();
                break;
        }
    }

    public void hndAddCyclicEntryWithConstInterval() {
        Entry entry = getEntryFromUser();
        if (entry == null) { return; }
        int intervalInDays = getCyclicIntervalInDaysFromUser();
        CyclicEntryPrototype prototype = new IntervalCyclicEntryPrototype(entry, intervalInDays);
        walletService.addEntry(walletService.getCurrentWalletName(), entry);
        walletService.addCyclicPrototype(walletService.getCurrentWalletName(), prototype);
    }

    public void hndAddCyclicEntryWithMonthlyInterval() {
        Entry entry = getEntryFromUser();
        int dayOfMonth = getCyclicDayOfMonthFromUser();
        CyclicEntryPrototype prototype = new MonthlyCyclicEntryPrototype(entry, dayOfMonth);
        walletService.addEntry(walletService.getCurrentWalletName(), entry);
        walletService.addCyclicPrototype(walletService.getCurrentWalletName(), prototype);
    }
}
