package com.example.testing.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends PageObject {

     @FindBy(css = ".shopping_cart_badge")
    private WebElementFacade cartBadge;

    public void addItemToCart(String itemName) {
        findBy("//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']")
                .waitUntilVisible()
                .click();
    }

    public void removeItemFromCart(String itemName) {
        findBy("//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//button[text()='Remove']")
                .waitUntilVisible()
                .click();
        waitABit(500);
    }

    public String getCartBadgeCount() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(cartBadge),
                    ExpectedConditions.invisibilityOf(cartBadge)
            ));

            return cartBadge.isPresent() ? cartBadge.getText() : "0";
        } catch (Exception e) {
            return "0";
        }
    }
}
