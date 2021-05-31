package menu;

import exception.InvalidInputException;
import model.Wallet;
import model.WalletList;

import java.util.Scanner;

public class MenuManageWallets extends AbstractMenu {
    private final Scanner scanner;
    private final WalletList walletList;

    public MenuManageWallets(WalletList walletList) {
        this(new Scanner(System.in), walletList);
    }

    public MenuManageWallets(Scanner scanner, WalletList walletList) {
        this.scanner = scanner;
        this.walletList = walletList;
    }

    @Override
    public int getMinInputNumber() {
        return 0;
    }

    @Override
    public int getMaxInputNumber() {
        return 3;
    }

    @Override
    public String show() {
        return "\nMENU MANAGE WALLETS\n" +
                "1. Add wallet\n" +
                "2. Archive wallet\n" +
                "3. Rename wallet\n" +
                "0. RETURN\n" +
                "Please select 0-3!";
    }

    public Wallet addWalletInputShow() {
        System.out.println("Provide name for new wallet:");
        String line = scanner.nextLine();
        return new Wallet(line);
    }

    public String pickNameInputShow() {
        System.out.println("Provide name:");
        return scanner.nextLine();
    }

    public void showInputsByChoice(int choice) throws InvalidInputException {
        Wallet wallet;

        switch (choice) {
            case 1:
                wallet = addWalletInputShow();
                walletList.addWallet(wallet);
                break;
            case 2:
                wallet = letUserChooseWallet();
                wallet.markAsArchived();
                break;
            case 3:
                wallet = letUserChooseWallet();
                String newName = pickNameInputShow();
                wallet.setName(newName);
                break;
        }
    }

    private Wallet letUserChooseWallet() throws InvalidInputException {
        MenuPickWallet menuPickWallet = new MenuPickWallet(walletList);
        System.out.println(menuPickWallet.show());
        String line = scanner.nextLine();
        int choice = menuPickWallet.read(line);
        return menuPickWallet.executeActions(choice);
    }
}
