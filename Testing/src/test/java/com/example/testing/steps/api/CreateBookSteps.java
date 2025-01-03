package com.example.testing.steps.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import com.example.testing.utils.ApiTestContext;

public class CreateBookSteps {

    private Response response;

    @When("I send a POST request to {string} with the following data:")
    public void iSendAPostRequestToWithTheFollowingData(String endpoint, Map<String, String> bookData) {
        ApiTestContext apiContext = ApiTestContext.getInstance();
        response = apiContext.sendPostRequest(endpoint, bookData);
    }

    @Then("the response status code should {int}")
    public void checkResponseStatusCode(int expectedStatusCode) {
        ApiTestContext apiContext = ApiTestContext.getInstance();
        apiContext.theResponseStatusCodeShouldBe(expectedStatusCode,response);
    }

    // Scenarios for Invalid Inputs
    @When("I send a POST request to {string} with title null and an author {string}")
    public void iSendAPostRequestWithANullTitleAndAnAuthor(String endpoint,String author) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", null);
        requestBody.put("author", author);
        ApiTestContext apiContext = ApiTestContext.getInstance();
        response = apiContext.sendPostRequestWithNullValues(endpoint, requestBody);
    }

    @When("I send a POST request to {string} with id {string} and the following data:")
    public void iSendAPostRequestWithInvalidId(String endpoint,String id,Map<String, String> bookData) {
        ApiTestContext apiContext = ApiTestContext.getInstance();
        response = apiContext.iSendAPostRequestWithInvalidId(endpoint,id,bookData);
    }

    @When("I send a POST request to {string} without authentication with the following data:")
    public void iSendAPostRequestToWithoutAuthenticationWithTheFollowingData(String endpoint, Map<String, String> bookData) {
        ApiTestContext apiContext = ApiTestContext.getInstance();
        response = apiContext.sendPostRequestWithoutAuth(endpoint, bookData);
    }

    @Then("the response should indicate invalid data types")
    public void theResponseShouldIndicateInvalidDataTypes() {
        String errorMessage = response.jsonPath().getString("error");
        assertTrue(errorMessage.contains("Invalid data type"));
    }

    @Then("the response should contain {string} error message")
    public void theResponseShouldContainErrorMessage(String errorMessage) {
        String responseBody = response.getBody().asString();
        if (responseBody == null || responseBody.isEmpty()) {
            throw new AssertionError("Response body is empty or null");
        }
        try {
            assertEquals(errorMessage, responseBody);
        } catch (Exception e) {
            throw new AssertionError("Error message not found or invalid JSON format: " + e.getMessage());
        }
    }

    @When("I send another POST request to {string} with the same id:")
    public void iSendAnotherPostRequestToWithTheSameId(String endpoint, Map<String, String> bookData) {
        ApiTestContext apiContext = ApiTestContext.getInstance();
        response = apiContext.sendPostRequest(endpoint, bookData);
    }

    @Given("I create a book with id {int}")
    public void iCreateABookWithIdOnly(int bookId) {
        Map<String, String> bookData = new HashMap<>();
        bookData.put("id", String.valueOf(bookId));
        bookData.put("title", "Book With Same Id");
        bookData.put("author", "Author Test");

        ApiTestContext apiContext = ApiTestContext.getInstance();
        response = apiContext.sendPostRequest("/api/books", bookData);
    }

    // Scenario for Special Character Handling
    @Then("the response should correctly include special characters")
    public void theResponseShouldCorrectlyIncludeSpecialCharacters() {
        String author = response.jsonPath().getString("author");
        assertTrue(author.matches(".*[!@#$%^&*].*"));
    }
}


