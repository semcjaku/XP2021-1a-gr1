Feature: Login user by email and password
  User can register and log in.

  Scenario: Login user
    Given Users data has 1 users
    When I login user "user1@gmail.com" "userpass1"
    Then User exist

  Scenario: Login invalid user
    Given Users data has 1 users
    When I login user "user1@gmail.com" "userpassinvalid"
    Then User not exist

  Scenario: Login not exist user
    Given Users data has 10 users
    When I login user "user11@gmail.com" "userpass11"
    Then User not exist