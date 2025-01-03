package com.example.testing.utils;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class ApiTestContext {

    private static ApiTestContext instance;

    private String baseUrl;
    @Getter
    private String username;
    @Getter
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

    @Step("I send a DELETE request to {string} with it's id")
    public Response sendDeleteRequestWithAuth(String endpoint,String id) {
        ApiTestContext context = ApiTestContext.getInstance();
        return given()
                .auth().basic(username, password)  // Basic Authentication
                .contentType("application/json")
                .when()
                .delete(endpoint+id);
    @Step("Get book by ID {0}")
    public Response getBookById(String id) {
        return RestAssured.given()
                .auth()
                .basic(username, password)
                .header("Content-Type", "application/json")
                .get("/api/books/" + id);
    }
    @Step("Send PUT request to {0} with request body {1}")
    public Response sendPutRequest(String id, Map<String, String> requestData) {
        return RestAssured.given()
                .auth().basic(username, password)  // Basic Authentication
                .contentType("application/json")
                .body(requestData)
                .when()
                .put("/api/books/" + id);
    }
    @Step("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode,Response response) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

}