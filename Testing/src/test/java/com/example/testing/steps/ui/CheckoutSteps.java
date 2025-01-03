package com.example.testing.steps.ui;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import com.example.testing.pages.CheckoutPage;

public class CheckoutSteps {

    private CheckoutPage checkoutPage;

    @Given("I am on the checkout page")
    public void iAmOnTheCheckoutPage() {
        checkoutPage.openCheckoutPage();
    }

    @When("I enter {string},{string} and {string} in the FirstName,LastName and ZipCode field")
    public void iEnterFields(String firstName, String lastName, String zipCode) {
        checkoutPage.enterFields(firstName,lastName,zipCode);
    }

    @When("I submit the form")
    public void iSubmitTheForm() {
        checkoutPage.submitForm();
    }

    @Then("the FirstName field should accept {string}")
    public void theFirstNameFieldShouldAccept(String expectedFirstName) {
        String actualFirstName = checkoutPage.getFirstName();
        Assert.assertEquals("FirstName field should accept numbers", expectedFirstName, actualFirstName);
    }

    @Then("the page should not navigate to the checkout step two page")
    public void thePageShouldNotNavigateToCheckoutStepTwo() {
        String currentUrl = checkoutPage.getCurrentUrl();
        // Assert that the URL is not the checkout step two page
        Assert.assertNotEquals("The page should not navigate to the checkout step two page",
                "https://www.saucedemo.com/checkout-step-two.html", currentUrl);
    }
}