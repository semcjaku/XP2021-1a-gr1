package definitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.User;
import service.UserService;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class UserStepdefs
{
    private UserService userService;
    private User user;

    @Given("Users data is empty")
    public void usersDataIsEmpty() {
        userService = new UserService();
    }

    @Given("Users data has {int} users")
    public void usersDataHasXUsers(int number) {
        List<User> users = new LinkedList<User>();
        for (int i = 1; i <= number; i++ ) {
            users.add(new User(String.format("user%d", i), String.format("userpass%d", number)));
        }
        userService = new UserService(users);
    }

    @When("I register user {string} {string}")
    public void iRegisterUser(String name, String password) {
        user = userService.register(name, password);
    }

    @When("I login user {string} {string}")
    public void iLoginUser(String name, String password) {
        user = userService.login(name, password);
    }

    @Then("User exist")
    public void userExist() {
        assertNotNull(user);
    }

    @Then("User not exist")
    public void userNotExist() {
        assertNull(user);
    }
}
