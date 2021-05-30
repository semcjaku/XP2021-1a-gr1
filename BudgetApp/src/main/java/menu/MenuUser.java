package menu;

import model.User;

import java.io.InputStream;
import java.util.Scanner;

public class MenuUser extends AbstractMenu {
    public MenuUser() {
        this.scanner = new Scanner(System.in);
    }

    public MenuUser(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public int getMinInputNumber() {
        return 0;
    }

    @Override
    public int getMaxInputNumber() {
        return 1;
    }

    @Override
    public String show() {
        return "\nMENU USER\n" +
                "1.Login\n" +
                "2.Register\n" +
                "0.Exit\n" +
                "Please select 0-1!";
    }

    public String getEmailInputShow() {
        String line = "";
        do {
            System.out.println("Provide email:");
            line = scanner.nextLine();
            line = line.replaceAll("\\s+","");
        } while (line.equals(""));
        return line;
    }

    public String getPasswordInputShow() {
        String line = "";
        do {
            System.out.println("Provide password:");
            line = scanner.nextLine();
            line = line.replaceAll("\\s+", "");
        } while (line.equals(""));
        return line;
    }

    public User showInputsByChoice(int choice) {

        switch (choice) {
            case 1:
            case 2:
                String email = getEmailInputShow();
                String password = getPasswordInputShow();

                return new User(email, password);
        }

        return null;
    }
}
