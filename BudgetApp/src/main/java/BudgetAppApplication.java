import menu.Menu;
import menu.MenuCategory;
import menu.MenuCyclicEntry;
import menu.MenuEntry;
import model.CategoryList;
import model.Entry;
import model.EntryList;
import model.cyclic.CyclicEntryPrototype;
import model.cyclic.CyclicPrototypeList;
import scheduling.Scheduler;

import java.util.Scanner;

public class BudgetAppApplication {

    public static void main(String[] args) throws Exception {
        System.out.println("Budget application has started!");

        EntryList entryList = new EntryList();
        CategoryList categoryList = new CategoryList();
        CyclicPrototypeList prototypeList = new CyclicPrototypeList();

        Scheduler scheduler = new Scheduler(entryList, prototypeList);
        scheduler.runScheduler();

        Menu menu = new Menu();
        MenuEntry menuEntry = new MenuEntry();
        MenuCategory menuCategory = new MenuCategory();
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry();

        int choice = 0;
        do {
            System.out.println(menu.show());
            Scanner keyboard = new Scanner(System.in);
            String line = keyboard.next();
            choice = menu.read(line);
            switch (choice) {
                case 1:
                    System.out.println(menuEntry.show());
                    line = keyboard.next();
                    int entryChoice = menuEntry.read(line);
                    Entry entry = menuEntry.showInputsByChoice(entryChoice);
                    entryList.addEntry(entry);
                    break;
                case 2:
                    System.out.println(menuCyclicEntry.show());
                    line = keyboard.next();
                    int cyclicChoice = menuCyclicEntry.read(line);
                    CyclicEntryPrototype prototype = menuCyclicEntry.showInputsByChoice(cyclicChoice);
                    entryList.addEntry(prototype.getPrototypeEntry());
                    prototypeList.addPrototype(prototype);
                    break;
                case 3:
                    System.out.println(menuCategory.show());
                    line = keyboard.next();
                    int categoryChoice = menuCategory.read(line);
                    String category = menuCategory.showInputsByChoice(categoryChoice);
                    categoryList.addCategory(category);
                    break;
                case 4:
                    System.out.println(entryList);
                    break;
                case 5:
                    System.out.println(categoryList);
                    break;
                default:
                    break;
            }
        } while (choice != 0);

        scheduler.stopScheduler();
    }
}
