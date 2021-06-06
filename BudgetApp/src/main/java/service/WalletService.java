package service;

import menu.MenuModifyCyclicEntries;
import menu.MenuModifyEntries;
import model.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class WalletService {
    private WalletList wallets;
    private SerializerService serializerService;

    public WalletService() {
        this.serializerService = new SerializerService();
        this.wallets = new WalletList();
    }

    public void loadWalletsOnStart(String walletListPath) throws FileNotFoundException {
        wallets = (WalletList) serializerService.readObjectFromFile(walletListPath);
        if (wallets == null) {
            wallets = new WalletList();
        }
    }

    public void saveOnStop(String filePath) {
        serializerService.writeObjectToFile(filePath, wallets);
    }

    public WalletList getWallets() {
        return wallets;
    }
//    public Wallet getWallet(String walletName) {
//        return wallets.getWalletByName(walletName);

//    }

    public List<Wallet> getUserWallets(String userEmail) {
        return wallets.getUserWallets(userEmail);
    }

    public List<String> getUserWalletsNames(String userEmail) {
        return wallets.getUserWalletsNames( userEmail);
    }

    public boolean checkIfWalletExists(String walletName) {
        return wallets.checkIfWalletExists(walletName);
    }

    public void addEntry(String walletName, Entry entry) {
        wallets.addEntry(walletName, entry);
    }

    public void addCyclicPrototype(String walletName, CyclicEntryPrototype cyclicEntryPrototype) {
        wallets.addCyclicPrototype(walletName, cyclicEntryPrototype);
    }

    public void addWallet(String walletName, String ownerEmail) {
        wallets.addWallet(walletName, ownerEmail);
    }

    public void archiveWallet(String walletName) {
        wallets.archiveWallet(walletName);
    }

    private List<Wallet> getUnarchivedWallets() {
        return wallets.getUnarchivedWallets();
    }

    public void addCategory(String walletName, String category) {
        wallets.addCategory(walletName, category);
    }

    public Wallet getWalletByName(String walletName) {
        return wallets.getWalletByName(walletName);
    }

    public EntryList getEntryList(String walletName) {
        return wallets.getEntryList(walletName);
    }

    public CyclicPrototypeList getCyclicPrototypes(String walletName) {
        return wallets.getCyclicPrototypes(walletName);
    }

    public void renameWallet(String oldWalletName, String newWalletName) {
        wallets.rename(oldWalletName, newWalletName);
    }

    public boolean hasCategory(String walletName, String category) {
        return wallets.hasCategory(walletName,category);
    }

    public CategoryList getCategoryList(String walletName) {
        return wallets.getCategoryList(walletName);
    }

    public void removeEntry(String walletName, Scanner scanner) {
        Wallet wallet = wallets.getWalletByName(walletName);
        EntryList entryList = wallet.getEntryList();

        if (entryList.length() == 0) {
            System.out.println("No entries to remove!");
            return;
        }

        System.out.print(entryList.getOrderedEntriesString());
//        int entry_num = getIntFromUser(String.format("Select an entry from 1 to %d to be removed:\n", wallet.getEntryList().length()));
        System.out.printf("Select an entry from 1 to %d to be removed:\n", entryList.length());

        int entry_choice;
        try {
            entry_choice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return;
        }
        if (entry_choice >= 1 && entry_choice <= wallet.getEntryList().length()) {
            entryList.removeEntry(entry_choice - 1);
            System.out.println("Entry removed!");
        }
        else {
            System.out.println("Invalid entry number!");
        }
    }

    public void removeCyclicEntry(String walletName, Scanner scanner) {
        Wallet wallet = wallets.getWalletByName(walletName);
        CyclicPrototypeList cyclicPrototypes = wallet.getCyclicPrototypes();

        if (cyclicPrototypes.length() == 0) {
            System.out.println("No cyclic entries to remove!");
            return;
        }

        System.out.print(cyclicPrototypes.getOrderedEntriesString());
        System.out.printf("Select a cyclic  entry from 1 to %d to be removed:\n", cyclicPrototypes.length());

        int entry_choice;
        try {
            entry_choice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return;
        }
        if (entry_choice >= 1 && entry_choice <= cyclicPrototypes.length()) {
            cyclicPrototypes.removePrototype(entry_choice - 1);
            System.out.println("Cyclic entry removed!");
        }
        else {
            System.out.println("Invalid cyclic entry number!");
        }
    }

    public void modifyEntry(String walletName, Scanner scanner) {
        Wallet wallet = wallets.getWalletByName(walletName);
        EntryList entryList = wallet.getEntryList();

        System.out.print(entryList.getOrderedEntriesString());
        if (entryList.length() == 0) {
            System.out.println("No entries to modify!");
            return;
        }

        MenuModifyEntries menuModEntries = new MenuModifyEntries(scanner);
        System.out.printf("Select an entry from 1 to %d to be modified:\n", entryList.length());

        int entry_choice;
        try {
            entry_choice = scanner.nextInt();
            scanner.nextLine(); // consuming enter
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return;
        }

        int optionChoice;
        if (entry_choice >= 1 && entry_choice <= entryList.length()) {
            do {
                System.out.println("Chosen entry:");
                System.out.print(entryList.getEntry(entry_choice-1)+"\n");

                System.out.println(menuModEntries.show());
                optionChoice = menuModEntries.getChoiceFromUser();

                menuModEntries.executeActions(optionChoice, entryList.getEntry(entry_choice - 1));
            } while (optionChoice != 0);
        }
        else {
            System.out.println("Invalid entry number!");
        }

    }

    public void modifyCyclicEntry(String walletName, Scanner scanner) {
        Wallet wallet = wallets.getWalletByName(walletName);
        CyclicPrototypeList cyclicPrototypes = wallet.getCyclicPrototypes();

        System.out.print(cyclicPrototypes.getOrderedEntriesString());
        if (cyclicPrototypes.length() == 0) {
            System.out.println("No entries to modify!");
            return;
        }

        MenuModifyCyclicEntries menuModCyclicEntries = new MenuModifyCyclicEntries(scanner);
        System.out.printf("Select a cyclic entry from 1 to %d to be modified:\n", cyclicPrototypes.length());

        int entry_choice;
        try {
            entry_choice = scanner.nextInt();
            scanner.nextLine(); // consuming enter
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return;
        }

        int optionChoice;
        if (entry_choice >= 1 && entry_choice <= cyclicPrototypes.length()) {
            do {
                System.out.println("Chosen entry:");
                System.out.print(cyclicPrototypes.getCyclicEntry(entry_choice-1)+"\n");

                System.out.println(menuModCyclicEntries.show());
                optionChoice = menuModCyclicEntries.getChoiceFromUser();

                menuModCyclicEntries.executeActions(optionChoice, cyclicPrototypes.getPrototypes().get(entry_choice - 1));
            } while (optionChoice != 0);
        }
        else {
            System.out.println("Invalid entry number!");
        }
    }
}
