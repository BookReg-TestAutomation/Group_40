Feature: Inventory Page Functionality

  Background:
    Given I am logged in as "standard_user"

  Scenario: Verify items are displayed on the inventory page
    Then I should see a list of items displayed

  Scenario: Verify sorting functionality on the inventory page
    When I select sorting option "Price (low to high)"
    Then the items should be sorted in ascending order of price

  Scenario: Add an item to the cart
    When I add the item "Sauce Labs Backpack" to the cart
    Then the cart badge should display "1"

  Scenario: Remove an item from the cart
    And I add the item "Sauce Labs Backpack" to the cart
    When I remove the item "Sauce Labs Backpack" from the cart
    Then the cart badge should display "0"