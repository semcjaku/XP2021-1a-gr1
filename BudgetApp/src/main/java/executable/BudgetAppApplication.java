package executable;

import menu.*;
import model.*;
import scheduling.Scheduler;

import service.UserService;
import service.WalletService;
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
            choice = menuUser.getChoiceFromUser();

            switch (choice) {
                case 1:
                    User inputUser = menuUser.showInputsByChoice(choice);
                    logedInUser = userService.login(inputUser.getEmail(), inputUser.getPassword());
                    if (logedInUser == null) {
                        System.out.println("Invalid user data! Try again.");
                    }
                    break;
                case 2:
                    User inputRegisterUser = menuUser.showInputsByChoice(choice);
                    logedInUser = userService.register(inputRegisterUser.getEmail(), inputRegisterUser.getPassword());
                    if (logedInUser == null) {
                        System.out.println("User with this email already exists! Try again.");
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

        WalletService walletService = new WalletService();
        walletService.loadWalletsOnStart();

        String walletName = "";

        Scheduler scheduler = new Scheduler(walletService.getWallets());
        scheduler.runScheduler();

        Menu menu = new Menu();
        MenuEntry menuEntry = new MenuEntry();
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry();
        MenuCategory menuCategory = new MenuCategory();
        MenuModifyEntries menuModEntries = new MenuModifyEntries();
        MenuModifyCyclicEntries menuModCyclicEntries = new MenuModifyCyclicEntries();
        MenuPickWallet menuPickWallet = new MenuPickWallet(walletService.getWallets());
        MenuManageWallets menuManageWallets = new MenuManageWallets(walletService.getWallets());
        MenuPickOrCreateWallet menuPickOrCreateWallet = new MenuPickOrCreateWallet();

        do {
            System.out.println(menuPickOrCreateWallet.show());
            choice = menuPickOrCreateWallet.getChoiceFromUser();
            walletName = menuPickOrCreateWallet.executeActions(choice,logedInUser.getEmail(),walletService);
        } while(walletName.isEmpty() && choice != 0);
        if (choice == 0) return;


        int mainChoice;
        do {
            Scanner keyboard = new Scanner(System.in);
            System.out.println(menu.show());
            mainChoice = menu.getChoiceFromUser();
            switch (mainChoice) {
                case 1:
                    System.out.println(menuEntry.show());
                    int entryChoice = menuEntry.getChoiceFromUser();
                    Entry entry = menuEntry.showInputsByChoice(entryChoice);
                    walletService.addEntry(walletName,entry);
                    break;
                case 2:
                    System.out.println(menuCyclicEntry.show());
                    int cyclicChoice = menuCyclicEntry.getChoiceFromUser();
                    CyclicEntryPrototype prototype = menuCyclicEntry.showInputsByChoice(cyclicChoice);
                    walletService.addEntry(walletName,prototype.getPrototypeEntry());
                    walletService.addCyclicPrototype(walletName,prototype);
                    break;
                case 3:
                    System.out.println(menuCategory.show());
                    int categoryChoice = menuCategory.getChoiceFromUser();
                    menuCategory.executeActions(categoryChoice,walletName,walletService);
                    break;
                case 4:
                    walletService.removeEntry(walletName, keyboard);
                    break;
                case 5:
                    walletService.removeCyclicEntry(walletName, keyboard);
                    break;
                case 6:
                    walletService.modifyEntry(walletName, keyboard);
                    break;
                case 7:
                    walletService.modifyCyclicEntry(walletName, keyboard);
                    break;
                case 8:
                    System.out.println(walletService.getEntryList(walletName));
                    break;
                case 9:
                    System.out.println(walletService.getCyclicPrototypes(walletName));
                    break;
                case 10:
                    System.out.println(walletService.getCategoryList(walletName));
                    break;
                case 11:
                    System.out.println(menuPickWallet.show());
                    choice = menuPickWallet.getChoiceFromUser();
                    walletName = menuPickWallet.executeActions(choice);
                    break;
                case 12:
                    System.out.println(menuManageWallets.show());
                    choice = menuManageWallets.getChoiceFromUser();
                    menuManageWallets.executeActions(choice,logedInUser.getEmail(),walletService);
                    break;
                case 13:
                    System.out.println(menuManageWallets.showUserWallets(logedInUser.getEmail(),walletService));
                    break;

                default:
                    break;
            }
        } while (mainChoice != 0);

        scheduler.stopScheduler();
        userService.saveOnStop();
        walletService.saveOnStop();
    }

}
