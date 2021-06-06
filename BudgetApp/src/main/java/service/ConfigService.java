package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Config;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigService {

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

            return getConfigFromFile(inputFilePath);

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return getDefaultConfig();
        }
    }

    public static Config getConfigFromFile(String filepath) throws IOException {
        System.out.println("Getting config from path: " + filepath);

        ObjectMapper mapper = new ObjectMapper();

        Config config = mapper.readValue(Paths.get(filepath).toFile(), Config.class);

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
}
