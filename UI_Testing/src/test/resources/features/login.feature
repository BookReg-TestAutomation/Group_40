Feature: Login Functionality

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter username "standard_user" and password "secret_sauce"
    Then I should be redirected to the inventory page

  Scenario: Failed login with invalid credentials
    Given I am on the login page
    When I enter username "invalid_user" and password "invalid_password"
    Then I should see an error message "Epic sadface: Username and password do not match any user in this service"

  Scenario: Failed login with locked out user
    Given I am on the login page
    When I enter username "locked_out_user" and password "secret_sauce"
    Then I should see an error message "Epic sadface: Sorry, this user has been locked out."