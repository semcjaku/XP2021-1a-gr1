package menu;


import service.WalletService;
import java.io.InputStream;

public class MenuPickOrCreateWallet extends AbstractMenu {

    private WalletService walletService;

    public MenuPickOrCreateWallet(InputStream inputStream) {
        super(inputStream);
    }
//
//    public MenuPickOrCreateWallet() {
//        super(System.in);
//    }

    public MenuPickOrCreateWallet(WalletService walletService) {
        super(walletService.getScanner());
        this.walletService = walletService;
    }

    @Override
    public int getMinInputNumber() {
        return 0;
    }

    @Override
    public int getMaxInputNumber() {
        return 2;
    }

    @Override
    public String show() {
        return "\nMENU PICK OR CREATE WALLET\n" +
                "1.Create wallet\n" +
                "2.Pick wallet\n" +
                "0.Exit";
    }

    public void executeChoice(Integer choice) {
        switch (choice) {
            case 0:
                break;
            case 1:
                hndCreateWallet();
                break;
            case 2:
                hndPickWallet();
                break;
        }
    }

//    private String pickWallet(String ownerEmail, WalletService walletService) {
//        if (walletService.getUserWallets(ownerEmail).isEmpty()) {
//            System.out.println("User " + ownerEmail + " doesn't have any wallets!");
//            return "";
//        }
//
//        MenuPickWallet menuPickWallet = new MenuPickWallet(this.scanner,walletService.getWallets());
//        System.out.println(menuPickWallet.show());
//        int choice = menuPickWallet.getChoiceFromUser();
//        String walletName = menuPickWallet.executeChoice(choice);
//
//        return walletName;
//
//    }

    public void hndPickWallet() {
        if (walletService.getUserWallets(walletService.getLoggedInUserName()).isEmpty()) {
            System.out.println("User " + walletService.getLoggedInUserName() + " doesn't have any wallets!");
            return;
        }

        MenuPickWallet menuPickWallet = new MenuPickWallet(this.walletService);
        System.out.println(menuPickWallet.show());
        int choice = menuPickWallet.getChoiceFromUser();
        menuPickWallet.executeChoice(choice);
    }

    public void hndCreateWallet() {
        MenuManageWallets menuManageWallets = new MenuManageWallets(this.walletService);
        menuManageWallets.executeChoice(1);
    }

//    private String createWallet(String ownerEmail, WalletService walletService) {
//        String walletName = "";
//        do {
//            walletName = getWalletNameFromUser();
//            if (walletService.checkIfWalletExists(walletName)) {
//                System.out.println("Wallet with name " + walletName + " already exists!");
//                walletName = "";
//            }
//        } while (walletName.equals(""));
//
//        walletService.addWallet(walletName, ownerEmail);
//        return walletName;
//    }

    public String getWalletNameFromUser() {
        return getStringFromUser("Provide name for new wallet:");
    }
}
