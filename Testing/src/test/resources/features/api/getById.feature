Feature: Get Book by ID API
  As a library system user
  I want to retrieve specific books by their IDs
  So that I can view detailed information about particular books

  Background:
    Given the library system is running at "http://localhost:7081"

    # Scenario: Successfully retrieve different books by ID as admin
  Scenario Outline: Successfully retrieve different books by ID as admin
    Given I am authenticated as "admin" with password "password"
    And there is a book with ID "<bookId>" in the system
    When I send a GET request to "/api/books/<bookId>"
    Then the response status code should be 200
    And the response should contain the book details

    Examples:
      | bookId |
      | 2     |
      | 3      |
      | 4      |
      | 5      |

    #Scenario: Successfully retrieve different books by ID as regular user
  Scenario Outline: Successfully retrieve different books by ID as regular user
    Given I am authenticated as "user" with password "password"
    And there is a book with ID "<bookId>" in the system
    When I send a GET request to "/api/books/<bookId>"
    Then the response status code should be 200
    And the response should contain the book details

    Examples:
      | bookId |
      | 2      |
      | 3     |
      | 4     |
      | 5     |

  # Senario: Attempt to retrieve a book without authentication
  Scenario: Attempt to retrieve a book without authentication
    When I send a GET request to "/api/books/1" without authentication
    Then the response status code should be 401

    #Senario Attempt to retrieve a non-existent book
  Scenario: Attempt to retrieve a non-existent book
    Given I am authenticated as "admin" with password "password"
    When I send a GET request to "/api/books/999"
    Then the response status code should be 404