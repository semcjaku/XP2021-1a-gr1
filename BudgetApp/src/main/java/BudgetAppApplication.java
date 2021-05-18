import java.util.Scanner;

public class BudgetAppApplication {

    public static void main(String[] args) throws Exception {
        System.out.println("Budget application has started!");

        EntryList entryList = new EntryList();
        CategoryList categoryList = new CategoryList();

        Scheduler scheduler = new Scheduler(entryList);
        scheduler.runScheduler();

        Menu menu = new Menu();
        MenuEntry menuEntry = new MenuEntry();
        MenuCategory menuCategory = new MenuCategory();
        int choice = 0;
        do {
            System.out.println(menu.show());
            Scanner keyboard = new Scanner(System.in);
            String line = keyboard.next();
            choice = menu.read(line);
            switch (choice) {
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
                    String category = menuCategory.showInputsByChoice(categoryChoice);
                    categoryList.addCategory(category);
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
        } while (choice != 0);

        scheduler.stopScheduler();
    }
}
