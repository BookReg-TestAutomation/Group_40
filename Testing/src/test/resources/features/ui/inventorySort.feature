Feature: Product Sorting Functionality

  Background:
    Given I am logged in as "standard_user"

  Scenario: Sort products by name from A to Z
    When I click on the sort dropdown
    And I select "Name (A to Z)" option
    Then products should be sorted alphabetically in ascending order

  Scenario: Sort products by name from Z to A
    When I click on the sort dropdown
    And I select "Name (Z to A)" option
    Then products should be sorted alphabetically in descending order

  Scenario: Sort products by price from low to high
    When I click on the sort dropdown
    And I select "Price (low to high)" option
    Then products should be sorted by price in ascending order

  Scenario: Sort products by price from high to low
    When I click on the sort dropdown
    And I select "Price (high to low)" option
    Then products should be sorted by price in descending order