package executable;

import exception.InvalidInputException;
import menu.*;
import model.*;
import model.cyclic.CyclicEntryPrototype;
import model.cyclic.CyclicPrototypeList;
import scheduling.Scheduler;
import service.UserService;

import java.util.Scanner;

public class BudgetAppApplication {
    public static User logedInUser = null;

    public static void main(String[] args) throws Exception {
        System.out.println("Budget application has started!");

        UserService userService = new UserService();
        userService.loadUsersOnStart();

        MenuUser menuUser = new MenuUser();

        int choice;
        do {
            System.out.println(menuUser.show());
            Scanner keyboard = new Scanner(System.in);
            String line = keyboard.next();
            choice = menuUser.read(line);
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
            userService.saveOnStop();
            return;
        }

        WalletList walletList = new WalletList();
        Wallet wallet = null;

        Scheduler scheduler = new Scheduler(walletList);
        scheduler.runScheduler();

        Menu menu = new Menu();
        MenuEntry menuEntry = new MenuEntry();
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry();
        MenuCategory menuCategory = new MenuCategory();
        MenuModifyEntries menuModEntries = new MenuModifyEntries();
        MenuModifyCyclicEntries menuModCyclicEntries = new MenuModifyCyclicEntries();
        MenuPickWallet menuPickWallet = new MenuPickWallet(walletList);
        MenuManageWallets menuManageWallets = new MenuManageWallets(walletList);

        do {
            System.out.println(menuPickWallet.show());
            Scanner keyboard = new Scanner(System.in);
            String line = keyboard.next();
            try {
                choice = menu.read(line);
                wallet = menuPickWallet.executeActions(choice);
            }
            catch (InvalidInputException e) {
                System.out.println("Invalid wallet number provided");
            }
        } while(wallet == null);

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
                    wallet.getEntryList().addEntry(entry);
                    break;
                case 2:
                    System.out.println(menuCyclicEntry.show());
                    line = keyboard.next();
                    int cyclicChoice = menuCyclicEntry.read(line);
                    CyclicEntryPrototype prototype = menuCyclicEntry.showInputsByChoice(cyclicChoice);
                    wallet.getEntryList().addEntry(prototype.getPrototypeEntry());
                    wallet.getCyclicPrototypes().addPrototype(prototype);
                    break;
                case 3:
                    System.out.println(menuCategory.show());
                    line = keyboard.next();
                    int categoryChoice = menuCategory.read(line);
                    String category = menuCategory.showInputsByChoice(categoryChoice);
                    wallet.getCategoryList().addCategory(category);
                    break;
                case 4:
                    System.out.print(wallet.getEntryList().getOrderedEntriesString());
                    if (wallet.getEntryList().length() == 0) {
                        System.out.println("No entries to remove!");
                    }
                    else {
                        System.out.printf("Select an entry from 1 to %d to be removed:\n", wallet.getEntryList().length());
                        line = keyboard.next();
                        int line_numeric = Integer.parseInt(line);
                        if (line_numeric >= 1 && line_numeric <= wallet.getEntryList().length()) {
                            wallet.getEntryList().removeEntry(Integer.parseInt(line) - 1);
                            System.out.println("Entry removed!");
                        }
                        else {
                            System.out.println("Invalid entry number!");
                        }
                    }
                    break;
                case 5:
                    System.out.print(wallet.getCyclicPrototypes().getOrderedEntriesString());
                    if (wallet.getCyclicPrototypes().length() == 0) {
                        System.out.println("No cyclic entries to remove!");
                    }
                    else {
                        System.out.printf("Select an cyclic entry from 1 to %d to be removed:\n", wallet.getCyclicPrototypes().length());
                        line = keyboard.next();
                        int line_numeric = Integer.parseInt(line);
                        if (line_numeric >= 1 && line_numeric <= wallet.getCyclicPrototypes().length()) {
                            wallet.getCyclicPrototypes().removePrototype(Integer.parseInt(line) - 1);
                            System.out.println("Cyclic Entry removed!");
                        }
                        else {
                            System.out.println("Invalid cyclic entry number!");
                        }
                    }
                    break;
                case 6:
                    System.out.print(wallet.getEntryList().getOrderedEntriesString());
                    if (wallet.getEntryList().length() == 0) {
                        System.out.println("No entries to modify!");
                    }
                    else {
                        System.out.printf("Select an entry from 1 to %d to be modified:\n", wallet.getEntryList().length());
                        line = keyboard.next();
                        int line_numeric = Integer.parseInt(line);
                        if (line_numeric >= 1 && line_numeric <= wallet.getEntryList().length()) {
                            while (true) {
                                System.out.println("Chosen entry:");
                                System.out.print(wallet.getEntryList().getEntry(line_numeric-1));
                                System.out.println(menuModEntries.show());
                                line = keyboard.next();
                                if(Integer.parseInt(line) == 0)
                                    break;
                                menuModEntries.executeActions(Integer.parseInt(line), wallet.getEntryList().getEntry(line_numeric - 1));
                            }
                        }
                        else {
                            System.out.println("Invalid entry number!");
                        }
                    }
                    break;
                case 7:
                    System.out.print(wallet.getCyclicPrototypes().getOrderedEntriesString());
                    if (wallet.getCyclicPrototypes().length() == 0) {
                        System.out.println("No cyclic entries to modify!");
                    }
                    else {
                        System.out.printf("Select a cyclic entry from 1 to %d to be modified:\n", wallet.getCyclicPrototypes().length());
                        line = keyboard.next();
                        int line_numeric = Integer.parseInt(line);
                        if (line_numeric >= 1 && line_numeric <= wallet.getCyclicPrototypes().length()) {
                            while (true) {
                                System.out.println("Chosen entry:");
                                System.out.print(wallet.getCyclicPrototypes().getPrototypes().get(line_numeric-1));
                                System.out.println(menuModCyclicEntries.show());
                                line = keyboard.next();
                                if(Integer.parseInt(line) == 0)
                                    break;
                                menuModCyclicEntries.executeActions(Integer.parseInt(line), wallet.getCyclicPrototypes().getPrototypes().get(line_numeric - 1));
                            }
                        }
                        else {
                            System.out.println("Invalid entry number!");
                        }
                    }
                    break;
                case 8:
                    System.out.println(wallet.getEntryList());
                    break;
                case 9:
                    System.out.println(wallet.getCyclicPrototypes());
                    break;
                case 10:
                    System.out.println(wallet.getCategoryList());
                    break;
                case 11:
                    do {
                        System.out.println(menuPickWallet.show());
                        line = keyboard.next();
                        try {
                            choice = menu.read(line);
                            wallet = menuPickWallet.executeActions(choice);
                        }
                        catch (InvalidInputException e) {
                            System.out.println("Invalid wallet number provided");
                        }
                    } while(wallet == null);
                    break;
                case 12:
                    System.out.println(menuManageWallets.show());
                    line = keyboard.next();
                    int walletManageChoice = menuManageWallets.read(line);
                    menuManageWallets.showInputsByChoice(walletManageChoice);
                    break;
                default:
                    break;
            }
        } while (choice != 0);

        scheduler.stopScheduler();
        userService.saveOnStop();
    }
}
