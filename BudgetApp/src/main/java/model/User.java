package model;

import java.util.UUID;

public class User {
    private String Id;
    private String Email;
    private String Password;

    User() {
        this.Id = UUID.randomUUID().toString();
    }

    public User(String email, String password) {
        this.Id = UUID.randomUUID().toString();
        this.Email = email;
        this.Password = password;
    }

    public User(String id, String email, String password) {
        this.Id = id;
        this.Email = email;
        this.Password = password;
    }

    public String getId() {
        return Id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "User{" + Email + '@' + Id + '}';
    }
}
