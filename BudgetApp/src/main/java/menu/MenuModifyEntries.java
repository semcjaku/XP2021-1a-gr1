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

    public Integer getAmountInputShow() { // TODO us getStringFromUser
        System.out.println("Provide new amount:");
        String line = scanner.nextLine();
        return Integer.parseInt(line);
    }

    public ArrayList<String> getCategoriesInputShow() {
        System.out.println("Provide categories separated by commas:");
        String line = scanner.nextLine();
        ArrayList<String> newCategories = new ArrayList<>(Arrays.asList(line.split(",")));
        newCategories.replaceAll(String::trim);
        return newCategories;
    }

    public String addCategoryInputShow() {
        System.out.println("Provide new category:");
        return scanner.nextLine();
    }

    public String removeCategoryInputShow() {
        System.out.println("Choose a category to be removed:");
        return scanner.nextLine();
    }

    public void executeActions(int choice, Entry entry) {
        switch(choice) {
            case 1:
                Integer newAmount = getAmountInputShow();
                entry.setAmount(newAmount);
                break;
            case 2:
                ArrayList<String> newCategories = getCategoriesInputShow();
                entry.setCategories(newCategories);
                break;
            case 3:
                String newCategory = addCategoryInputShow();
                entry.addCategory(newCategory);
                break;
            case 4:
                System.out.println(entry.getCategories());
                String cat = removeCategoryInputShow();
                entry.removeCategory(cat);
                break;
            default:
                break;
        }
    }
}
