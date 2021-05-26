package menu;

import model.Entry;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MenuModifyEntries extends AbstractMenu {
    public MenuModifyEntries() {
        this.scanner = new Scanner(System.in);
    }

    public MenuModifyEntries(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public int getMinInputNumber() {
        return 0;
    }

    @Override
    public int getMaxInputNumber() {
        return 6;
    }

    @Override
    public String show() {
        return "\nChoose an action:\n" +
                "1.Change amount\n" +
                "2.Change categories\n" +
                "3.Add category\n" +
                "4.Remove category\n" +
                "5.Set cyclic interval\n" +
                "6.Set cyclic day of month\n" +
                "0.Return\n" +
                "Please select 0-6!";
    }

    public Integer getAmountInputShow() {
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

    public Integer getIntervalInputShow() {
        System.out.println("Provide new interval:");
        return Integer.parseInt(scanner.nextLine());
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
                System.out.print(entry.getCategories());
                String cat = removeCategoryInputShow();
                entry.removeCategory(cat);
                break;
            case 5:
                Integer newInterval = getIntervalInputShow();
                entry.setCyclicIntervalInDays(newInterval);
                break;
            case 6:
                Integer newDay = getIntervalInputShow();
                entry.setCyclicDayOfMonth(newDay);
                break;
            default:
                break;
        }
    }
}
