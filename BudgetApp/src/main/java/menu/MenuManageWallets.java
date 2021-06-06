package menu;

import service.WalletService;

import java.io.InputStream;

public class MenuManageWallets extends AbstractMenu {

    private WalletService walletService;

    private enum ManageMode {
        CREATE, ARCHIVE, RENAME
    }

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
//
//    public MenuManageWallets(WalletList walletList) {
//        this(new Scanner(System.in));
//    }

//    public MenuManageWallets(Scanner scanner) {
//        super(scanner);
//    }

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
                "4. Show logged in user wallets\n" +
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
            case 0:
                break;
        }
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
        System.out.println(showUserWallets());
        String oldWalletName = getWalletName(ManageMode.RENAME,true);
        String newWalletName = getWalletName(ManageMode.CREATE,false);

        if (walletService.checkIfWalletExists(newWalletName)) {
            System.out.println("Wallet with this name already exists!");
            return;
        }
        walletService.renameWallet(oldWalletName,newWalletName);
    }

    private void hndArchiveWallet() {
        System.out.println(showUserWallets());
        String walletName = getWalletName(ManageMode.ARCHIVE,true);
        walletService.archiveWallet(walletName);
    }

    private String hndCreateWallet() {
        String walletName = getWalletName(ManageMode.CREATE,false);
        walletService.addWallet(walletName, walletService.getLoggedInUserName());
        walletService.setCurrentWalletName(walletName);
        return walletName;
    }

    private String getWalletName(ManageMode manageMode, boolean canWalletExist) {
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
