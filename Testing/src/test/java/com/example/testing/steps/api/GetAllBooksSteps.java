package com.example.testing.steps.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Map;
import com.example.testing.utils.ApiTestContext;

public class GetAllBooksSteps {

    @Steps
    private ApiTestContext apiContext;

    @Given("the library system is running at {string}")
    public void theLibrarySystemIsRunningAt(String baseUrl) {
        apiContext.setBaseUrl(baseUrl);
    }

    @Given("I am authenticated as {string} with password {string}")
    public void iAmAuthenticatedAsWithPassword(String username, String password) {
        apiContext.setBasicAuth(username, password);
    }

    @Given("there are books in the system")
    public void thereAreBooksInTheSystem() {
        String bookPayload = """
            {
                "title": "Test Book",
                "author": "Test Author"
            }
            """;
        apiContext.createBook(bookPayload);
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        apiContext.sendGetRequest(endpoint);
    }

    @When("I send a GET request to {string} without authentication")
    public void iSendAGETRequestToWithoutAuthentication(String endpoint) {
        apiContext.sendGetRequestWithoutAuth(endpoint);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        Response response = apiContext.getLastResponse();
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("the response should contain a list of books")
    public void theResponseShouldContainAListOfBooks() {
        Response response = apiContext.getLastResponse();
        List<Map<String, Object>> books = response.jsonPath().getList("$");
        assertNotNull("Response should not be null", books);
        assertTrue("Response should be a list", books instanceof List);
    }

    @Then("each book should have {string}, {string}, and {string} fields")
    public void eachBookShouldHaveRequiredFields(String field1, String field2, String field3) {
        Response response = apiContext.getLastResponse();
        List<Map<String, Object>> books = response.jsonPath().getList("$");
        books.forEach(book -> {
            assertNotNull("Book should have " + field1, book.get(field1));
            assertNotNull("Book should have " + field2, book.get(field2));
            assertNotNull("Book should have " + field3, book.get(field3));
        });
    }
}