package service;

import model.User;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void UserServiceConstructorShouldCreateEmptyListTest() throws FileNotFoundException {
        // Arrange

        // Act
        UserService userService = new UserService();

        // Assert
        assertNotNull(userService.users);
        assertEquals(0, userService.users.size());
    }

    @Test
    public void UserServiceParametrizedConstructorShouldCopyListTest() {
        // Arrange
        List<User> users = new LinkedList<User>();
        users.add(new User("1","1"));
        // Act
        UserService userService = new UserService(users);

        // Assert
        assertNotNull(userService.users);
        assertNotEquals(0, userService.users.size());
        assertEquals(1, userService.users.size());
    }

    @Test
    public void UserServiceLoginShouldReturnUserWhenInputsAreCorrectTest() {
        // Arrange
        List<User> users = new LinkedList<User>();
        users.add(new User("1","1"));
        UserService userService = new UserService(users);
        // Act
        User user = userService.login("1", "1");
        // Assert
        assertNotNull(user);
        assertNotNull(user.getId());
        assertNotNull(user.getPassword());
        assertNotNull(user.getEmail());
    }

    @Test
    public void UserServiceLoginShouldReturnNullWhenNameAreIncorrectTest() {
        // Arrange
        List<User> users = new LinkedList<User>();
        users.add(new User("1","1"));
        UserService userService = new UserService(users);
        // Act
        User user = userService.login("incorrect", "1");
        // Assert
        assertNull(user);
    }

    @Test
    public void UserServiceLoginShouldReturnNullWhenPasswordAreIncorrectTest() {
        // Arrange
        List<User> users = new LinkedList<User>();
        users.add(new User("1","1"));
        UserService userService = new UserService(users);
        // Act
        User user = userService.login("1", "incorrect");
        // Assert
        assertNull(user);
    }

    @Test
    public void UserServiceLoginShouldReturnNullWhenUserNotFoundTest() {
        // Arrange
        List<User> users = new LinkedList<User>();
        users.add(new User("1","1"));
        UserService userService = new UserService(users);
        // Act
        User user = userService.login("2", "2");
        // Assert
        assertNull(user);
    }
}
