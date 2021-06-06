package service;

import model.Config;
import model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class UserService {
    public List<User> users;

    public UserService() {
        this.users = new LinkedList<User>();
    }

    public UserService(List<User> users) {
        this.users = users;
    }

    public User login(String name, String password) {
        return users.stream()
                .filter(u -> u.getEmail().equals(name)
                        && u.getPassword().equals(password))
                .findFirst().orElse(null);
    }

    public User register(String name, String password) {
        boolean userExists = users.stream()
                                .anyMatch(u -> u.getEmail().equals(name));
        if (userExists) {
            return null;
        }

        User newUser = new User(name, password);
        users.add(newUser);
        return  newUser;
    }

    public void loadUsersOnStart(String usersDbPath) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(usersDbPath));
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String[] split = line.split(";");
            this.users.add(new User(split[0], split[1], split[2]));
            //Here you can manipulate the string the way you want
        }
        scan.close();
    }

    public void saveOnStop(String usersDbPath) throws IOException {
        Files.deleteIfExists(Path.of(usersDbPath));
        PrintWriter out = new PrintWriter(usersDbPath);
        for (User user : users) {
            out.println(user.getId() + ";" + user.getEmail() + ";" + user.getPassword());
        }
        out.close();
    }
}
