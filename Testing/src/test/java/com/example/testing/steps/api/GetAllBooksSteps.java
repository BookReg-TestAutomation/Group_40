package com.example.testing.steps.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Map;
import com.example.testing.utils.ApiTestContext;

public class GetAllBooksSteps {

    private CommonSteps commonSteps;
    private Response response;

//    @Given("the library system is running at {string}")
//    public void theLibrarySystemIsRunningAt(String baseUrl) {
//        apiContext.setBaseUrl(baseUrl);
//    }
//
//    @Given("I am authenticated as {string} with password {string}")
//    public void iAmAuthenticatedAsWithPassword(String username, String password) {
//        apiContext.setBasicAuth(username, password);
//    }

    @Given("there are books in the system")
    public void thereAreBooksInTheSystem() {
        String bookPayload = """
            {
                "title": "Test Book",
                "author": "Test Author"
            }
            """;
        ApiTestContext apiContext = ApiTestContext.getInstance();
        apiContext.createBook(bookPayload);
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        System.out.println("Inside the GetAllBooks step file");
        ApiTestContext apiContext = ApiTestContext.getInstance();
        response = apiContext.sendGetRequest(endpoint);
    }

    @When("I send a GET request to {string} without authentication")
    public void iSendAGETRequestToWithoutAuthentication(String endpoint) {
        ApiTestContext apiContext = ApiTestContext.getInstance();
        response = apiContext.sendGetRequestWithoutAuth(endpoint);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("the response should contain a list of books")
    public void theResponseShouldContainAListOfBooks() {
        Response response = CommonSteps.getResponse();
        // Add debug logging
        System.out.println("Get all books response: " + response.asString());

        List<Map<String, Object>> books = response.jsonPath().getList("$");
        assertNotNull("Response should not be null", books);
        assertTrue("Response should be a list", books instanceof List);
        assertFalse("Response should not be empty", books.isEmpty());
    }

    @Then("each book should have {string}, {string}, and {string} fields")
    public void eachBookShouldHaveRequiredFields(String field1, String field2, String field3) {
        Response response = CommonSteps.getResponse();
        // Add debug logging
        System.out.println("Checking fields in response: " + response.asString());

        List<Map<String, Object>> books = response.jsonPath().getList("$");
        assertFalse("Book list should not be empty", books.isEmpty());

        for (Map<String, Object> book : books) {
            System.out.println("Checking book: " + book);
            assertTrue("Book should have " + field1, book.containsKey(field1));
            assertTrue("Book should have " + field2, book.containsKey(field2));
            assertTrue("Book should have " + field3, book.containsKey(field3));

            assertNotNull("Book should have non-null " + field1, book.get(field1));
            assertNotNull("Book should have non-null " + field2, book.get(field2));
            assertNotNull("Book should have non-null " + field3, book.get(field3));
        }
    }
}