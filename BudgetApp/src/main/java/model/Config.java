package model;

public class Config {

    private String usersDbPath;
    private String walletListPath;

    public Config() {
    }

    public Config(String usersDbPath, String walletListPath) {
        this.usersDbPath = usersDbPath;
        this.walletListPath = walletListPath;
    }

    public String getUsersDbPath() {
        return usersDbPath;
    }

    public String getWalletListPath() {
        return walletListPath;
    }

    public void setUsersDbPath(String usersDbPath) {
        this.usersDbPath = usersDbPath;
    }

    public void setWalletListPath(String walletListPath) {
        this.walletListPath = walletListPath;
    }

}
