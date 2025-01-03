package com.example.testing.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends PageObject {

    @FindBy(id = "first-name")
    private WebElementFacade firstNameField;

    @FindBy(id = "last-name")
    private WebElementFacade lastNameField;

    @FindBy(id = "postal-code")
    private WebElementFacade zipCodeField;

    @FindBy(id = "continue")
    private WebElementFacade submitButton;

    // Function to enter the first name
    public void enterFields(String firstName, String lastName, String zipCode) {
        firstNameField.waitUntilVisible().type(firstName);
        lastNameField.waitUntilVisible().type(lastName);
        zipCodeField.waitUntilVisible().type(zipCode);
    }

    public void openCheckoutPage() {
        getDriver().get("https://www.saucedemo.com/checkout-step-one.html");
    }

    // Function to enter the last name
    public void enterLastName(String lastName) {
        lastNameField.type(lastName);  // Set the LastName field value
    }

    // Function to enter the zip or postal code
    public void enterZipPostalCode(String zipCode) {
        zipCodeField.type(zipCode);  // Set the Zip/Postal Code field value
    }

    // Function to submit form
    public void submitForm() {
        submitButton.click();  // Click the submit button
    }

    // Function to get the value of the FirstName field
    public String getFirstName() {
        return firstNameField.getValue();  // Return the value of the FirstName field
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
}
