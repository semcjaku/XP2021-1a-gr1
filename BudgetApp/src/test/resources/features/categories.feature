Feature: Managing categories
  Categories are important. User should be able to view and modify category list.

  Scenario: Category List have default values
    Given My category list is new category list
    When I get default categories
    Then My categories contains "clothes"
    Then My categories contains "entertainment"
    Then My categories contains "food"

  Scenario: Adding category
    Given My category list is new category list
    When I add category "netflix"
    Then My categories contains "netflix"
