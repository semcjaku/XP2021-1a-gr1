package menu;

import model.User;
import service.WalletService;

public class MenuWallet extends AbstractMenu{



    @Override
    public int getMinInputNumber() {
        return 0;
    }

    @Override
    public int getMaxInputNumber() {
        return 4;
    }

    @Override
    public String show() {
        return "\nMENU WALLET\n" +
                "1.Choose wallet\n" +
                "2.Create wallet\n" +
                "3.Add user to wallet\n" +
                "4.Remove user from wallet\n" +
                "0.Exit\n" +
                "Please select 0-4!";
    }

    public String show(int mode) {
        switch (mode) {
            case 1:
                return "\nMENU WALLET\n" +
                    "1.Choose wallet\n" +
                    "2.Create wallet\n" +
                    "0.Exit\n" +
                    "Please select 0-2!";
            case 2:
                return "\nMENU WALLET\n" +
                        "3.Add user to wallet\n" +
                        "4.Remove user from wallet\n" +
                        "0.Exit\n" +
                        "Please select 0,3 or 4!";
            case 3:
                return show();
            default:
                return "";
        }
    }

    public String showInputsByChoice(WalletService walletService, int choice, String userEmail) {
        String walletId = "";
        switch (choice) {
            case 1: // choose wallet
                walletId = getWalletId();
                if (walletService.checkIfWalletExists(walletId)) {
                    return walletId;
                }
                else {
                    System.out.println("Wallet "+walletId+" does not exist!");
                    return "";
                }

            case 2:
                walletId = getWalletId();
                if (walletService.checkIfWalletExists(walletId)) {
                    System.out.println("Wallet "+walletId+" already exists!");
                    return "";
                }

                walletService.addWallet(walletId, userEmail);

                System.out.println(walletService.getUserWallets(userEmail));
                return walletId;

            case 3:
            case 4:
                String sharedUserEmail = getUserEmail();
                return sharedUserEmail;
            default:
                return "";
        }
    }

    private String getUserEmail() {
        return getDataFromUser("Provide user email:");
    }

    public String getWalletId() {
        return getDataFromUser("Provide wallet name:");
    }
}
