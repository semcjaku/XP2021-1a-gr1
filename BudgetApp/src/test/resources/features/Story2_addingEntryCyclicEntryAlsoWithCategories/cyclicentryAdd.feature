Feature: Adding cyclic entries
  Users should be able to add cyclic entry to the cyclic entry list.

  Scenario: Entry list contains monthly cyclic entries after being provided
    Given No cyclic entries were provided yet
    When I add new entry with day of month 20 amount of 99
    Then My cyclic entry list contains provided cyclic entry

  Scenario: Entry list contains monthly cyclic entries after being provided with category
    Given No cyclic entries were provided yet
    When I add new entry with day of month 20 amount of 99 and category "clothes"
    Then My cyclic entry list contains provided cyclic entry

  Scenario: Entry list contains Interval cyclic entries after being provided with category
    Given No cyclic entries were provided yet
    When I add new entry with interval 20 amount of 99 and category "clothes"
    Then My cyclic entry list contains provided cyclic entry

  Scenario: Entry list contains Interval cyclic entries after being provided
    Given No cyclic entries were provided yet
    When I add new entry with interval 20 amount of 99
    Then My cyclic entry list contains provided cyclic entry
