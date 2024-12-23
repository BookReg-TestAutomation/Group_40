package com.example.testing.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;

public class ApiTestContext {
    private String baseUrl;
    private String username;
    private String password;

    @Step("Set base URL to {0}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        RestAssured.baseURI = baseUrl;
    }

    @Step("Set basic authentication credentials")
    public void setBasicAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Step("Send GET request to {0}")
    public Response sendGetRequest(String endpoint) {
        RequestSpecification request = RestAssured.given()
                .auth()
                .basic(username, password)
                .header("Content-Type", "application/json");

        return request.get(endpoint);
    }

    @Step("Send GET request without authentication to {0}")
    public Response sendGetRequestWithoutAuth(String endpoint) {
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json");

        return request.get(endpoint);
    }

    @Step("Create a book with payload {0}")
    public Response createBook(String payload) {
        RequestSpecification request = RestAssured.given()
                .auth()
                .basic(username, password)
                .header("Content-Type", "application/json")
                .body(payload);

        return request.post("/api/books");
    }
}