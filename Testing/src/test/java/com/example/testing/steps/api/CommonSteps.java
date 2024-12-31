package com.example.testing.steps.api;

import io.cucumber.java.en.Given;
import com.example.testing.utils.ApiTestContext;

public class CommonSteps {

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

}
