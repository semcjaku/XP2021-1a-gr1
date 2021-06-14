package main;

import main.systems.MenuUserSystem;
import main.systems.PickOrCreateWalletSystem;
import model.Config;
import model.User;
import scheduling.Scheduler;
import service.ConfigService;
import service.UserService;
import service.WalletService;
import main.systems.ConfigSystem;
import main.systems.MainWalletSystem;

import java.util.Scanner;

public class BudgetApp {
    public String[] runOptions;
    public User loggedInUser = null;
    public int choice;

    public BudgetApp(String[] args) {
        this.runOptions = args;
    }

    public void start() throws Exception {

        System.out.println("Budget application has started!");

        Scanner keyboard = new Scanner(System.in);

        Config currentConfig = ConfigService.readConfig(runOptions);
        currentConfig = ConfigSystem.run(currentConfig);

        UserService userService = new UserService();
        userService.loadUsersOnStart(currentConfig.getUsersDbPath());

        loggedInUser = MenuUserSystem.run(userService);

        if (loggedInUser == null) {
            userService.saveOnStop(currentConfig.getUsersDbPath());
            return;
        }

        WalletService walletService = new WalletService(keyboard);
        walletService.loadWalletsOnStart(currentConfig.getWalletListPath());
        walletService.setLoggedInUser(loggedInUser.getEmail());

        Scheduler scheduler = new Scheduler(walletService.getWallets());
        scheduler.runScheduler();

        PickOrCreateWalletSystem.run(walletService);

        MainWalletSystem.run(walletService);

        scheduler.stopScheduler();
        userService.saveOnStop(currentConfig.getUsersDbPath());
        walletService.saveOnStop(currentConfig.getWalletListPath());
    }

}
