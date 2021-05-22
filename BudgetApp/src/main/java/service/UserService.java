package service;

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
    private String file = "./BudgetApp/data/users_db.txt";

    public UserService() throws FileNotFoundException {
        this.users = new LinkedList<User>();
    }

    UserService(List<User> users) {
        this.users = users;
    }

    public User login(String name, String password) {
        return users.stream()
                .filter(u -> u.getEmail().equals(name)
                        && u.getPassword().equals(password))
                .findFirst().orElse(null);
    }

    public void loadUsersOnStart() throws FileNotFoundException {
        Scanner scan = new Scanner(new File(this.file));
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String[] split = line.split(";");
            this.users.add(new User(split[0], split[1], split[2]));
            //Here you can manipulate the string the way you want
        }
        scan.close();
    }

    public void saveOnStop() throws IOException {
        Files.deleteIfExists(Path.of(this.file));
        PrintWriter out = new PrintWriter(this.file);
        for (User user : users) {
            out.println(user.getId() + ";" + user.getEmail() + ";" + user.getPassword());
        }
        out.close();
    }
}
