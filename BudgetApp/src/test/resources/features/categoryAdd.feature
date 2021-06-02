Feature: Managing categories
  Categories are important. User should be able to add new category to list.

  Scenario: Adding category
    Given My category list is new category list
    When I add category "netflix"
    Then My categories contains "netflix"