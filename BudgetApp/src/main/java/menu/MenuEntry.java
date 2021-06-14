package menu;

import model.Entry;
import service.WalletService;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuEntry extends AbstractMenu {

    private WalletService walletService;

//    public MenuEntry() {
//        super(System.in);
//    }

    public MenuEntry(WalletService walletService) {
//        super(System.in);
        super(walletService.getScanner());
        this.walletService = walletService;
    }


    public MenuEntry(InputStream inputStream) {
         super(inputStream);
    }

    public MenuEntry(Scanner scanner) {
        super(scanner);
    }


    @Override
    public int getMinInputNumber() {
        return 0;
    }

    @Override
    public int getMaxInputNumber() {
        return 2;
    }

    @Override
    public String show() {
        return "\nMENU ENTRY\n" +
                "1.Add Entry with amount\n" +
                "2.Add Entry with amount and category list\n" +
                "0.Exit";
    }

    public int getAmountFromUser() {
        return getIntFromUser("Provide amount:");
    }

    public List<String> getCategoriesFromUser() {
        String categories = getStringFromUser("Provide category (delimiter ;):");
        return Arrays.asList(categories.split(";"));
    }

    public void executeChoice(int choice) {
        switch(choice) {
            case 0:
                break;
            case 1:
                hndAddEntry();
                break;
            case 2:
                hndAddEntryWithCategory();
                break;
        }
    }

    // used with cyclic entry
    public Entry getNewEntry(int choice) {
        switch(choice) {
            case 0:
                break;
            case 1:
                return makeEntry(false);
            case 2:
                return makeEntry(true);
        }
        return null; //TODO nullobject
    }

    private void hndAddEntryWithCategory() {
        Entry entry = makeEntry(true);
        walletService.addEntry(walletService.getCurrentWalletName(), entry);
    }

    private void hndAddEntry() {
        Entry entry = makeEntry(false);
        walletService.addEntry(walletService.getCurrentWalletName(), entry);
    }

    private Entry makeEntry(boolean withCategories) {
        int amount = getAmountFromUser();
        String creator = walletService.getLoggedInUserName();
        if (withCategories) {
            List<String> catList = getCategoriesFromUser();
            return new Entry(creator, amount, catList);
        }
        else {
            return new Entry(creator, amount);
        }
    }
}
