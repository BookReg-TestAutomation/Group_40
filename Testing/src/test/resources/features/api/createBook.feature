Feature: Create Book API
  As a user of the library system
  I want to interact with the book creation API
  So that I can manage books effectively

  Background:
    Given the library system is running at "http://localhost:7081"

      # Scenario: Attempt to Create a Book with Invalid Data
  Scenario: Attempt to Create a Book with Invalid Data
    Given I am authenticated as "admin" with password "password"
    When I send a POST request to "/api/books" with title null and an author "tom cruise"
    Then the response status code is 400
    And the response should contain "Invalid title" error message

  # Scenario: Data Type Validation for Id
  Scenario: Data Type Validation For Id
    Given I am authenticated as "admin" with password "password"
    When I send a POST request to "/api/books" with id "abc123" and the following data:
      | title        | Book123 |
      | author       | Author  |
    Then the response status code is 400

    # Scenario: Attempt to Create a Book Without Authentication
  Scenario: Attempt to Create a Book Without Authentication
    When I send a POST request to "/api/books" without authentication with the following data:
      | title        | Unauthenticated Book |
      | author       | No Author            |
    Then the response status code is 401

  # Scenario: Special Character Handling
  Scenario: Special Character Handling in Author Name
    Given I am authenticated as "admin" with password "password"
    When I send a POST request to "/api/books" with the following data:
      | title        | Special Characters Book |
      | author       | Special @#$%    |
    Then the response status code is 400
    And the response should correctly include special characters