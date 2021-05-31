package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class WalletList {
    private final List<Wallet> wallets;

    private final String defaultWalletName = "Default wallet";

    public WalletList() {
        wallets = new ArrayList<>(1);
        wallets.add(new Wallet(defaultWalletName));
    }

    public List<Wallet> getWallets() {
        return getUnarchivedWallets();
    }

    public void addWallet(Wallet wallet) {
        wallets.add(wallet);
    }

    public int getLength() {
        return getUnarchivedWallets().size();
    }

    public String getOrderedWalletsString() {
        List<String> lines = new LinkedList<>();
        List<Wallet> unarchivedWallets = getUnarchivedWallets();

        for (int i = 0; i < unarchivedWallets.size(); i++) {
            lines.add(i+1 + ". " + unarchivedWallets.get(i).getName());
        }

        return String.join("\n", lines);
    }

    private List<Wallet> getUnarchivedWallets() {
        return wallets
                .stream()
                .filter(wallet -> !wallet.isArchived())
                .collect(Collectors.toList());
    }
}
