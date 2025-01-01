package com.example.testing.steps.ui;

import com.example.testing.pages.CartPage;
import com.example.testing.pages.InventoryPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

public class CartSteps {

    @Steps
    private CartPage cartPage;

    @Steps
    private InventoryPage inventoryPage;

    @When("I navigate to the cart page")
    public void iNavigateToTheCartPage() {
        cartPage.openCartPage();
    }

    @Then("I should see the cart page")
    public void iShouldSeeTheCartPage() {
        Assert.assertTrue("Cart page should be displayed", cartPage.isCartPageDisplayed());
    }

    @Then("I should see {string} in the cart")
    public void iShouldSeeItemInTheCart(String itemName) {
        Assert.assertTrue("Item should be visible in the cart", cartPage.isItemInCart(itemName));
    }

    @Then("I should not see {string} in the cart")
    public void iShouldNotSeeItemInTheCart(String itemName) {
        Assert.assertFalse("Item should not be visible in the cart", cartPage.isItemInCart(itemName));
    }

    @When("I remove the item {string} from the cart on the cart page")
    public void iRemoveTheItemFromTheCartOnTheCartPage(String itemName) {
        cartPage.removeItemFromCart(itemName);
    }
}
