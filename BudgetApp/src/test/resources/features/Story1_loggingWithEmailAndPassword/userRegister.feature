Feature: Register user by email and password
  User can register to application.

  Scenario: Register user
    Given Users data is empty
    When I register user "name@gmail.com" "password"
    Then User exist

  Scenario: Register exist user
    Given Users data has 10 users
    When I register user "user1@gmail.com" "userpass1"
    Then User not exist