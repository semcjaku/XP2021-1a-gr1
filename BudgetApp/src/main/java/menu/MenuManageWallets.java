package menu;

import service.WalletService;

import java.io.InputStream;

public class MenuManageWallets extends AbstractMenu {

    private WalletService walletService;

    public MenuManageWallets(WalletService walletService) {
        super(walletService.getScanner());
        this.walletService = walletService;
    }

//    public MenuManageWallets() {
//       super(System.in);
//    }
//
    public MenuManageWallets(InputStream inputStream) {
        super(inputStream);
    }

//    public MenuManageWallets(Scanner scanner) {
//        super(scanner);
//    }

    @Override
    public int getMinInputNumber() {
        return 0;
    }

    @Override
    public int getMaxInputNumber() {
        return 5;
    }

    @Override
    public String show() {
        return "\nMENU MANAGE WALLETS\n" +
                "1. Add wallet\n" +
                "2. Archive wallet\n" +
                "3. Rename wallet\n" +
                "4. Show logged in user wallets\n" +
                "5. Add user to wallet\n" +
                "0. Exit";
    }

    public void executeChoice(int choice) {
        switch (choice) {
            case 1:
                hndCreateWallet();
                break;
            case 2:
                hndArchiveWallet();
                break;
            case 3:
                hndRenameWallet();
                break;
            case 4:
                hndShowUserWallets();
                break;
            case 5:
                hndAddUserToWallet();
            case 0:
                break;
        }
    }

    private void hndAddUserToWallet() {
        String userEmailToAdd = getEmailFromUser();
        if (walletService.hasUserAccess(userEmailToAdd)) {
            System.out.println("User "+userEmailToAdd+" already has access to wallet "+walletService.getCurrentWalletName());
            return;
        }
        walletService.getWalletByName(walletService.getCurrentWalletName()).addSharedUserEmail(userEmailToAdd);
    }

    private void hndShowUserWallets() {
        System.out.println(showUserWallets());
    }

    public String showUserWallets() {
        return new StringBuilder("Available wallets for user ")
                .append(walletService.getLoggedInUserName()).append(": ")
                .append(walletService.getUserWalletsNames(walletService.getLoggedInUserName())).toString();
    }

    private void hndRenameWallet() {
        String oldWalletName = getWalletNameFromPickMenu();
        String newWalletName = getNewWalletNameFromUser();
        walletService.renameWallet(oldWalletName,newWalletName);
    }

    private void hndArchiveWallet() {
        String walletName = getWalletNameFromPickMenu();
        walletService.archiveWallet(walletName);
        //TODO if archived wallet was current wallet then force change of wallet
    }

    private void hndCreateWallet() {
        String newWalletName = getNewWalletNameFromUser();
        walletService.addWallet(newWalletName, walletService.getLoggedInUserName());
        walletService.setCurrentWalletName(newWalletName);
        System.out.println("Current wallet name:"+newWalletName);
    }

    private String getNewWalletNameFromUser() {
        String newWalletName;
        do {
            newWalletName = getStringFromUser("Provide wallet name:");
            if (walletService.checkIfWalletExists(newWalletName)) {
                System.out.println("Wallet with this name already exists!");
                newWalletName = "";
            }
        } while (newWalletName.equals(""));
        return newWalletName;
    }

    private String getWalletNameFromPickMenu() {
        MenuPickWallet menuPickWallet = new MenuPickWallet(walletService);
        System.out.println(menuPickWallet.show());
        int choice = menuPickWallet.getChoiceFromUser();
        return menuPickWallet.getWalletName(choice);
    }


    private String getEmailFromUser() {
        return getStringFromUser("Provide email of user to add:");
    }


}
