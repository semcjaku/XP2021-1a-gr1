package model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class User implements Serializable {
    private String id;
    private String email;
    private String password;
//    private List<Wallet> wallets;

    User() {
        this.id = UUID.randomUUID().toString();
    }

    public User(String email, String password) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
    }

    public User(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + email + '@' + id + '}';
    }
}
