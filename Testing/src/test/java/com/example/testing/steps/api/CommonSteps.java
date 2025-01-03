package com.example.testing.steps.api;

import io.cucumber.java.en.Given;
import com.example.testing.utils.ApiTestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertEquals;

public class CommonSteps {

    private static Response response;
    private static ApiTestContext apiContext = ApiTestContext.getInstance();
    @Given("the library system is running at {string}")
    public void theLibrarySystemIsRunningAt(String baseUrl) {
        ApiTestContext apiContext = ApiTestContext.getInstance();
        apiContext.setBaseUrl(baseUrl);
    }

    @Given("I am authenticated as {string} with password {string}")
    public void iAmAuthenticatedAsWithPassword(String username, String password) {
        ApiTestContext apiContext = ApiTestContext.getInstance();
        apiContext.setBasicAuth(username, password);
    }
    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        response = apiContext.sendGetRequest(endpoint);
    }

    @When("I send a GET request to {string} without authentication")
    public void iSendAGETRequestWithoutAuthentication(String endpoint) {
        response = apiContext.sendGetRequestWithoutAuth(endpoint);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    public static Response getResponse() {
        return response;
    }

}
