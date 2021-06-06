package service;

import menu.InvalidInputException;
import model.Config;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConfigServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void ConfigServiceReadConfigWhenNoArgs() throws IOException {
        // Arrange
        String[] args = {};

        // Act
        Config config = ConfigService.readConfig(args);

        // Assert
        assertConfigIsDefault(config);
    }

    @Test
    public void ConfigServiceReadConfigWhenOtherArgs() throws IOException {
        // Arrange
        String[] args = {"bla", "blabla", "blabla"};

        // Act
        Config config = ConfigService.readConfig(args);

        // Assert
        assertConfigIsDefault(config);

    }

    @Test
    public void ConfigServiceReadConfigWhenInvalidArgs() throws IOException {
        // Arrange
        String[] args = {"-c", "notExistingFile.json"};

        exception.expect(FileNotFoundException.class);
        exception.expectMessage("notExistingFile.json"); //(The system cannot find the file specified)");

        // Act
        Config config = ConfigService.readConfig(args);

        // Assert
        // throw exception

    }

    @Test
    public void ConfigServiceReadConfigWhenCorrectArgs() throws IOException {
        // Arrange
        String[] args = {"-c", "src/test/resources/testConfig.json"};

        // Act
        Config config = ConfigService.readConfig(args);

        // Assert
        assertNotNull(config);
        String path = Path.of("").toAbsolutePath().toString();

        assertEquals(path + "/usersTest.csv", config.getUsersDbPath());
        assertEquals(path + "/walletTest.ser", config.getWalletListPath());
    }

    @Test
    public void ConfigServiceGetConfigFromFile() throws IOException {
        // Arrange
        String filePath = "src/test/resources/testConfig.json";

        // Act
        Config config = ConfigService.getConfigFromFile(filePath);

        // Assert
        assertNotNull(config);
        String path = Path.of("").toAbsolutePath().toString();

        assertEquals(path + "/usersTest.csv", config.getUsersDbPath());
        assertEquals(path + "/walletTest.ser", config.getWalletListPath());
    }

    @Test
    public void ConfigServiceGetDefaultConfig() {
        // Arrange

        // Act
        Config config = ConfigService.getDefaultConfig();

        // Assert
        assertConfigIsDefault(config);
    }

    private void assertConfigIsDefault(Config config) {
        String path = Path.of("").toAbsolutePath().toString();

        assertNotNull(config);
        assertEquals(path + "/data/users_db.txt", config.getUsersDbPath());
        assertEquals(path + "/data/WalletList.ser", config.getWalletListPath());
    }
}