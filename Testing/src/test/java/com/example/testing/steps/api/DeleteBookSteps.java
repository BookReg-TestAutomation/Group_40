package com.example.testing.steps.api;

import com.example.testing.utils.ApiTestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteBookSteps {

    private String token;
    private Response response;
    private String baseUrl;
    private List<Map<String, Object>> books;
    private int validIdForDelete;

    //    @Given("a book with id {string} exists in the system")
//    public void a_book_with_id_exists_in_the_system(String bookId) {
//        ApiTestContext apiContext = ApiTestContext.getInstance();
//
//        Map<String, String> bookData = new HashMap<>();
//        bookData.put("title", "Delete Test");
//        bookData.put("author", "Delete Test Author");
//        bookData.put("id", bookId);
//        RestAssured.given()
//                .auth().basic(apiContext.getUsername(), apiContext.getPassword())  // Basic Authentication
//                .contentType("application/json")
//                .body(bookData)
//                .when()
//                .post("http://localhost:7081/api/books");
//    }
//    @Given("a book with id {string} exists in the system")
//    public void a_book_with_id_exists_in_the_system(String bookId) {
//        ApiTestContext apiContext = ApiTestContext.getInstance();
//
//        try {
//            // Send a GET request to retrieve all books
//            Response getResponse = RestAssured.given()
//                    .auth().basic(apiContext.getUsername(), apiContext.getPassword())
//                    .contentType("application/json")
//                    .when()
//                    .get("http://localhost:7081/api/books");
//
//            // Check if the GET request was successful
//            if (getResponse.getStatusCode() != 200) {  // Assuming 200 OK is the expected status code
//                throw new RuntimeException("Failed to fetch books. Response code: " + getResponse.getStatusCode()
//                        + ", Response body: " + getResponse.getBody().asString());
//            }
//
//            // Parse the response to get the first book
//            List<Map<String, Object>> books = getResponse.jsonPath().getList("$");
//            if (books.isEmpty()) {
//                throw new RuntimeException("No books found in the system.");
//            }
//
//            Map<String, Object> firstBook = books.get(1);
//            int firstBookId = (int) firstBook.get("id");
//
//            // Verify that the first book's id is 2
//            if (firstBookId != 2) {
//                throw new RuntimeException("First book ID is not 2. Actual ID: " + firstBookId);
//            }
//        } catch (Exception e) {
//            System.err.println("Error occurred: " + e.getMessage());
//            throw e;
//        }
//    }

    //    @Given("a valid book is created in the system")
//    public void validBookIsCreatedInTheSystem() {
//        ApiTestContext apiContext = ApiTestContext.getInstance();
//
//        // Define the book details (this could be dynamically generated if needed)
//        Map<String, Object> bookDetails = Map.of(
//                "title", "test book to delete",  // You can dynamically generate the title or use a fixed one
//                "author", "delete test author"  // Similarly for the author
//        );
//
//        try {
//            // Send a POST request to create a new book
//            Response postResponse = RestAssured.given()
//                    .auth().basic(apiContext.getUsername(), apiContext.getPassword())
//                    .contentType("application/json")
//                    .body(bookDetails)
//                    .when()
//                    .post("http://localhost:7081/api/books");
//
//            // Check if the POST request was successful
//            if (postResponse.getStatusCode() != 201) {
//                throw new RuntimeException("Failed to create book. Response code: " + postResponse.getStatusCode()
//                        + ", Response body: " + postResponse.getBody().asString());
//            }
//
//            // Parse the response to get the created book's ID and other details
//            Map<String, Object> createdBook = postResponse.jsonPath().getMap("$");
//            validIdForDelete = (int) createdBook.get("id");  // Save the ID for later deletion
//
//            // Optionally, you can store other book details like title and author if needed
//            String bookTitle = (String) createdBook.get("title");
//            String bookAuthor = (String) createdBook.get("author");
//
//            // Print or log the book details for reference
//            System.out.println("Created Book ID: " + validIdForDelete);
//            System.out.println("Created Book Title: " + bookTitle);
//            System.out.println("Created Book Author: " + bookAuthor);
//
//        } catch (Exception e) {
//            System.err.println("Error occurred while creating the book: " + e.getMessage());
//            throw e;
//        }
//    }


    @Given("a valid book is exists in the system")
    public void validBookExistsInTheSystem() {
        ApiTestContext apiContext = ApiTestContext.getInstance();

        try {
            // Send a GET request to retrieve all books
//            Response getResponse = RestAssured.given()
//                    .auth().basic(apiContext.getUsername(), apiContext.getPassword())
//                    .contentType("application/json")
//                    .when()
//                    .get("http://localhost:7081/api/books");
            Response getResponse=apiContext.sendGetRequest("/api/books");

            // Check if the GET request was successful
            if (getResponse.getStatusCode() != 200) {  // Assuming 200 OK is the expected status code
                throw new RuntimeException("Failed to fetch books. Response code: " + getResponse.getStatusCode()
                        + ", Response body: " + getResponse.getBody().asString());
            }

            // Parse the response to get the first book
            List<Map<String, Object>> books = getResponse.jsonPath().getList("$");
            if (books.isEmpty()) {
                throw new RuntimeException("No books found in the system.");
            }

            Map<String, Object> firstBook = books.get(0);
            Object idObject = firstBook.get("id");

            validIdForDelete = (int) idObject;

        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            throw e;
        }
    }

//working function
    @When("I send a DELETE request to {string} with it's id")
    public void sendDeleteRequest(String endpoint) {
        ApiTestContext context = ApiTestContext.getInstance();
        response=context.sendDeleteRequestWithAuth(endpoint,Integer.toString(validIdForDelete));
    }

    @When("I send a DELETE request to {string} with Id {string}")
    public void sendDeleteRequestWithIdPassedInUrl(String endpoint, String id) {
        ApiTestContext context = ApiTestContext.getInstance();
        response = context.sendDeleteRequestWithAuth(endpoint,id);
    }

//    @When("I send a DELETE request to {string} with id {string}")
//    public void sendDeleteRequestWithIdPassedAsParam(String endpoint,String id) {
//        ApiTestContext context = ApiTestContext.getInstance();
//        response = given()
//                .auth().basic(context.getUsername(), context.getPassword())  // Basic Authentication
//                .contentType("application/json")
//                .when()
//                .delete(endpoint+id);
//    }

    @When("I send a DELETE request to {string} with the book's id")
    public void sendDeleteRequestToDeleteCreatedBook(String endpoint) {
        ApiTestContext apiContext = ApiTestContext.getInstance();

            // Send DELETE request to delete the book using the ID
            Response deleteResponse = RestAssured.given()
                    .auth().basic(apiContext.getUsername(), apiContext.getPassword())
                    .contentType("application/json")
                    .when()
                    .delete("http://localhost:7081/api/books/6");
    }


    @When("I send a DELETE request to {string} with the id")
    public void sendDeleteRequestWithId(String endpoint) {
        ApiTestContext context = ApiTestContext.getInstance();
        response = RestAssured.given()
                .auth().basic(context.getUsername(), context.getPassword())
                .when()
                .delete(baseUrl + endpoint+validIdForDelete);
    }

    @When("I send a DELETE request to {string} without authentication")
    public void SendDeleteRequestWithoutAuth(String endpoint) {
        response = given()
                .delete(endpoint);
    }

    @Then("the response status code is equal to {int}")
    public void checkResponseStatusCodeInDeleteRequest(int expectedStatusCode) {
        ApiTestContext apiContext = ApiTestContext.getInstance();
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("the response should contain {string}")
    public void the_response_should_contain(String message) {
        response.then().body(containsString(message));
    }
}
