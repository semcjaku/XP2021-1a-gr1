package menu;

import model.WalletList;
import java.io.InputStream;
import java.util.Scanner;

public class MenuPickWallet extends AbstractMenu {
    private WalletList walletList;

    public MenuPickWallet(InputStream inputStream, WalletList walletList) {
        super(inputStream);
        this.walletList = walletList;
    }

    public MenuPickWallet(Scanner scanner, WalletList walletList) {
        super(scanner);
        this.walletList = walletList;
    }

    public MenuPickWallet(WalletList walletList) {
        super(System.in);
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
                walletList.getOrderedWalletsString();
    }

    public String executeActions(int choice) {
        switch (choice) {
            default:
                return walletList.getWallets().get(choice-1).getName();
        }
    }

}
