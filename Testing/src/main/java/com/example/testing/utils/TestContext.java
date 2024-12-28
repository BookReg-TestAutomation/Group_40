package com.example.testing.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;

public class TestContext {
    private static TestContext instance;

    @Getter @Setter
    private String baseUrl;

    @Getter
    private RequestSpecification requestSpec;

    @Getter @Setter
    private Response lastResponse;

    private TestContext() {
        requestSpec = RestAssured.given()
                .header("Content-Type", "application/json");
    }

    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }

    public void setAuthentication(String username, String password) {
        requestSpec = RestAssured.given()
                .auth()
                .basic(username, password)
                .header("Content-Type", "application/json");
    }

    public void reset() {
        requestSpec = RestAssured.given()
                .header("Content-Type", "application/json");
        lastResponse = null;
        baseUrl = null;
    }
}