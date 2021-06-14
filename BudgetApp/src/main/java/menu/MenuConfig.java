package menu;

import model.Config;

import java.io.InputStream;


public class MenuConfig extends AbstractMenu {
    public MenuConfig() {
        super(System.in);
    }

    public MenuConfig(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public int getMinInputNumber() {
        return 1;
    }

    @Override
    public int getMaxInputNumber() {
        return 3;
    }

    @Override
    public String show() {
        return "\nMENU CONFIG\n" +
                "1.Open application\n" +
                "2.View config\n" +
                "3.Save new config";
    }

    public Config getNewConfigFromUser() {
        String usersDB = getStringFromUser("Provide users DB (with .txt or .csv extension):");
        String walletDB = getStringFromUser("Provide wallet DB (with .ser extension):");

        return new Config(usersDB, walletDB);
    }
}
