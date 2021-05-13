public class BudgetAppApplication {

    public static void main(String[] args) throws Exception {
        System.out.println("Budget application has started!");

        EntryList entryList = new EntryList();

        MenuEntry menuEntry = new MenuEntry();
        do {
            System.out.println(menuEntry.show());
//            String line = System.console().readLine();
            String line = "";
            int choice = menuEntry.read(line);
            //menuEntry.showInputsByChoice(choice);
            Entry entry = menuEntry.showInputsByChoice(System.in, choice);

            System.out.println(entry);

        } while(true);
    }
}
