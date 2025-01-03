package com.example.testing.steps.api;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import com.example.testing.utils.ApiTestContext;
import io.cucumber.datatable.DataTable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;
import java.util.Map;

public class UpdateBookSteps {
    private Response response;
    private final ApiTestContext apiContext = ApiTestContext.getInstance();

    @When("I send a PUT request to {string} with the following data:")
    public void iSendAPUTRequestToWithTheFollowingData(String endpoint, DataTable table) {
        Map<String, String> bookData = table.asMap(String.class, String.class);
        ApiTestContext apiContext = ApiTestContext.getInstance();
        response = apiContext.sendPutRequest(endpoint, bookData);
    }

    @Then("the error message should be {string}")
    public void theErrorMessageShouldBe(String expectedMessage) {
        String responseBody = response.getBody().asString();
        try {
            String actualMessage = response.jsonPath().getString("message");
            assertEquals(expectedMessage, actualMessage);
        } catch (Exception e) {
            assertEquals(expectedMessage, responseBody.trim());
        }
    }

    @Then("the response should indicate success with a message {string}")
    public void theResponseShouldIndicateSuccessWithMessage(String expectedMessage) {
        try {
            String actualMessage = response.jsonPath().getString("message");
            assertEquals(expectedMessage, actualMessage);
        } catch (Exception e) {
            String responseBody = response.getBody().asString();
            assertTrue(responseBody.contains(expectedMessage));
        }
    }
}