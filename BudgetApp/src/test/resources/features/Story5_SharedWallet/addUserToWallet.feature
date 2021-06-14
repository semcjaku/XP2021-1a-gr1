Feature: Possibility to add another user to wallet
  Users should be able to add another user to his/hers wallet.

  Scenario: Another user can be added
    Given There is "Jan Kowalski" wallet named "Home wallet"
    When I add user "Anna Kowalska" to wallet named "Home wallet"
    Then "Anna Kowalska" has access to wallet named "Home wallet"

  Scenario: Another user can not be added to shared users duplicated owner name
    Given There is "Jan Kowalski" wallet named "Home wallet"
    When I add user "Jan Kowalski" to wallet named "Home wallet"
    Then "Jan Kowalski" is not in shared users of wallet "Home wallet"

  Scenario: Another user can not be added to shared users duplicated shared name
    Given There is "Jan Kowalski" wallet named "Home wallet" and "Anna Kowalska" shared user
    When I add user "Anna Kowalska" to wallet named "Home wallet"
    Then "Anna Kowalska" is not repeated in shared users of wallet "Home wallet"
