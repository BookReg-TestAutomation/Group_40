package com.example.testing.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.Map;

public class ApiTestContext {

    private static ApiTestContext instance;

    private String baseUrl;
    private String username;
    private String password;

    private ApiTestContext() { }

    public static ApiTestContext getInstance() {
        if (instance == null) {
            instance = new ApiTestContext();
        }
        return instance;
    }

    @Step("Set base URL to {0}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        RestAssured.baseURI = baseUrl;
    }

    @Step("Set basic authentication credentials")
    public void setBasicAuth(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("Username and password from ApiContext SetBasicAuth " + username + " " + password+" "+baseUrl);
    }

    @Step("Send GET request to {0}")
    public Response sendGetRequest(String endpoint) {
        System.out.println("Username and password from ApiContext sendGetReq " + username + " " + password+" "+baseUrl);
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

    @Step("Send POST request to {0} with request body {1}")
    public Response sendPostRequest(String endpoint, Map<String, String> requestData) {
        return RestAssured.given()
                .auth().basic(username, password)  // Basic Authentication
                .contentType("application/json")
                .body(requestData)
                .when()
                .post(endpoint);
    }

    @Step("Send POST request to {0} with request body {1}")
    public Response sendPostRequestWithNullValues(String endpoint, Map<String, Object> requestData) {
        return RestAssured.given()
                .auth().basic(username, password)  // Basic Authentication
                .contentType("application/json")
                .body(requestData)
                .when()
                .post(endpoint);
    }

    @Step("Send POST request to {0} with request body {1} and {2}")
    public Response iSendAPostRequestWithInvalidId(String endpoint,String id, Map<String, String> requestData) {
        Map<String, String> requestBody = new HashMap<>(requestData);
        requestBody.put("id", id);
        return RestAssured.given()
                .auth().basic(username, password)  // Basic Authentication
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(endpoint);
    }

    @Step("Send POST request to {0} with request body {1}")
    public Response sendPostRequestWithIdField(String endpoint, Map<String, String> requestData) {
        return RestAssured.given()
                .auth().basic(username, password)  // Basic Authentication
                .contentType("application/json")
                .body(requestData)
                .when()
                .post(endpoint);
    }

    // Method to send a POST request without authentication
    @Step("Send POST request to {0} without authentication with request body {1}")
    public Response sendPostRequestWithoutAuth(String endpoint, Map<String, String> requestData) {
        return RestAssured.given()
                .contentType("application/json")
                .body(requestData)
                .when()
                .post(endpoint);
    }

    @Step("Send PUT request to {0} with request body {1}")
    public Response sendPutRequest(String endpoint, Map<String,String> requestData) {
        return RestAssured.given()
                .auth().basic(username, password)  // Basic Authentication
                .contentType("application/json")
                .body(requestData)
                .when()
                .put(endpoint);
    }

    @Step("Send PUT request to {0} without authentication with request body {1}")
    public Response sendPutRequestWithoutAuth(String endpoint, Map<String,String> requestData) {
        return RestAssured.given()
                .contentType("application/json")
                .body(requestData)
                .when()
                .put(endpoint);
    }
    @Step("Authenticate with username: {0} and password: {1}")
    public void authenticate(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("Authentication set with username: " + username + " and password: " + password);
    }
}