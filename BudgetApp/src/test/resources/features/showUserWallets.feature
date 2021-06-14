Feature: Showing user wallets
  User should be able to see his/her wallets.

  Scenario: After creating wallets user should be able to see them.
    Given There are no wallets at wallet list
    When I "Jan Kowalski" add new wallet named "My home wallet"
    Then I "Jan Kowalski" can check my wallets

  Scenario: After creating wallets user should be able to see them.
    Given There are no wallets at wallet list
    When I "Jan Kowalski" add new wallet named "My home wallet" and "Work wallet"
    Then I "Jan Kowalski" can check my wallets