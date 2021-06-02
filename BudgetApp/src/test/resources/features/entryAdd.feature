Feature: Managing entries
  Users should be able to add entry to the entry list.

  Scenario: Entry list contains entries after being provided
    Given No entries were provided yet
    When I add new entry with amount of 99 and category "clothes"
    Then My entry list contains provided entry

  Scenario: Entry list contains entries after being provided with multi category
    Given No entries were provided yet
    When I add new entry with amount of 199 and category "car;fuel"
    Then My entry list contains provided entry