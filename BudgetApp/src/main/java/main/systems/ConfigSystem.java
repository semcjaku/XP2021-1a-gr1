package main.systems;

import menu.MenuConfig;
import model.Config;
import service.ConfigService;

import java.io.IOException;

public class ConfigSystem {


    public static Config run(Config currentConfig) throws IOException {
        int choice = 0;

        Config newConfig = currentConfig;

        MenuConfig menuConfig = new MenuConfig();

        do {
            System.out.println(menuConfig.show());
            choice = menuConfig.getChoiceFromUser();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    System.out.println(currentConfig);
                    break;
                case 3:
                    Config configFromUser = menuConfig.getNewConfigFromUser();
                    newConfig = ConfigService.saveNewConfig(configFromUser);
                    System.out.println("Successfully saved new config");
                    break;
                case 0:
                    break;
            }
        } while (choice != 0 && choice != 1);

        return newConfig;
    }
}
