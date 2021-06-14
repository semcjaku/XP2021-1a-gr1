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
}
