package menu;

import model.Wallet;
import model.WalletList;

public class MenuPickWallet extends AbstractMenu {
    private final WalletList walletList;

    public MenuPickWallet(WalletList walletList) {
        this.walletList = walletList;
    }

    @Override
    public int getMinInputNumber() {
        return 1;
    }

    @Override
    public int getMaxInputNumber() {
        return walletList.getLength();
    }

    @Override
    public String show() {
        return "\nPICK WALLET:\n" +
                walletList.getOrderedWalletsString() +
                "\nPlease select 1-" + walletList.getLength() + "!";
    }

    public Wallet executeActions(int choice) {
        return walletList.getWallets().get(choice-1);
    }
}
