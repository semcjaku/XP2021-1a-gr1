package menu;

import model.Entry;
import model.CyclicEntryPrototype;

import java.io.InputStream;
import java.util.Scanner;

public class MenuModifyCyclicEntries extends AbstractMenu {
    public MenuModifyCyclicEntries() {
        super(System.in);
    }

    public MenuModifyCyclicEntries(InputStream inputStream) {
        super(inputStream);
    }

    public MenuModifyCyclicEntries(Scanner scanner) {
        super(scanner);
    }

    @Override
    public int getMinInputNumber() {
        return 0;
    }

    @Override
    public int getMaxInputNumber() {
        return 2;
    }

    @Override
    public String show() {
        return "\nChoose an action:\n" +
                "1.Modify entry prototype (amount, categories)\n" +
                "2.Change cyclic parameter\n" +
                "0.Return";
    }

    public int getCyclicParameterInputShow() {
        System.out.println("Provide new cyclic parameter:");
        String line = scanner.nextLine();
        return Integer.parseInt(line);
    }

    private void modifyEntryInputShow(Entry entry)  {
        MenuModifyEntries menuModEntries = new MenuModifyEntries(scanner);

        System.out.println(menuModEntries.show());
        int entryChoice = menuModEntries.getChoiceFromUser();
        menuModEntries.executeActions(entryChoice, entry);
    }

    public void executeActions(int choice, CyclicEntryPrototype prototype)  {
        switch(choice) {
            case 1:
                modifyEntryInputShow(prototype.getPrototypeEntry());
                break;
            case 2:
                int newCyclicParameterValue = getCyclicParameterInputShow();
                prototype.setCyclicParameter(newCyclicParameterValue);
                break;
            default:
                break;
        }
    }
}
