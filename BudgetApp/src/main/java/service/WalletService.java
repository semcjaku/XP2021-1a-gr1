package service;

import menu.*;
import model.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class WalletService {
    private WalletList wallets;
    private SerializerService serializerService;
    private Scanner scanner;

    private String currentWalletName;
    private String loggedInUserName;

    public WalletService(Scanner scanner) {
        this.serializerService = new SerializerService();
        this.wallets = new WalletList();
        this.scanner = scanner;
        this.currentWalletName = "";
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

    public void setLoggedInUser(String loggedInUserName) {
        this.loggedInUserName = loggedInUserName;
    }

    public void setCurrentWalletName(String currentWalletName) {
        this.currentWalletName = currentWalletName;
    }

    public String getCurrentWalletName() {
        return currentWalletName;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public WalletList getWallets() {
        return wallets;
    }
//    public Wallet getWallet(String walletName) {
//        return wallets.getWalletByName(walletName);

//    }


    public String getLoggedInUserName() {
        return loggedInUserName;
    }

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

    public void hndRemoveEntry() {
        Wallet wallet = wallets.getWalletByName(getCurrentWalletName());
        EntryList entryList = wallet.getEntryList();

        try {
            stopIfListIsEmpty(entryList);
        }
        catch (OperationOnEmptyListException ignore) {
            return;
        }

        printOptions(entryList, "removed");

        int entry_choice;
        try {
            entry_choice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return;
        }
        if (validateChoiceFromList(entryList, entry_choice)) {
            removeAtUserProvidedIndex(entryList, entry_choice);
            System.out.println("Entry removed!");
        }
        else {
            System.out.println("Invalid entry number!");
        }
    }

    public void hndRemoveCyclicEntry() {
        Wallet wallet = wallets.getWalletByName(getCurrentWalletName());
        CyclicPrototypeList cyclicPrototypes = wallet.getCyclicPrototypes();

        try {
            stopIfListIsEmpty(cyclicPrototypes);
        }
        catch (OperationOnEmptyListException ignore) {
            return;
        }

        printOptions(cyclicPrototypes, "removed");

        int entry_choice;
        try {
            entry_choice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return;
        }
        if (validateChoiceFromList(cyclicPrototypes, entry_choice)) {
            removeAtUserProvidedIndex(cyclicPrototypes, entry_choice);
            System.out.println("Cyclic entry removed!");
        }
        else {
            System.out.println("Invalid cyclic entry number!");
        }
    }

    public void hndModifyEntry() {
        Wallet wallet = wallets.getWalletByName(getCurrentWalletName());
        EntryList entryList = wallet.getEntryList();

        try {
            stopIfListIsEmpty(entryList);
        }
        catch (OperationOnEmptyListException ignore) {
            return;
        }

        MenuModifyEntries menuModEntries = new MenuModifyEntries(scanner);
        printOptions(entryList, "modified");

        int entry_choice;
        try {
            entry_choice = scanner.nextInt();
            scanner.nextLine(); // consuming enter
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return;
        }

        int optionChoice;
        if (entry_choice >= 1 && entry_choice <= entryList.getLength()) {
            do {
                System.out.println("Chosen entry:");
                System.out.print(entryList.getAt(entry_choice-1)+"\n");

                System.out.println(menuModEntries.show());
                optionChoice = menuModEntries.getChoiceFromUser();

                menuModEntries.executeActions(optionChoice, entryList.getAt(entry_choice - 1));
            } while (optionChoice != 0);
        }
        else {
            System.out.println("Invalid entry number!");
        }

    }

    public void hndModifyCyclicEntry() {
        Wallet wallet = wallets.getWalletByName(getCurrentWalletName());
        CyclicPrototypeList cyclicPrototypes = wallet.getCyclicPrototypes();

        try {
            stopIfListIsEmpty(cyclicPrototypes);
        }
        catch (OperationOnEmptyListException ignore) {
            return;
        }

        MenuModifyCyclicEntries menuModCyclicEntries = new MenuModifyCyclicEntries(scanner);
        printOptions(cyclicPrototypes, "modified");

        int entry_choice;
        try {
            entry_choice = scanner.nextInt();
            scanner.nextLine(); // consuming enter
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return;
        }

        int optionChoice;
        if (validateChoiceFromList(cyclicPrototypes, entry_choice)) {
            do {
                System.out.println("Chosen entry:");
                System.out.print(cyclicPrototypes.getAt(entry_choice-1)+"\n");

                System.out.println(menuModCyclicEntries.show());
                optionChoice = menuModCyclicEntries.getChoiceFromUser();

                menuModCyclicEntries.executeActions(optionChoice, cyclicPrototypes.asList().get(entry_choice - 1));
            } while (optionChoice != 0);
        }
        else {
            System.out.println("Invalid entry number!");
        }
    }

    public void hndAddEntry() {
        MenuEntry menuEntry = new MenuEntry(this);
        System.out.println(menuEntry.show());
        int entryChoice = menuEntry.getChoiceFromUser();
        menuEntry.executeChoice(entryChoice);
    }

    public void hndAddCyclicEntry() {
        MenuCyclicEntry menuCyclicEntry = new MenuCyclicEntry(this);
        System.out.println(menuCyclicEntry.show());
        int choice = menuCyclicEntry.getChoiceFromUser();
        menuCyclicEntry.executeChoice(choice);
    }

    public void hndAddCategory() {
        MenuCategory menuCategory = new MenuCategory(this);
        System.out.println(menuCategory.show());
        int choice = menuCategory.getChoiceFromUser();
        menuCategory.executeChoice(choice);
    }

    public void hndShowEntryList() {
        System.out.println(getEntryList(getCurrentWalletName()));
    }

    public void hndShowCyclicEntryList() {
        System.out.println(getCyclicPrototypes(getCurrentWalletName()));
    }

    public void hndShowCategoryList() {
        CategoryList categoryList = getCategoryList(getCurrentWalletName());
        if (categoryList == null) {
            System.out.println("No categories to show!");
        }
        System.out.println(getCategoryList(getCurrentWalletName()));
    }

    public void hndSwitchWallet() {
        MenuPickWallet menuPickWallet = new MenuPickWallet(this);
        System.out.println(menuPickWallet.show());
        int choice = menuPickWallet.getChoiceFromUser();
        menuPickWallet.executeChoice(choice);
    }

    public void hndManageWallets() {
        MenuManageWallets menuManageWallets = new MenuManageWallets(this);
        System.out.println(menuManageWallets.show());
        int choice = menuManageWallets.getChoiceFromUser();
        menuManageWallets.executeChoice(choice);
    }

    public boolean hasUserAccess(String userEmailToCheck) {
        if (userEmailToCheck.equals(getLoggedInUserName())) {
            return true;
        }
        if (userEmailToCheck.equals(getWalletByName(getCurrentWalletName()).getOwnerEmail())) {
            return true;
        }
        if (getWalletByName(getCurrentWalletName()).getSharedUsersEmails().contains(userEmailToCheck)) {
            return true;
        }
        return false;
    }

    private boolean validateChoiceFromList(PrintableList list, int choice) {
        return choice >= 1 && choice <= list.getLength();
    }

    private void stopIfListIsEmpty(PrintableList list) throws OperationOnEmptyListException {
        if (list.getLength() == 0) {
            System.out.println("Nothing to remove (list empty)!");
            throw new OperationOnEmptyListException();
        }
    }

    private void printOptions(PrintableList list, String action) {
        System.out.print(list.getOrderedEntriesString());
        System.out.printf("Select an entry from 1 to %d to be %s:\n", list.getLength(), action);
    }

    private <T> void removeAtUserProvidedIndex(ListManager<T> list, int index) {
        list.removeAt(index - 1);
    }
}
