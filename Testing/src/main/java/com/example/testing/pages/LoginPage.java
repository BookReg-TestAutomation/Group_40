package com.example.testing.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.annotations.findby.FindBy;

@DefaultUrl("https://www.saucedemo.com")
public class LoginPage extends PageObject {

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public void enterCredentials(String username, String password) {
        element(usernameField).waitUntilVisible().clear();
        element(usernameField).sendKeys(username);
        element(passwordField).clear();
        element(passwordField).sendKeys(password);
        element(loginButton).click();
    }

    public String getErrorMessage() {
        element(errorMessage).waitUntilVisible();
        return element(errorMessage).getText();
    }
}
