package menu;

import service.WalletService;

import java.io.InputStream;

public class MenuCategory extends AbstractMenu {
    public MenuCategory(InputStream inputStream) {
        super(inputStream);
    }

    public MenuCategory() {
        super(System.in);
    }

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
                "1.Add Category";
    }

    public void executeActions(int choice, String walletName, WalletService walletService) {
        switch (choice) {
            case 1:
                addCategory(walletName, walletService);
                break;
        }
    }

    private void addCategory(String walletName, WalletService walletService) {
        String newCategory;
        do {
            newCategory = getStringFromUser("Provide new category name: ");
            if (walletService.hasCategory(walletName, newCategory)) {
               System.out.println("Wallet "+walletName+" already contains category: "+newCategory);
               newCategory = "";
            }
        } while (newCategory == "");
        walletService.addCategory(walletName,newCategory);
    }
}
