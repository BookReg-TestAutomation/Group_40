package com.example.testing.steps.api;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.testing.utils.ApiTestContext;

public class UpdateBookSteps {

    private Response response;

    @Given("I am authenticated as {string} with password {string}")
    public void iAmAuthenticatedAsWithPassword(String username, String password) {
        ApiTestContext apiContext = ApiTestContext.getInstance();
        apiContext.authenticate(username, password);
    }

    @When("I send a PUT request to {string} with the following data:")
    public void iSendAPUTRequestToWithTheFollowingData(String endpoint, DataTable table) {
        Map<String, String> bookData = table.asMap(String.class, String.class);
        ApiTestContext apiContext = ApiTestContext.getInstance();
        response = apiContext.sendPutRequest(endpoint, bookData);
    }

    @When("I send a PUT request to {string} without authentication with the following data:")
    public void iSendAPUTRequestToWithoutAuthenticationWithTheFollowingData(String endpoint, DataTable table) {
        Map<String, String> bookData = table.asMap(String.class, String.class);
        ApiTestContext apiContext = ApiTestContext.getInstance();
        response = apiContext.sendPutRequestWithoutAuth(endpoint, bookData);
    }

    @Then("the response status code is {int}")
    public void checkResponseStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("the error message should be {string}")
    public void theErrorMessageShouldBe(String expectedMessage) {
        String actualMessage = response.jsonPath().getString("message");
        assertEquals(expectedMessage, actualMessage, "Error message mismatch");
    }

    @Then("the response should indicate invalid data types")
    public void theResponseShouldIndicateInvalidDataTypes() {
        String errorMessage = response.jsonPath().getString("error");
        assertTrue(errorMessage.contains("Invalid Input Parameters"));
    }

    @Then("the response should correctly include special characters")
    public void theResponseShouldCorrectlyIncludeSpecialCharacters() {
        String author = response.jsonPath().getString("author");
        assertTrue(author.matches(".*[!@#$%^&*()].*"), "Special characters not correctly included");
    }

    @When("I send a PUT request to {string} with title null and author null")
    public void iSendAPUTRequestToWithTitleNullAndAuthorNull(String endpoint) {
        Map<String,String> requestBody = Map.of(

                "title", "",
                "author", ""
        );
        ApiTestContext apiContext = ApiTestContext.getInstance();
        response = apiContext.sendPutRequest(endpoint, requestBody);
        response.then().statusCode(200);
    }
}
