Feature: Possibility to add or archive wallet
  Users should be able to add new wallet or archive existing one.

  Scenario: Wallet can be added
    Given There are no wallets at wallet list
    When I add new wallet named "Main"
    Then Wallet list contains wallet named "Main"

  Scenario: Wallet can be archived
    Given There is one wallet named "Savings" on wallet list
    When I archive wallet named "Savings"
    Then Wallet list is empty