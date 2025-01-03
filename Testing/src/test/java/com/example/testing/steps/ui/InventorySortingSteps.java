package com.example.testing.steps.ui;

import com.example.testing.pages.InventoryPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import net.serenitybdd.core.steps.ScenarioActor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventorySortingSteps extends ScenarioActor {

    @Steps
    private InventoryPage inventoryPage;

    @When("I click on the sort dropdown")
    public void iClickOnSortDropdown() {
        inventoryPage.clickSortDropdown();
    }

    @When("I select {string} option")
    public void iSelectSortOption(String option) {
        inventoryPage.selectSortOption(option);
    }

    @Then("products should be sorted alphabetically in ascending order")
    public void verifyProductsSortedAlphabeticallyAscending() {
        List<String> actualNames = inventoryPage.getProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);

        Assert.assertEquals("Products are not sorted alphabetically ascending",
                expectedNames, actualNames);
    }

    @Then("products should be sorted alphabetically in descending order")
    public void verifyProductsSortedAlphabeticallyDescending() {
        List<String> actualNames = inventoryPage.getProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames, Collections.reverseOrder());

        Assert.assertEquals("Products are not sorted alphabetically descending",
                expectedNames, actualNames);
    }

    @Then("products should be sorted by price in ascending order")
    public void verifyProductsSortedByPriceAscending() {
        List<Double> actualPrices = inventoryPage.getProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        Assert.assertEquals("Products are not sorted by price ascending",
                expectedPrices, actualPrices);
    }

    @Then("products should be sorted by price in descending order")
    public void verifyProductsSortedByPriceDescending() {
        List<Double> actualPrices = inventoryPage.getProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices, Collections.reverseOrder());

        Assert.assertEquals("Products are not sorted by price descending",
                expectedPrices, actualPrices);
    }
}