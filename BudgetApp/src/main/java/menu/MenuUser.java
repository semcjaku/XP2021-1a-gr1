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
                "0.Exit\n" +
                "Please select 0-1!";
    }

    public String getEmailInputShow() {
        System.out.println("Provide email:");
        String line = scanner.nextLine();
        return line;
    }

    public String getPasswordInputShow() {
        System.out.println("Provide password:");
        String line = scanner.nextLine();
        return line;
    }

    public User showInputsByChoice(int choice) {
        if (choice == 1) {
            String email = getEmailInputShow();
            String password = getPasswordInputShow();

            return new User(email, password);
        }

        return null;
    }
}
