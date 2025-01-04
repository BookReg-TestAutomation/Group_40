Feature: Update Book API Scenarios
  This feature file contains tests for updating books in the system.

  Background:
    Given the library system is running at "http://localhost:7081"

#  Scenario: Data Type Validation for Id
  Scenario: Data Type Validation for Id
    Given I am authenticated as "admin" with password "password"
    When I send a PUT request to "/api/books/2" with the following data:
      | id    | 2      |
      | title | Valid Title |
      | author| Valid Author|
    Then the response status code is 400

#  Scenario: Attempt to Update a Book Without Authentication
  Scenario: Attempt to Update a Book Without Authentication
    When I send a PUT request to "/api/books/1" without authentication with the following data:
      | id    | 1             |
      | title | Unauthenticated Update |
      | author| No Author      |
    Then the response status code is 401

#  Scenario: Special Character Handling in Author Name
  Scenario: Special Character Handling in Author Name
    Given I am authenticated as "admin" with password "password"
    When I send a PUT request to "/api/books/1" with the following data:
      | id    | 1                          |
      | title | Special Characters Title   |
      | author| Special @#$%               |
    Then the response status code is 400
    And the error message should be "Invalid Input Parameters"

#  Scenario: Attempt to Update a Book with Missing ID in Body
  Scenario: Attempt to Update a Book with Missing ID in Body
    Given I am authenticated as "admin" with password "password"
    When I send a PUT request to "/api/books/1" with the following data:
      | title | Updated Title |
      | author| Updated Author|
    Then the response status code is 400

  Scenario: Successfully Update a Book
    Given I am authenticated as "admin" with password "password"
    When I send a PUT request to "/api/books/1" with the following data:
      | id    | 1                  |
      | title | Updated Book Title |
      | author| Updated Author Name|
    Then the response status code is 200

  # Scenario: Update Non-Existent Book
  Scenario: Update Non-Existent Book
    Given I am authenticated as "admin" with password "password"
    When I send a PUT request to "/api/books/9999" with the following data:
      | id    | 9999         |
      | title | Nonexistent  |
      | author| Unknown      |
    Then the response status code is 404

  # Scenario: Attempt to Update a Book with Empty Strings
  Scenario: Attempt to Update a Book with Empty Strings
    Given I am authenticated as "admin" with password "password"
    When I send a PUT request to "/api/books/3" with the following data:
      | id    | 3    |
      | title |      |
      | author|      |
    Then the response status code is 400

  # Scenario: Successful Update of a Book
  Scenario: Successful Update of a Book
    Given I am authenticated as "user" with password "password"
    When I send a PUT request to "/api/books/2" with the following data:
      | id    | 2                |
      | title | Successfully Updated Title |
      | author| Updated Author    |
    Then the response status code is 403


