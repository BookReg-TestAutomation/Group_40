package com.example.ui_testing.steps;

import com.example.ui_testing.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        loginPage.open();
        loginPage.getDriver().manage().window().maximize();
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        loginPage.enterCredentials(username, password);
    }

    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected_to_the_inventory_page() {
        Assert.assertTrue("Expected to be on inventory page",
                loginPage.getDriver().getCurrentUrl().contains("/inventory.html"));
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String expectedError) {
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals("Error message doesn't match",
                expectedError, actualError);
    }
}