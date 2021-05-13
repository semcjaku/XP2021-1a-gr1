import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BudgetAppApplication {

    public static void main(String[] args) throws Exception {
        System.out.println("Budget application has started!");

        EntryList entryList = new EntryList();
        List<Category> categoryList = new LinkedList<>();

        Menu menu = new Menu();
        MenuEntry menuEntry = new MenuEntry();
        MenuCategory menuCategory = new MenuCategory();
        int choice = 0;
        do {
            System.out.println(menu.show());
            Scanner keyboard = new Scanner(System.in);
            String line = keyboard.next();
            choice = menu.read(line);
            switch(choice){
                case 1:
                    System.out.println(menuEntry.show());
                    line = keyboard.next();
                    int entryChoice = menuEntry.read(line);
                    Entry entry = menuEntry.showInputsByChoice(entryChoice);
                    entryList.addEntry(entry);
                    break;
                case 2:
                    System.out.println(menuCategory.show());
                    line = keyboard.next();
                    int categoryChoice = menuCategory.read(line);
                    Category category = menuCategory.showInputsByChoice(categoryChoice);
                    categoryList.add(category);
                    break;
                case 3:
                    System.out.println(entryList);
                    break;
                case 4:
                    System.out.println(categoryList);
                    break;
                default:
                    break;
            }
        } while(choice != 0);
    }
}
