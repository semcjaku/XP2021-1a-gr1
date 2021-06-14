Feature: Saving config to file
  Program has to read configuration from user and save it to .json file.

  Scenario: Save new config
    Given Config service with default path
    When I add config with users database "data/usersTest.csv" and wallet database "data/walletTest.ser"
    Then Config saved to file have user database "data/usersTest.csv" and wallet database "data/walletTest.ser"