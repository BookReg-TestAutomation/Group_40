package com.example.ui_testing.steps;

import com.example.ui_testing.pages.InventoryPage;
import com.example.ui_testing.pages.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import net.serenitybdd.core.steps.ScenarioActor;
import net.thucydides.core.annotations.Step;
import java.util.List;

public class InventorySteps extends ScenarioActor {

    @Steps
    private InventoryPage inventoryPage;

    @Then("I should see a list of items displayed")
    public void iShouldSeeAListOfItemsDisplayed() {
        Assert.assertTrue("Expected to see items displayed",
                inventoryPage.isItemsListDisplayed());
    }

    @When("I select sorting option {string}")
    public void iSelectSortingOption(String sortingOption) {
        inventoryPage.selectSortingOption(sortingOption);
    }

    @Then("the items should be sorted in ascending order of price")
    public void theItemsShouldBeSortedInAscendingOrderOfPrice() {
        Assert.assertTrue("Items should be sorted by price ascending",
                inventoryPage.areItemsSortedByPriceAscending());
    }

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
