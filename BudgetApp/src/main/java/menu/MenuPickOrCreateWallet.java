package menu;


import service.WalletService;
import java.io.InputStream;

public class MenuPickOrCreateWallet extends AbstractMenu {

    public MenuPickOrCreateWallet(InputStream inputStream) {
        super(inputStream);
    }

    public MenuPickOrCreateWallet() {
        super(System.in);
    }

    @Override
    public int getMinInputNumber() {
        return 1;
    }

    @Override
    public int getMaxInputNumber() {
        return 2;
    }

    @Override
    public String show() {
        return "\nMENU PICK OR CREATE WALLET\n" +
                "1.Create wallet\n" +
                "2.Pick Wallet";
    }

    public String executeActions(Integer choice, String ownerEmail, WalletService walletService ) {
        switch (choice) {
            case 1:
                return createWallet(ownerEmail, walletService);
            case 2:
                return pickWallet(ownerEmail, walletService);
        }
        return "";
    }

    private String pickWallet(String ownerEmail, WalletService walletService) {
        if (walletService.getUserWallets(ownerEmail).isEmpty()) {
            System.out.println("User " + ownerEmail + " doesn't have any wallets!");
            return "";
        }

        MenuPickWallet menuPickWallet = new MenuPickWallet(this.scanner,walletService.getWallets());
        System.out.println(menuPickWallet.show());
        int choice = menuPickWallet.getChoiceFromUser();
        String walletName = menuPickWallet.executeActions(choice);

        return walletName;

    }

    private String createWallet(String ownerEmail, WalletService walletService) {
        String walletName = "";
        do {
            walletName = getWalletNameFromUser();
            if (walletService.checkIfWalletExists(walletName)) {
                System.out.println("Wallet with name " + walletName + " already exists!");
                walletName = "";
            }
        } while (walletName.equals(""));

        walletService.addWallet(walletName, ownerEmail);
        return walletName;
    }

    public String getWalletNameFromUser() {
        return getStringFromUser("Provide name for new wallet:");
    }
}
