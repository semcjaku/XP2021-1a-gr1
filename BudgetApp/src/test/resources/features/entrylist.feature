Feature: Managing entries
  Users should be able to add entry to the entry list.

  Scenario: Entry list is empty by default
    Given No entries were provided yet
    When I check entries now
    Then There are no entries in the entry list

  Scenario: Entry list contains entries after being provided
    Given No entries were provided yet
    When I add new entry with amount of "99" and category "clothes"
    Then My entry list contains provided entry

  Scenario: Entry can be deleted from entry list
    Given Entry list contains one entry with amount "199"
    When I remove entry from the entry list
    Then There are no entries in the entry list
