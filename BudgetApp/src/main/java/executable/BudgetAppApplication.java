package executable;

import menu.Menu;
import menu.MenuConfig;
import menu.MenuPickOrCreateWallet;
import menu.MenuUser;
import model.Config;
import model.User;
import scheduling.Scheduler;
import service.ConfigService;
import service.UserService;
import service.WalletService;

import java.util.Scanner;

public class BudgetAppApplication {
    public static User loggedInUser = null;

    public static void main(String[] args) throws Exception {

        System.out.println("Budget application has started!");

        Scanner keyboard = new Scanner(System.in);

        Config currentConfig = ConfigService.readConfig(args);
        int choice;

        MenuConfig menuConfig = new MenuConfig();

        do {
            System.out.println(menuConfig.show());
            choice = menuConfig.getChoiceFromUser();

            switch (choice) {
                case 1:
                    break;
                case 2:
                    System.out.println(currentConfig);
                    break;
                case 3:
                    Config newConfig = menuConfig.getNewConfigFromUser();
                    ConfigService.saveNewConfig(newConfig);
                    System.out.println("Successfully saved new config");
                    break;
                case 0:
                    break;
            }
        } while (choice != 0 && choice != 1);

        UserService userService = new UserService();
        userService.loadUsersOnStart(currentConfig.getUsersDbPath());

        MenuUser menuUser = new MenuUser();

        do {
            System.out.println(menuUser.show());
            choice = menuUser.getChoiceFromUser();

            switch (choice) {
                case 1:
                    User inputUser = menuUser.showInputsByChoice(choice);
                    loggedInUser = userService.login(inputUser.getEmail(), inputUser.getPassword());
                    if (loggedInUser == null) {
                        System.out.println("Invalid user data! Try again.");
                    }
                    break;
                case 2:
                    User inputRegisterUser = menuUser.showInputsByChoice(choice);
                    loggedInUser = userService.register(inputRegisterUser.getEmail(), inputRegisterUser.getPassword());
                    if (loggedInUser == null) {
                        System.out.println("User with this email already exists! Try again.");
                    }
                    break;
                case 0:
                    break;
            }
        } while (choice != 0 && loggedInUser == null);

        if (loggedInUser == null) {
            userService.saveOnStop(currentConfig.getUsersDbPath());
            return;
        }

        WalletService walletService = new WalletService(keyboard);
        walletService.loadWalletsOnStart(currentConfig.getWalletListPath());
        walletService.setLoggedInUser(loggedInUser.getEmail());

        Scheduler scheduler = new Scheduler(walletService.getWallets());
        scheduler.runScheduler();

        Menu menu = new Menu();
        MenuPickOrCreateWallet menuPickOrCreateWallet = new MenuPickOrCreateWallet(walletService);

        do {
            System.out.println(menuPickOrCreateWallet.show());
            choice = menuPickOrCreateWallet.getChoiceFromUser();
            menuPickOrCreateWallet.executeChoice(choice);
        } while (walletService.getCurrentWalletName().isEmpty() && choice != 0);
        if (choice == 0) return;


        int mainChoice;
        do {
            System.out.println(menu.show());
            mainChoice = menu.getChoiceFromUser();
            switch (mainChoice) {
                case 1:
                    walletService.hndAddEntry();
                    break;
                case 2:
                    walletService.hndAddCyclicEntry();
                    break;
                case 3:
                    walletService.hndAddCategory();
                    break;
                case 4:
                    walletService.hndRemoveEntry();
                    break;
                case 5:
                    walletService.hndRemoveCyclicEntry();
                    break;
                case 6:
                    walletService.hndModifyEntry();
                    break;
                case 7:
                    walletService.hndModifyCyclicEntry();
                    break;
                case 8:
                    walletService.hndShowEntryList();
                    break;
                case 9:
                    walletService.hndShowCyclicEntryList();
                    break;
                case 10:
                    walletService.hndShowCategoryList();
                    break;
                case 11:
                    walletService.hndSwitchWallet();
                    break;
                case 12:
                    walletService.hndManageWallets();
                    break;
                default:
                    break;
            }
        } while (mainChoice != 0);

        scheduler.stopScheduler();
        userService.saveOnStop(currentConfig.getUsersDbPath());
        walletService.saveOnStop(currentConfig.getWalletListPath());
    }

}
