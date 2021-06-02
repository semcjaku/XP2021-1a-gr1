package menu;

import model.Entry;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuEntry extends AbstractMenu {
    public MenuEntry() {
        super(System.in);
    }

    public MenuEntry(InputStream inputStream) {
         super(inputStream);
    }

    public MenuEntry(Scanner scanner) {
        super(scanner);
    }

    @Override
    public int getMinInputNumber() {
        return 1;
    }

    @Override
    public int getMaxInputNumber() {
        return 2;
    }

    @Override
    public String show() {
        return "\nMENU ENTRY\n" +
                "1.Add Entry with amount\n" +
                "2.Add Entry with amount and category list";
    }

    public int getAmountInputShow() {
        return getIntFromUser("Provide amount:");
    }

    public List<String> getCategoryInputShow() {
        System.out.println("Provide category (delimeter ;):");
        String line = scanner.nextLine();
        return Arrays.asList(line.split(";"));
    }

    public Entry showInputsByChoice(int choice){
        int amount = getAmountInputShow();
        List<String> catList;
        Entry entry;
        switch(choice) {
            case 2:
                catList = getCategoryInputShow();
                entry = new Entry(amount, catList);
                break;
            case 1:
            default:
                entry = new Entry(amount);
                break;
        }
        return entry;
    }
}
