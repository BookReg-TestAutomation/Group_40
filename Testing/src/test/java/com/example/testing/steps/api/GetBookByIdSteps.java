package com.example.testing.steps.api;

import com.example.testing.utils.ApiTestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.Map;
import java.util.HashMap;

public class GetBookByIdSteps {
    private ApiTestContext apiContext = ApiTestContext.getInstance();
    private Map<String, Response> bookResponses = new HashMap<>();

    @Given("there is a book with ID {string} in the system")
    public void thereIsABookWithIDInTheSystem(String id) {
        System.out.println("Checking if book with ID " + id + " exists in the system...");
        Response response = apiContext.getBookById(id);
        bookResponses.put(id, response);

        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody().asString());

        Assert.assertEquals("Book with ID " + id + " should exist in the system",
                200, response.getStatusCode());

        // Verify basic book structure
        Assert.assertNotNull("Book response should not be null",
                response.getBody().asString());
        Assert.assertEquals("Book ID in response should match requested ID",
                id, response.jsonPath().getString("id"));
    }

    @Then("the response should contain the book details")
    public void theResponseShouldContainTheBookDetails() {
        Response response = CommonSteps.getResponse();
        System.out.println("Validating the response body contains book details...");
        System.out.println("Response body: " + response.getBody().asString());

        // Enhanced validation of book details
        String responseBody = response.getBody().asString();
        Assert.assertNotNull("Response body should not be null", responseBody);
        Assert.assertTrue("Response body should not be empty",
                responseBody.length() > 0);

        // Validate JSON structure
        Assert.assertTrue("Response should be valid JSON",
                isValidJson(response));
    }

    @Then("the book should have {string}, {string}, and {string} fields")
    public void theBookShouldHaveFields(String field1, String field2, String field3) {
        Response response = CommonSteps.getResponse();
        System.out.println("Validating the book response contains fields: " + field1 + ", " + field2 + ", and " + field3);
        System.out.println("Response JSON: " + response.jsonPath().prettify());

        // Enhanced field validation
        validateField(response, field1);
        validateField(response, field2);
        validateField(response, field3);

        // Additional validation for specific fields
        if (field1.equals("id") || field2.equals("id") || field3.equals("id")) {
            Assert.assertTrue("ID should be a number",
                    isNumeric(response.jsonPath().getString("id")));
        }
    }

    private void validateField(Response response, String fieldName) {
        String fieldValue = response.jsonPath().getString(fieldName);
        Assert.assertNotNull(String.format("Field '%s' should not be null", fieldName),
                fieldValue);
        Assert.assertFalse(String.format("Field '%s' should not be empty", fieldName),
                fieldValue.trim().isEmpty());
    }

    private boolean isValidJson(Response response) {
        try {
            response.jsonPath().getMap("$");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}