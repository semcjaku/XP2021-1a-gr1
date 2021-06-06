package menu;

import service.WalletService;

import java.io.InputStream;

public class MenuCategory extends AbstractMenu {
    private WalletService walletService;

    public MenuCategory(InputStream inputStream) {
        super(inputStream);
    }

    public MenuCategory(WalletService walletService) {
        super(walletService.getScanner());
        this.walletService = walletService;
    }
//    public MenuCategory() {
//        super(System.in);
//    }

    @Override
    public int getMinInputNumber() {
        return 1;
    }

    @Override
    public int getMaxInputNumber() {
        return 1;
    }

    @Override
    public String show() {
        return "\nMENU CATEGORY\n" +
                "1.Add Category\n" +
                "0.Exit";
    }

    public void executeChoice(int choice) {
        switch (choice) {
            case 0:
                break;
            case 1:
                hndAddCategory();
                break;
        }
    }

    private void hndAddCategory() {
        String newCategory;
        do {
            newCategory = getStringFromUser("Provide new category name: ");
            if (walletService.hasCategory(walletService.getCurrentWalletName(), newCategory)) {
               System.out.println("Wallet "+walletService.getCurrentWalletName()+" already contains category: "+newCategory);
               newCategory = "";
            }
        } while (newCategory == "");
        walletService.addCategory(walletService.getCurrentWalletName(),newCategory);
    }
}
