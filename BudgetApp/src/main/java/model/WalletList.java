package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class WalletList implements Serializable {
    private final List<Wallet> wallets;

    public WalletList() {
        wallets = new LinkedList<>();
    }

    public List<Wallet> getWallets() {
        return getUnarchivedWallets();
    }

//    public void addWallet(Wallet wallet) {
//        wallets.add(wallet);
//    }

    public void addWallet(String walletName, String ownerEmail) {
        Wallet wallet = new Wallet(walletName, ownerEmail);
        wallets.add(wallet);
    }

    public int getLength() {
        return getUnarchivedWallets().size();
    }

//    public List<Wallet> getOrderedWallets() {
//        return  getUnarchivedWallets();

//    }

    public String getOrderedWalletsString() {
        List<String> lines = new LinkedList<>();
        List<Wallet> unarchivedWallets = getUnarchivedWallets();

        for (int i = 0; i < unarchivedWallets.size(); i++) {
            lines.add(i+1 + ". " + unarchivedWallets.get(i).getName());
        }

        return String.join("\n", lines);
    }

    public List<Wallet> getUnarchivedWallets() {
        return wallets
                .stream()
                .filter(wallet -> !wallet.isArchived())
                .collect(Collectors.toList());
    }

    public List<Wallet> getUserWallets(String userEmail) {
        return getUnarchivedWallets()
                .stream()
                .filter(w -> w.getOwnerEmail().equals(userEmail) ||
                        w.getSharedUsersEmails().contains(userEmail))
                .collect(Collectors.toList()); // ALWAYS returns list, if nothing is found than empty.

    }

    public boolean checkIfWalletExists(String walletName) {
        return wallets.stream()
                .anyMatch(w -> w.getName().equals(walletName));
    }

    public void addEntry(String walletName, Entry entry) {
        wallets.stream()
                .filter(w -> w.getName().equals(walletName))
                .findFirst()
                .orElse(null) // optional? :(
                .addEntry(entry);
    }

//    public Entry getLastAddedEntry(String walletName) {
//        wallets.stream()
//                .filter(w -> w.getName().equals(walletName))
//                .findFirst()
//                .orElse(null) // optional? :(
//                .getLastAddedEntry();
//    }

    public void addCyclicPrototype(String walletName,  CyclicEntryPrototype cyclicEntryPrototype) {
        wallets.stream()
                .filter(w -> w.getName().equals(walletName))
                .findFirst()
                .orElse(null) // optional? :(
                .addCyclicPrototype(cyclicEntryPrototype);
    }

    public void addCategory(String walletName, String category) {
        wallets.stream()
                .filter(w -> w.getName().equals(walletName))
                .findFirst()
                .orElse(null)
                .addCategory(category);
    }

    public List<String> getUserWalletsNames(String userEmail) {
        return getUserWallets(userEmail)
                .stream()
                .map(Wallet::getName)
                .collect(Collectors.toList());
    }

    public Wallet getWalletByName(String walletName) {
        return wallets.stream()
                .filter(w -> w.getName().equals(walletName))
                .findFirst()
                .orElse(null);
    }

    public EntryList getEntryList(String walletName) {
        return wallets.stream()
                .filter(w -> w.getName().equals(walletName))
                .findFirst()
                .orElse(null)
                .getEntryList();
    }

    public CyclicPrototypeList getCyclicPrototypes(String walletName) {
        return wallets.stream()
                .filter(w -> w.getName().equals(walletName))
                .findFirst()
                .orElse(null)
                .getCyclicPrototypes();
    }

    public void archiveWallet(String walletName) {
        wallets.stream()
                .filter(w -> w.getName().equals(walletName))
                .findFirst()
                .orElse(null)
                .markAsArchived();
    }

    public void rename(String oldWalletName, String newWalletName) {
        wallets.stream()
                .filter(w -> w.getName().equals(oldWalletName))
                .findFirst()
                .orElse(null)
                .setName(newWalletName);
    }

    public boolean hasCategory(String walletName, String category) {
        return wallets.stream()
                .filter(w -> w.getName().equals(walletName))
                .findFirst()
                .orElse(null)
                .hasCategory(category);
    }

    public CategoryList getCategoryList(String walletName) {
        return wallets.stream()
                .filter(w -> w.getName().equals(walletName))
                .findFirst()
                .orElse(null)
                .getCategoryList();
    }
}
