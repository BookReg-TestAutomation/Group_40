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

    @FindBy(css = ".inventory_item")
    private List<WebElementFacade> itemsList;

    @FindBy(css = ".product_sort_container")
    private WebElementFacade sortingDropdown;

    @FindBy(css = ".shopping_cart_badge")
    private WebElementFacade cartBadge;

    @FindBy(css = ".inventory_item_price")
    private List<WebElementFacade> itemPrices;

    public boolean isItemsListDisplayed() {
        return !itemsList.isEmpty() && itemsList.get(0).isVisible();
    }

    public void selectSortingOption(String option) {
        element(sortingDropdown).waitUntilVisible().selectByVisibleText(option);
    }

    public boolean areItemsSortedByPriceAscending() {
        List<Double> prices = itemPrices.stream()
                .map(element -> Double.parseDouble(element.getText().replace("$", "")))
                .collect(Collectors.toList());

        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

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
