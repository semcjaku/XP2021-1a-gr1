package menu;

import model.WalletList;
import service.WalletService;

import java.io.InputStream;
import java.util.Scanner;

public class MenuPickWallet extends AbstractMenu {
//    private WalletList walletList;
    private WalletService walletService;

    public MenuPickWallet(WalletService walletService) {
        super(walletService.getScanner());
        this.walletService = walletService;
    }

//    public MenuPickWallet(Scanner scanner) {
//        super(scanner);
//    }

    public MenuPickWallet(InputStream inputStream) {
        super(inputStream);
//        this.walletList = walletList;
    }
//
//    public MenuPickWallet(Scanner scanner, WalletList walletList) {
//        super(scanner);
//        this.walletList = walletList;
//    }
//
//    public MenuPickWallet(WalletList walletList) {
//        super(System.in);
//        this.walletList = walletList;
//    }

    @Override
    public int getMinInputNumber() {
        return 1;
    }

    @Override
    public int getMaxInputNumber() {
        return walletService.getWallets().getLength();
    }

    @Override
    public String show() {
        return "\nPICK WALLET:\n" +
                walletService.getWallets().getOrderedWalletsString();
    }

    public void executeChoice(int choice) {
        switch (choice) {
            default:
//                return walletService.getWallets().getWallets().get(choice-1).getName();
                this.walletService.setCurrentWalletName(walletService.getWallets().getWallets().get(choice-1).getName());
        }
    }

}
