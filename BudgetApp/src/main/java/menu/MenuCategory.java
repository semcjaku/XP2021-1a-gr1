package menu;

import java.io.InputStream;
import java.util.Scanner;

public class MenuCategory extends AbstractMenu {
    public MenuCategory() {
        this.scanner = new Scanner(System.in);
    }

    public MenuCategory(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
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
                "1.Add Category\n" +
                "Please select 1!";
    }

    public String getNameInputShow() {
        System.out.println("Provide name:");
        String line = scanner.nextLine();
        return line;
    }

    public String showInputsByChoice(int choice) {
        String category = null;

        if (choice == 1) {
            category = getNameInputShow();
        }
        return category;
    }
}
