package menu;

import exception.InvalidInputException;
import model.Entry;
import model.cyclic.CyclicEntryPrototype;

import java.util.Scanner;

public class MenuModifyCyclicEntries extends AbstractMenu {
    public MenuModifyCyclicEntries() {
        this.scanner = new Scanner(System.in);
    }

    public MenuModifyCyclicEntries(Scanner scanner) {
        this.scanner = scanner;
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
                "0.Return\n" +
                "Please select " + getMinInputNumber() + "-" + getMaxInputNumber() + "!";
    }

    public int getCyclicParameterInputShow() {
        System.out.println("Provide new cyclic parameter:");
        String line = scanner.nextLine();
        return Integer.parseInt(line);
    }

    private void modifyEntryInputShow(Entry entry) throws InvalidInputException {
        MenuModifyEntries menuModEntries = new MenuModifyEntries(scanner);

        System.out.println(menuModEntries.show());
        String line = scanner.nextLine();
        int entryChoice = menuModEntries.read(line);
        menuModEntries.executeActions(entryChoice, entry);
    }

    public void executeActions(int choice, CyclicEntryPrototype prototype) throws InvalidInputException {
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
