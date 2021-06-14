package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Config;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigService {

    public static String configPath = Path.of("").toAbsolutePath() + "/data/config.json";

    public static void setConfigPath(String newConfigPath) {
        configPath = Path.of("").toAbsolutePath() + newConfigPath;
    }

    public static Config readConfig(String[] args) throws IOException {

        Options options = new Options();

        Option input = new Option("c", "config", true, "config file path");
        options.addOption(input);

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);
            String inputFilePath = cmd.getOptionValue("config");

            if (inputFilePath == null || inputFilePath.isEmpty()) {
                return getDefaultConfig();
            }
            configPath = inputFilePath;
            return getConfigFromFile();

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return getDefaultConfig();
        }
    }

    public static Config getConfigFromFile() throws IOException {
        System.out.println("Getting config from path: " + configPath);

        ObjectMapper mapper = new ObjectMapper();

        Config config = mapper.readValue(Paths.get(configPath).toFile(), Config.class);

        config.setUsersDbPath(Path.of("").toAbsolutePath() + config.getUsersDbPath());
        config.setWalletListPath(Path.of("").toAbsolutePath() + config.getWalletListPath());

        return config;

    }

    public static Config getDefaultConfig() {
        System.out.println("No config provided, using default config");

        return new Config(
                Path.of("").toAbsolutePath() + "/data/users_db.txt",
                Path.of("").toAbsolutePath() + "/data/WalletList.ser"
        );
    }

    public static Config saveNewConfig(Config config) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(Paths.get(configPath).toFile(), config);

        return getConfigFromFile();
    }
}
