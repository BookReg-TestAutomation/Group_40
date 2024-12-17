package com.example.ui_testing.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class InventoryPage extends PageObject {

    @FindBy(css = ".inventory_item")
    private List<WebElementFacade> inventoryItems;

    @FindBy(css = ".shopping_cart_badge")
    private WebElementFacade cartBadge;

    @FindBy(css = ".add-to-cart-button")
    private List<WebElementFacade> addToCartButtons;

    public boolean isDisplayed() {
        return getDriver().getCurrentUrl().contains("inventory.html");
    }

    public int getNumberOfProducts() {
        return inventoryItems.size();
    }

    public void addProductToCart(int index) {
        if (index < addToCartButtons.size()) {
            addToCartButtons.get(index).click();
        }
    }

    public String getCartItemCount() {
        return cartBadge.getText();
    }
}