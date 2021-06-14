package menu;

import model.Entry;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MenuModifyEntries extends AbstractMenu {
    public MenuModifyEntries() {
        super(System.in);
    }

    public MenuModifyEntries(InputStream inputStream) {
        super(inputStream);
    }

    public MenuModifyEntries(Scanner scanner) {
        super(scanner);
    }

    @Override
    public int getMinInputNumber() {
        return 0;
    }

    @Override
    public int getMaxInputNumber() {
        return 4;
    }

    @Override
    public String show() {
        return "\nChoose an action:\n" +
                "1.Change amount\n" +
                "2.Change categories\n" +
                "3.Add category\n" +
                "4.Remove category\n" +
                "0.Return";
    }

    public Integer getAmountFromUser() {
        return getIntFromUser("Provide new amount:");
    }

    public ArrayList<String> getCategoriesFromUser() {
        String line = getStringFromUser("Provide categories separated by commas:");
        ArrayList<String> newCategories = new ArrayList<>(Arrays.asList(line.split(",")));
        newCategories.replaceAll(String::trim);
        return newCategories;
    }

    public String getNewCategoryFromUser() {
        return getStringFromUser("Provide new category:");
    }

    public String getCategoryToBeRemovedFromUser() {
        return getStringFromUser("Choose a category to be removed:");
    }

    public void executeActions(int choice, Entry entry) {
        switch(choice) {
            case 1:
                Integer newAmount = getAmountFromUser();
                entry.setAmount(newAmount);
                break;
            case 2:
                ArrayList<String> newCategories = getCategoriesFromUser();
                entry.setCategories(newCategories);
                break;
            case 3:
                String newCategory = getNewCategoryFromUser();
                entry.addCategory(newCategory);
                break;
            case 4:
                System.out.println(entry.getCategories());
                String cat = getCategoryToBeRemovedFromUser();
                entry.removeCategory(cat);
                break;
            default:
                break;
        }
    }
}
