package com.example.testing.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends PageObject {

    @FindBy(css = ".cart_item")
    private List<WebElementFacade> cartItems;

    @FindBy(css = ".title")
    private WebElementFacade cartPageTitle;

    public void openCartPage() {
        getDriver().get("https://www.saucedemo.com/cart.html");
    }

    public boolean isCartPageDisplayed() {
        return cartPageTitle.isVisible() && cartPageTitle.getText().equalsIgnoreCase("Your Cart");
    }

    public boolean isItemInCart(String itemName) {
        return cartItems.stream()
                .anyMatch(item -> item.findBy(".inventory_item_name").getText().equals(itemName));
    }

    public void removeItemFromCart(String itemName) {
        // Preprocess the item name to generate the button id
        String itemId = "remove-" + itemName.toLowerCase().replace(" ", "-");

        findBy("//button[@id='" + itemId + "']")
                .waitUntilClickable()
                .click();
    }
}
