package com.example.testing.steps.ui;

import com.example.testing.pages.InventoryPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import net.serenitybdd.core.steps.ScenarioActor;

public class InventorySteps extends ScenarioActor {

    @Steps
    private InventoryPage inventoryPage;

    @When("I add the item {string} to the cart")
    public void iAddTheItemToTheCart(String itemName) {
        inventoryPage.addItemToCart(itemName);
    }

    @Then("the cart badge should display {string}")
    public void theCartBadgeShouldDisplay(String expectedCount) {
        Assert.assertEquals("Cart badge count should match",
                expectedCount, inventoryPage.getCartBadgeCount());
    }

    @When("I remove the item {string} from the cart")
    public void iRemoveTheItemFromTheCart(String itemName) {
        inventoryPage.removeItemFromCart(itemName);
    }
}