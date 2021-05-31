package menu;

import java.util.Scanner;

public abstract class AbstractMenu {
    protected Scanner scanner = new Scanner(System.in);

    public abstract int getMinInputNumber();

    public abstract int getMaxInputNumber();

    public abstract String show();

    public int read(String line) throws InvalidInputException {
        if (line == null || line.isEmpty()) {
            throw new InvalidInputException("Empty input");
        }

        try {
            int choice = Integer.parseInt(line.trim());

            if (choice < getMinInputNumber() || choice > getMaxInputNumber()) {
                throw new InvalidInputException("Input number out of range");
            }
            return choice;

        } catch (NumberFormatException ex) {
            throw new InvalidInputException("Input not a number");
        }
    }
}
