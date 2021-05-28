package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void UserConstructorShouldCreateIdTest() {
        // Arrange

        // Act
        User user = new User();

        // Assert
        assertNotNull(user.getId());
    }

    @Test
    public void UserParametrizedConstructorShouldCreateUserObjectTest() {
        // Arrange
        String email = "Email";
        String password = "pass123";
        // Act
        User user = new User(email, password);

        // Assert
        assertNotNull(user.getId());
        assertNotNull(user.getEmail());
        assertNotNull(user.getPassword());

        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void UserParametrizedConstructorShouldCreateUserObjectWithIdTest() {
        // Arrange
        String email = "Email";
        String password = "pass123";
        String id = "1";
        // Act
        User user = new User(id, email, password);

        // Assert
        assertNotNull(user.getId());
        assertNotNull(user.getEmail());
        assertNotNull(user.getPassword());

        assertEquals(id, user.getId());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void UserGetEmailShouldReturnEmailTest() {
        // Arrange
        String email = "Email";
        String password = "pass123";
        // Act
        User user = new User(email, password);

        // Assert
        assertNotNull(user.getEmail());

        assertEquals(email, user.getEmail());
    }

    @Test
    public void UserGetPasswordShouldReturnPasswordTest() {
        // Arrange
        String email = "Email";
        String password = "pass123";
        // Act
        User user = new User(email, password);

        // Assert
        assertNotNull(user.getPassword());

        assertEquals(password, user.getPassword());
    }

    @Test
    public void UserSetEmailShouldChangeEmailTest() {
        // Arrange
        String email = "Email";
        String email2 = "Email2";
        String password = "pass123";
        User user = new User(email, password);

        // Act
        user.setEmail(email2);

        // Assert
        assertNotNull(user.getEmail());
        assertNotEquals(email, user.getEmail());
        assertEquals(email2, user.getEmail());
    }

    @Test
    public void UserSetPasswordShouldChangePasswordTest() {
        // Arrange
        String email = "Email";
        String password = "pass123";
        String password2 = "pass123new";
        User user = new User(email, password);

        // Act
        user.setPassword(password2);

        // Assert
        assertNotNull(user.getPassword());
        assertNotEquals(password, user.getPassword());
        assertEquals(password2, user.getPassword());
    }

    @Test
    public void UserToStringTest() {
        // Arrange
        String email = "Email";
        String password = "pass123";
        User user = new User(email, password);
        String id = user.getId();

        // Assert
        assertEquals(user.toString(), "User{Email@" + id + '}');
    }
}
