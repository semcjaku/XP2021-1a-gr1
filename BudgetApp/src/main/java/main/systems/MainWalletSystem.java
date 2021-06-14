package main.systems;

import menu.Menu;
import service.WalletService;

public class MainWalletSystem {

    public static void run(WalletService walletService) {
        Menu menu = new Menu();

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
    }
}
