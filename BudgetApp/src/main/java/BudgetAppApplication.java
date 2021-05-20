import menu.Menu;
import menu.MenuCategory;
import menu.MenuEntry;
import model.CategoryList;
import model.Entry;
import model.EntryList;
import scheduling.Scheduler;

import java.util.Scanner;

public class BudgetAppApplication {

    public static void main(String[] args) throws Exception {
        System.out.println("Budget application has started!");

        EntryList entryList = new EntryList();
        CategoryList categoryList = new CategoryList();

        Scheduler scheduler = new Scheduler(entryList);
        scheduler.runScheduler();

        Menu menu = new Menu();
        MenuEntry menuEntry = new MenuEntry();
        MenuCategory menuCategory = new MenuCategory();
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
                    System.out.println(menuCategory.show());
                    line = keyboard.next();
                    int categoryChoice = menuCategory.read(line);
                    String category = menuCategory.showInputsByChoice(categoryChoice);
                    categoryList.addCategory(category);
                    break;
                case 3:
                    System.out.print(entryList.getOrderedEntriesString());
                    if (entryList.length() == 0) {
                        System.out.println("No entries to remove!");
                    }
                    else {
                        System.out.printf("Select an entry from 1 to %d to be removed:\n", entryList.length());
                        line = keyboard.next();
                        int line_numeric = Integer.parseInt(line);
                        if (line_numeric >= 1 && line_numeric <= entryList.length()) {
                            entryList.removeEntry(Integer.parseInt(line)-1);
                            System.out.println("Entry removed!");
                        }
                        else {
                            System.out.println("Invalid entry number!");
                        }
                    }
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
