package menu;

import model.User;
import java.io.InputStream;


public class MenuUser extends AbstractMenu {
    public MenuUser() {
        super(System.in);
    }

    public MenuUser(InputStream inputStream) {
        super(inputStream);
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
        return "\nMENU USER\n" +
                "1.Login\n" +
                "2.Register";
    }

    public String getEmailInputShow() {
        return getStringFromUser("Provide email:");
    }

    public String getPasswordInputShow() {
        return getStringFromUser("Provide password:");
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
