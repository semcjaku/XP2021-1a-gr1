Feature: Managing entries
  Users should be able to add entry to the entry list.

  Scenario: Entry list is empty by default
    Given No entries were provided yet
    When I check entries now
    Then There are no entries in the entry list

  Scenario: Entry list contains entries after being provided
    Given No entries were provided yet
    When I add new entry with amount of 99 and category "clothes"
    Then My entry list contains provided entry

  Scenario: Entry can be deleted from entry list
    Given Entry list contains one entry with amount 199 on start
    When I remove entry from the entry list
    Then There are no entries in the entry list

  Scenario: Entry can has amount changed
    Given Entry list contains one entry with amount 199 on start
    When I change amount of entry to 12
    Then Entry list contains one entry with amount 12

  Scenario: Entry can has category added
    Given Entry list contains one entry with amount 199 on start
    When I add category "Food" to entry
    Then Entry list contains one entry with amount 199 and categories "Food"

  Scenario: Entry can has category set
    Given Entry list contains one entry with amount 199 on start
    When I set categories "Food,Bar" to entry
    Then Entry list contains one entry with amount 199 and categories "Food,Bar"