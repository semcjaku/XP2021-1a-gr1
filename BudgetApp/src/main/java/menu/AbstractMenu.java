package menu;

import java.io.InputStream;
import java.util.Scanner;

public abstract class AbstractMenu {

    protected Scanner scanner;


    protected AbstractMenu(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    protected AbstractMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public abstract int getMinInputNumber();

    public abstract int getMaxInputNumber();

    public abstract String show();

    public void validateChoice(String line) throws InvalidInputException { // return bool?
        if (line == null || line.isEmpty()) {
            throw new InvalidInputException("Empty input!");
        }
        try {
            int choice = Integer.parseInt(line.trim());

            if (choice < getMinInputNumber() || choice > getMaxInputNumber()) {
                throw new InvalidInputException("Input number out of range!");
            }

        } catch (NumberFormatException ex) {
            throw new InvalidInputException("Input not a number!");
        }
    }

    public void validateDecimal(String line) throws InvalidInputException {
//        line = line.replaceAll("\\s+", "");
        line = line.trim();
        if (line.isEmpty()) {
            throw new InvalidInputException("Empty input!");
        }
        line = line.replace(",",".");
        try {
            Double.parseDouble(line); // throws mismach
        } catch (Exception ex) {
            throw new InvalidInputException("Invalid input!");
        }
    }

    public void validateInteger(String line) throws InvalidInputException {
//        line = line.replaceAll("\\s+", "");
        line = line.trim();
        if (line.isEmpty()) {
            throw new InvalidInputException("Empty input!");
        }
        try {
            Integer.parseInt(line); // throws mismach
        } catch (Exception ex) {
            throw new InvalidInputException("Invalid input!");
        }
    }

    public void validatePositiveInteger(String line) throws InvalidInputException {
//        line = line.replaceAll("\\s+", "");
        line = line.trim();
        if (line.isEmpty()) {
            throw new InvalidInputException("Empty input!");
        }
        int i;
        try {
            i = Integer.parseInt(line);
        } catch (Exception ex) {
            throw new InvalidInputException("Invalid input!");
        }
        if (i <= 0) {
            throw new InvalidInputException("Input has to be positive!");
        }
    }

    public Integer getChoiceFromUser() {
        String choice;
        do {
            System.out.println("Please select "+getMinInputNumber()+"-"+getMaxInputNumber()+"!");
            try {
                choice = scanner.nextLine();
//                choice = choice.replaceAll("\\s+","");
                choice = choice.trim();
                validateChoice(choice);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                choice = "";
            }
        } while (choice.equals(""));

        return Integer.parseInt(choice);
    }

    public String getStringFromUser(String prompt) {
        String line = "";
        do {
            System.out.println(prompt);
            line = scanner.nextLine();
            line = line.trim();//.replaceAll("\\s+","");
        } while (line.equals(""));
        return line;
    }

    public double getDecimalFromUser(String prompt) {
        String line;
        double number = 0.0;
        do {
            System.out.println(prompt);
            try {
//                number = scanner.nextDouble();
                line = scanner.nextLine();
                validateDecimal(line);
                number = Double.parseDouble(line);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                line = "";
            }
        } while (line.equals(""));
        return number;
    }

    public int getIntFromUser(String prompt) {
        String line;
        int number = 0;
        do {
            System.out.println(prompt);
            try {
//                number = scanner.nextInt();
                line = scanner.nextLine();
                validateInteger(line);
                number = Integer.parseInt(line);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                line = "";
            }
        } while (line == "");
        return number;
    }

    public int getPositiveIntFromUser(String prompt) {
        String line;
        int number = 0;
        do {
            System.out.println(prompt);
            try {
//                number = scanner.nextInt();
                line = scanner.nextLine();
                validatePositiveInteger(line);
                number = Integer.parseInt(line);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                line = "";
            }
        } while (line == "");
        return number;
    }
}

