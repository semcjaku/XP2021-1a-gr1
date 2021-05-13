import java.io.InputStream;
import java.util.Scanner;

public class MenuCategory extends AbstractMenu {
    public MenuCategory() {
        this.in = System.in;
    }

    public MenuCategory(InputStream inputStream) {
        this.in = inputStream;
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
        return "MENU CATEGORY\n" +
                "1.Add Category\n" +
                "Please select 1!";
    }

    public String getNameInputShow() {
        System.out.println("Provide name:");
        Scanner keyboard = new Scanner(in);
        String line = keyboard.next();
        return line;
    }

    public Category showInputsByChoice(int choice) {
        String name;
        Category category = null;

        if (choice == 1) {
            name = getNameInputShow();
            category = new Category(name);
        }
        return category;
    }
}
