package menu;

import exception.InvalidInputException;
import model.Entry;
import model.cyclic.CyclicEntryPrototype;
import model.cyclic.IntervalCyclicEntryPrototype;
import model.cyclic.MonthlyCyclicEntryPrototype;

import java.io.InputStream;
import java.util.Scanner;

public class MenuCyclicEntry extends AbstractMenu {
    public MenuCyclicEntry() {
        this.scanner= new Scanner(System.in);
    }

    public MenuCyclicEntry(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
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
                "Please select 1-2!";
    }

    public int getCyclicIntervalInDaysInputShow() {
        System.out.println("...now, provide cyclic interval in days:");
        String line = scanner.next();
        return Integer.parseInt(line);
    }

    public int getCyclicDayOfMonthInputShow() {
        System.out.println("...now, provide cyclic day of month:");
        String line = scanner.next();
        return Integer.parseInt(line);
    }

    public Entry getEntryInputShow() throws InvalidInputException {
        MenuEntry menuEntry = new MenuEntry(scanner);

        System.out.println("First, input entry itself...");
        System.out.println(menuEntry.show());
        String line = scanner.nextLine();
        int entryChoice = menuEntry.read(line);
        return menuEntry.showInputsByChoice(entryChoice);
    }

    public CyclicEntryPrototype showInputsByChoice(int choice) throws InvalidInputException {
        Entry entry = getEntryInputShow();

        CyclicEntryPrototype prototype;
        switch(choice) {
            case 2:
                int dayOfMonth = getCyclicDayOfMonthInputShow();
                prototype = new MonthlyCyclicEntryPrototype(entry, dayOfMonth);
                break;
            case 1:
            default:
                int intervalInDays = getCyclicIntervalInDaysInputShow();
                prototype = new IntervalCyclicEntryPrototype(entry, intervalInDays);
                break;
        }
        return prototype;
    }
}
