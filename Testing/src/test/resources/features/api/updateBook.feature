  # Scenario: Data Type Validation for Id
  Scenario: Data Type Validation for Id
  Given I am authenticated as "admin" with password "password"
  When I send a PUT request to "/api/books/abc123" with the following data:
  | id    | abc123 |
  | title | Valid Title |
  | author| Valid Author |
  Then the response status code is 400
  And the error message should be "Invalid Input Parameters"

  # Scenario: Attempt to Update a Book Without Authentication
  Scenario: Attempt to Update a Book Without Authentication
  When I send a PUT request to "/api/books/1" without authentication with the following data:
  | id    | 1             |
  | title | Unauthenticated Update |
  | author| No Author      |
  Then the response status code is 401
  And the error message should be "You are not authorized to update the book"

  # Scenario: Special Character Handling in Author Name
  Scenario: Special Character Handling in Author Name
  Given I am authenticated as "admin" with password "password"
  When I send a PUT request to "/api/books/1" with the following data:
  | id    | 1                          |
  | title | Special Characters Title   |
  | author| Special @#$%               |
  Then the response status code is 400
  And the error message should be "Invalid Input Parameters"

  # Scenario: Attempt to Update a Book with Missing ID in Body
  Scenario: Attempt to Update a Book with Missing ID in Body
  Given I am authenticated as "admin" with password "password"
  When I send a PUT request to "/api/books/1" with the following data:
  | title | Updated Title |
  | author| Updated Author|
  Then the response status code is 400
  And the error message should be "Invalid Input Parameters"

  # Scenario: Attempt to Update a Book with Null Values
  Scenario: Attempt to Update a Book with Null Values
  Given I am authenticated as "admin" with password "password"
  When I send a PUT request to "/api/books/1" with the following data:
  | id    | 1    |
  | title | null |
  | author| null |
  Then the response status code is 400
  And the error message should be "Invalid Input Parameters"
