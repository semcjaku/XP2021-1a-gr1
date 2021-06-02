Feature: Managing users
 User can register and log in.

  Scenario: Register user
    Given Users data is empty
    When I register user "name" "password"
    Then User exist

  Scenario: Register exist user
    Given Users data has 10 users
    When I register user "user1" "userpass1"
    Then User not exist

  Scenario: Login user
    Given Users data has 1 users
    When I login user "user1" "userpass1"
    Then User exist

  Scenario: Login invalid user
    Given Users data has 1 users
    When I login user "user1" "userpassinvalid"
    Then User not exist