@cart
Feature: Cart Page Functionality

  Background:
    Given I am logged in as "standard_user"

  Scenario: Verify the cart page is displayed
    When I navigate to the cart page
    Then I should see the cart page

  Scenario: Verify adding an item updates the cart
    Given I add the item "Sauce Labs Backpack" to the cart
    When I navigate to the cart page
    Then I should see "Sauce Labs Backpack" in the cart

  Scenario: Verify removing an item updates the cart
    Given I add the item "Sauce Labs Backpack" to the cart
    And I add the item "Sauce Labs Bolt T-Shirt" to the cart
    When I navigate to the cart page
    When I remove the item "Sauce Labs Bolt T-Shirt" from the cart on the cart page
    Then I should not see "Sauce Labs Bolt T-Shirt" in the cart
    And I should see "Sauce Labs Backpack" in the cart
