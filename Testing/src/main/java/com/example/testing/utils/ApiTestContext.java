// File: src/main/java/com/example/testing/utils/ApiTestContext.java
package com.example.testing.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;

public class ApiTestContext {
    private final TestContext context;

    public ApiTestContext() {
        this.context = TestContext.getInstance();
    }

    @Step("Set base URL to {0}")
    public void setBaseUrl(String baseUrl) {
        context.setBaseUrl(baseUrl);
        RestAssured.baseURI = baseUrl;
    }

    @Step("Set basic authentication credentials")
    public void setBasicAuth(String username, String password) {
        context.setAuthentication(username, password);
    }

    @Step("Send GET request to {0}")
    public Response sendGetRequest(String endpoint) {
        Response response = context.getRequestSpec().get(endpoint);
        context.setLastResponse(response);
        return response;
    }

    @Step("Send GET request without authentication to {0}")
    public Response sendGetRequestWithoutAuth(String endpoint) {
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json");
        Response response = request.get(endpoint);
        context.setLastResponse(response);
        return response;
    }

    @Step("Create a book with payload {0}")
    public Response createBook(String payload) {
        Response response = context.getRequestSpec()
                .body(payload)
                .post("/api/books");
        context.setLastResponse(response);
        return response;
    }

    public Response getLastResponse() {
        return context.getLastResponse();
    }
}