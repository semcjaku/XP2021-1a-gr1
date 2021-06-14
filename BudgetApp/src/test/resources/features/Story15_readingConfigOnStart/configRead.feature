Feature: Reading config from json file and default config
  Program has to read configuration from json file on start, and if not specified, then return default config.

  Scenario: Read default config
    Given Program arguments: ""
    When I start application
    Then Default config is read

  Scenario: Read config from file
    Given Program arguments: "-c src/test/resources/testConfig.json"
    When I start application
    Then Config from test file is read