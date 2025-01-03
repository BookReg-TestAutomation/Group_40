@checkout
Feature: Checkout Page Functionality

  Background:
    Given I am logged in as "standard_user"


  Scenario: Check if the FirstName field accepts numbers
    Given I am on the checkout page
    When I enter "John123","Carter" and "20540" in the FirstName,LastName and ZipCode field
    And I submit the form
    Then the page should not navigate to the checkout step two page