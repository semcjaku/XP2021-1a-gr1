package executable;

import menu.*;
import model.CategoryList;
import model.Entry;
import model.EntryList;
import model.User;
import model.cyclic.CyclicEntryPrototype;
import model.cyclic.CyclicPrototypeList;
import scheduling.Scheduler;
import service.UserService;

import java.util.Scanner;

public class BudgetAppApplication {
    public static User logedInUser = null;

    public static void main(String[] args) throws Exception {
        System.out.println("Budget application has started!");

        EntryList entryList = new EntryList();
        CategoryList categoryList = new CategoryList();
        CyclicPrototypeList prototypeList = new CyclicPrototypeList();

        Scheduler scheduler = new Scheduler(entryList, prototypeList);
        scheduler.runScheduler();

        UserService userService = new UserService();
        userService.loadUsersOnStart();

        Menu menu = new Menu();
        MenuUser menuUser = new MenuUser();
        MenuEntry menuEntry = new MenuEntry();
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry();
        MenuCategory menuCategory = new MenuCategory();
        MenuModifyEntries menuModEntries = new MenuModifyEntries();
        MenuModifyCyclicEntries menuModCyclicEntries = new MenuModifyCyclicEntries();

        int choice = 0;
        do {
            System.out.println(menuUser.show());
            Scanner keyboard = new Scanner(System.in);
            String line = keyboard.next();
            choice = menu.read(line);
            switch (choice) {
                case 1:
                    User inputUser = menuUser.showInputsByChoice(choice);
                    logedInUser = userService.login(inputUser.getEmail(), inputUser.getPassword());
                    if (logedInUser == null) {
                        System.out.println("Invalid user data!. Try again.");
                    }
                    break;
                case 0:
                    break;
            }
        } while(choice != 0 && logedInUser == null);

        if (logedInUser == null) {
            scheduler.stopScheduler();
            userService.saveOnStop();
            return;
        }

        choice = 0;
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
                    System.out.print(entryList.getOrderedEntriesString());
                    if (entryList.length() == 0) {
                        System.out.println("No entries to remove!");
                    }
                    else {
                        System.out.printf("Select an entry from 1 to %d to be removed:\n", entryList.length());
                        line = keyboard.next();
                        int line_numeric = Integer.parseInt(line);
                        if (line_numeric >= 1 && line_numeric <= entryList.length()) {
                            entryList.removeEntry(Integer.parseInt(line) - 1);
                            System.out.println("Entry removed!");
                        }
                        else {
                            System.out.println("Invalid entry number!");
                        }
                    }
                    break;
                case 5:
                    System.out.print(prototypeList.getOrderedEntriesString());
                    if (prototypeList.length() == 0) {
                        System.out.println("No cyclic entries to remove!");
                    }
                    else {
                        System.out.printf("Select an cyclic entry from 1 to %d to be removed:\n", prototypeList.length());
                        line = keyboard.next();
                        int line_numeric = Integer.parseInt(line);
                        if (line_numeric >= 1 && line_numeric <= prototypeList.length()) {
                            prototypeList.removePrototype(Integer.parseInt(line) - 1);
                            System.out.println("Cyclic Entry removed!");
                        }
                        else {
                            System.out.println("Invalid cyclic entry number!");
                        }
                    }
                    break;
                case 6:
                    System.out.print(entryList.getOrderedEntriesString());
                    if (entryList.length() == 0) {
                        System.out.println("No entries to modify!");
                    }
                    else {
                        System.out.printf("Select an entry from 1 to %d to be modified:\n", entryList.length());
                        line = keyboard.next();
                        int line_numeric = Integer.parseInt(line);
                        if (line_numeric >= 1 && line_numeric <= entryList.length()) {
                            while (true) {
                                System.out.println("Chosen entry:");
                                System.out.print(entryList.getEntry(line_numeric-1));
                                System.out.println(menuModEntries.show());
                                line = keyboard.next();
                                if(Integer.parseInt(line) == 0)
                                    break;
                                menuModEntries.executeActions(Integer.parseInt(line), entryList.getEntry(line_numeric - 1));
                            }
                        }
                        else {
                            System.out.println("Invalid entry number!");
                        }
                    }
                    break;
                case 7:
                    System.out.print(prototypeList.getOrderedEntriesString());
                    if (prototypeList.length() == 0) {
                        System.out.println("No cyclic entries to modify!");
                    }
                    else {
                        System.out.printf("Select a cyclic entry from 1 to %d to be modified:\n", prototypeList.length());
                        line = keyboard.next();
                        int line_numeric = Integer.parseInt(line);
                        if (line_numeric >= 1 && line_numeric <= prototypeList.length()) {
                            while (true) {
                                System.out.println("Chosen entry:");
                                System.out.print(prototypeList.getPrototypes().get(line_numeric-1));
                                System.out.println(menuModCyclicEntries.show());
                                line = keyboard.next();
                                if(Integer.parseInt(line) == 0)
                                    break;
                                menuModCyclicEntries.executeActions(Integer.parseInt(line), prototypeList.getPrototypes().get(line_numeric - 1));
                            }
                        }
                        else {
                            System.out.println("Invalid entry number!");
                        }
                    }
                    break;
                case 8:
                    System.out.println(entryList);
                    break;
                case 9:
                    System.out.println(categoryList);
                    break;
                case 10:
                    System.out.println(prototypeList);
                    break;
                default:
                    break;
            }
        } while (choice != 0);

        scheduler.stopScheduler();
        userService.saveOnStop();
    }
}
