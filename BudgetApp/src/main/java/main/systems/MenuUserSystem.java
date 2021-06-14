package main.systems;

import menu.MenuUser;
import model.User;
import service.UserService;

public class MenuUserSystem {

    public static User run (UserService userService) {
        int choice;
        User loggedInUser = null;

        MenuUser menuUser = new MenuUser();

        do {
            System.out.println(menuUser.show());
            choice = menuUser.getChoiceFromUser();

            switch (choice) {
                case 1:
                    User inputUser = menuUser.showInputsByChoice(choice);
                    loggedInUser = userService.login(inputUser.getEmail(), inputUser.getPassword());
                    if (loggedInUser == null) {
                        System.out.println("Invalid user data! Try again.");
                    }
                    break;
                case 2:
                    User inputRegisterUser = menuUser.showInputsByChoice(choice);
                    loggedInUser = userService.register(inputRegisterUser.getEmail(), inputRegisterUser.getPassword());
                    if (loggedInUser == null) {
                        System.out.println("User with this email already exists! Try again.");
                    }
                    break;
                case 0:
                    break;
            }
        } while (choice != 0 && loggedInUser == null);
        return loggedInUser;
    }
}
