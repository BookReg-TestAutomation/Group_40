Feature: Update Book API
  As a user of the library system
  I want to interact with the book update API
  So that I can modify book details effectively

  Background:
    Given the library system is running at "http://localhost:7081"

  # Scenario: Update Book with Mismatched IDs
  Scenario: Update Book with Mismatched IDs
    Given I am authenticated as "admin" with password "password"
    When I send a PUT request to "/api/books/2" with the following data:
      | id    | 1             |
      | title | Mismatched ID |
      | author| Author Test   |
    Then the response status code is 400
#    And the error message should be "ID in URL does not match ID in body"

  # Scenario: Update Non-Existent Book
  Scenario: Update Non-Existent Book
    Given I am authenticated as "admin" with password "password"
    When I send a PUT request to "/api/books/9999" with the following data:
      | id    | 9999         |
      | title | Nonexistent  |
      | author| Unknown      |
    Then the response status code is 404
    And the error message should be "Book not found"

  # Scenario: Attempt to Update a Book with Empty Strings
  Scenario: Attempt to Update a Book with Empty Strings
    Given I am authenticated as "admin" with password "password"
    When I send a PUT request to "/api/books/3" with the following data:
      | id    | 3    |
      | title |      |
      | author|      |
    Then the response status code is 400
    And the error message should be "Invalid Input Parameters"

  # Scenario: Successful Update of a Book
  Scenario: Successful Update of a Book
    Given I am authenticated as "admin" with password "password"
    When I send a PUT request to "/api/books/4" with the following data:
      | id    | 4                |
      | title | Successfully Updated Title |
      | author| Updated Author    |
    Then the response status code is 200
    And the response should indicate success with a message "Book updated successfully"

    # Scenario: Regular User Attempts to Update a Book
  Scenario: Regular User Attempts to Update a Book
    Given I am authenticated as "user" with password "password"
    When I send a PUT request to "/api/books/4" with the following data:
      | id    | 4                      |
      | title | Updated Title by User  |
      | author| User Updated Author    |
    Then the response status code is 403
    And the error message should be "User is not permitted."