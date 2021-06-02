package menu;

import model.WalletList;
import service.WalletService;

import java.io.InputStream;
import java.util.Scanner;

public class MenuManageWallets extends AbstractMenu {

    private enum ManageMode {
        CREATE, ARCHIVE, RENAME
    }

    public MenuManageWallets() {
       super(System.in);
    }

    public MenuManageWallets(InputStream inputStream) {
        super(inputStream);
    }

    public MenuManageWallets(WalletList walletList) {
        this(new Scanner(System.in));
    }

    public MenuManageWallets(Scanner scanner) {
        super(scanner);
    }

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
        return "\nMENU MANAGE WALLETS\n" +
                "1. Add wallet\n" +
                "2. Archive wallet\n" +
                "3. Rename wallet\n" +
                "0. Exit";
    }

    public void executeActions(int choice, String ownerEmail, WalletService walletService) {
        switch (choice) {
            case 1:
                createWallet(ownerEmail, walletService);
                break;
            case 2:
                archiveWallet(ownerEmail, walletService);
                break;
            case 3:
                renameWallet(ownerEmail, walletService);
                break;
            case 4:
                showUserWallets(ownerEmail, walletService);
                break;
            case 0:
                break;
        }
    }

    public String showUserWallets(String ownerEmail, WalletService walletService) {
        return new StringBuilder("Available wallets for user ")
                .append(ownerEmail).append(": ")
                .append(walletService.getUserWalletsNames(ownerEmail)).toString();

    }

    private void renameWallet(String ownerEmail, WalletService walletService) {
        System.out.println(showUserWallets(ownerEmail,walletService));
        String oldWalletName = getWalletName(ManageMode.RENAME,true, ownerEmail, walletService);
        String newWalletName = getWalletName(ManageMode.CREATE,false, ownerEmail, walletService);

        if (walletService.checkIfWalletExists(newWalletName)) {
            System.out.println("Wallet with this name already exists!");
            return;
        }
        walletService.renameWallet(oldWalletName,newWalletName);
    }

    private void archiveWallet(String ownerEmail, WalletService walletService) {
        System.out.println(showUserWallets(ownerEmail,walletService));
        String walletName = getWalletName(ManageMode.ARCHIVE,true, ownerEmail, walletService);
        walletService.archiveWallet(walletName);
    }

    private String createWallet(String ownerEmail, WalletService walletService) {
        String walletName = getWalletName(ManageMode.CREATE,false, ownerEmail, walletService);
        walletService.addWallet(walletName, ownerEmail);
        return walletName;
    }

    private String getWalletName(ManageMode manageMode, boolean canWalletExist, String ownerEmail, WalletService walletService) {
        String walletName = "";
        do {
            switch (manageMode) {
                case CREATE:
                    walletName = getNewWalletNameFromUser();
                    break;
                case ARCHIVE:
                    walletName = getWalletNameToArchiveFromUser();
                    break;
                case RENAME:
                    walletName = getOldWalletNameFromUser();
                    break;
            }

            boolean walletExists = walletService.checkIfWalletExists(walletName);

            if (walletExists && !canWalletExist) {
                System.out.println("Wallet with name " + walletName + " already exists!");
                walletName = "";
            } else if (!walletExists && canWalletExist) {
                System.out.println("Wallet with name " + walletName + " does not exist!");
                walletName = "";
            }

        } while (walletName.equals(""));

        return walletName;
    }

    private String getNewWalletNameFromUser() {
        return getStringFromUser("Provide new name for wallet:");
    }

    private String getOldWalletNameFromUser() {
        return getStringFromUser("Provide wallet old name:");
    }

    private String getWalletNameToArchiveFromUser() {
        return getStringFromUser("Provide name of wallet to be archived:");
    }

    private String getWalletNameToUpdateFromUser() {
        return getStringFromUser("Provide name of wallet to be renamed:");
    }

}
