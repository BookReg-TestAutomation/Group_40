Feature: Delete Book API
  As a user of the library system
  I want to interact with the book deletion API
  So that I can remove books effectively

  Background:
    Given the library system is running at "http://localhost:7081"

  # Scenario: Successfully Delete a Book (Admin Only)
  Scenario: Successfully Delete a Book
    Given I am authenticated as "admin" with password "password"
    And a valid book is exists in the system
    When I send a DELETE request to "/api/books/" with it's id
    Then the response status code is equal to 200

  # Scenario: Attempt to Delete a Book That Does Not Exist
  Scenario: Attempt to Delete a Book That Does Not Exist
    Given I am authenticated as "admin" with password "password"
    When I send a DELETE request to "/api/books/" with Id "999"
    Then the response status code is equal to 404

  # Scenario: Attempt to Delete a Book Without Authentication
  Scenario: Attempt to Delete a Book Without Authentication
    When I send a DELETE request to "/api/books/123" without authentication
    Then the response status code is equal to 401

  # Scenario: User Access Restriction for Delete API
  Scenario: User Access Restriction for Delete API
    Given I am authenticated as "user" with password "password"
    And a valid book is exists in the system
    When I send a DELETE request to "/api/books/" with it's id
    Then the response status code is equal to 400

    # Scenario: Data Type Validation for Id
  Scenario: Data Type Validation for Id
    Given I am authenticated as "admin" with password "password"
    When I send a DELETE request to "/api/books/" with Id "abc123"
    Then the response status code is equal to 400

#senario: Delete Book API Without Specifying an ID
  Scenario: Attempt to Delete a Book Without Specifying an ID
    Given I am authenticated as "admin" with password "password"
    When I send a DELETE request to "/api/books/" without specifying an id
    Then the response status code is equal to 400





