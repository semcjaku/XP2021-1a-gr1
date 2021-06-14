package main.systems;

import menu.MenuPickOrCreateWallet;
import service.WalletService;

public class PickOrCreateWalletSystem {

    public static void run (WalletService walletService) {
        int choice;

        MenuPickOrCreateWallet menuPickOrCreateWallet = new MenuPickOrCreateWallet(walletService);

        do {
            System.out.println(menuPickOrCreateWallet.show());
            choice = menuPickOrCreateWallet.getChoiceFromUser();
            menuPickOrCreateWallet.executeChoice(choice);
        } while (walletService.getCurrentWalletName().isEmpty() && choice != 0);
    }
}
