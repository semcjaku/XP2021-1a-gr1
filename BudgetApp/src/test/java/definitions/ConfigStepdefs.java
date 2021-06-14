package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Config;
import service.ConfigService;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConfigStepdefs {

    private String[] arguments;
    private Config config;

    @Given("Program arguments: {string}")
    public void programArguments(String arg0) {
        arguments = arg0.split(" ");
    }

    @When("I start application")
    public void iStartApplication() throws IOException {
        config = ConfigService.readConfig(arguments);
    }

    @Then("Default config is read")
    public void defaultConfigIsRead() {
        String path = Path.of("").toAbsolutePath().toString();

        assertNotNull(config);
        assertEquals(path + "/data/users_db.txt", config.getUsersDbPath());
        assertEquals(path + "/data/WalletList.ser", config.getWalletListPath());
    }

    @Then("Config from test file is read")
    public void configFromTestFileIsRead() {
        String path = Path.of("").toAbsolutePath().toString();

        assertNotNull(config);
        assertEquals(path + "/usersTest.csv", config.getUsersDbPath());
        assertEquals(path + "/walletTest.ser", config.getWalletListPath());
    }

    @Given("Config service with default path")
    public void configServiceWithDefaultPath() {
        ConfigService.setConfigPath("/src/test/resources/newTestConfigFeatures.json");
    }

    @When("I add config with users database {string} and wallet database {string}")
    public void iAddConfigWithUsersDatabaseAndWalletDatabase(String arg0, String arg1) throws IOException {
        Config configToSave = new Config(arg0, arg1);

        ConfigService.saveNewConfig(configToSave);
    }

    @Then("Config saved to file have user database {string} and wallet database {string}")
    public void configSavedToFileHaveUserDatabaseAndWalletDatabase(String arg0, String arg1) throws IOException {

        Config configFromFile = ConfigService.getConfigFromFile();

        assertEquals(Path.of("").toAbsolutePath() + arg0, configFromFile.getUsersDbPath());
        assertEquals(Path.of("").toAbsolutePath() + arg1, configFromFile.getWalletListPath());
    }
}
