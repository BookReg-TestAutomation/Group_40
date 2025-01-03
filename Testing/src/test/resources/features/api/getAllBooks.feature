Feature: Get All Books API
  As a library system user
  I want to retrieve all books
  So that I can view the complete book inventory

  Background:
    Given the library system is running at "http://localhost:7081"

  Scenario: Successfully retrieve all books as admin user
    Given I am authenticated as "admin" with password "password"
    When I send a GET request to "/api/books"
    Then the response status code should be 200
    And the response should contain a list of books

  Scenario: Successfully retrieve all books as regular user
    Given I am authenticated as "user" with password "password"
    When I send a GET request to "/api/books"
    Then the response status code should be 200
    And the response should contain a list of books

  Scenario: Attempt to retrieve books without authentication
    When I send a GET request to "/api/books" without authentication
    Then the response status code should be 401
