Feature: Managing entries
  Categories are important. User should be able to view and modify category list.

  Scenario: Entry list is empty by default
    Given No entries were provided yet
    When I check entries now
    Then There are no entries in entry list

  Scenario: Entry list contains entries after being provided
    Given No entries were provided yet
    When I add new entry "99" with category "clothes"
    Then My entry list contains provided entry