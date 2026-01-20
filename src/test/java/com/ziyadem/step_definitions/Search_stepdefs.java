package com.ziyadem.step_definitions;

import com.ziyadem.enums.UserType;
import com.ziyadem.pages.LoginPage;
import com.ziyadem.pages.SearchPage;
import com.ziyadem.utilities.ConfigurationReader;
import com.ziyadem.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Search_stepdefs {

    LoginPage loginPage = new LoginPage();
    SearchPage searchPage = new SearchPage();

    private String lastSearchedTerm;
    private String selectedProductNameFromResults;
    private String selectedProductNameFromDropdown;

    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {
        Driver.get().get(ConfigurationReader.get("url"));
    }

    @Given("the user is {string}")
    public void the_user_is(String userType) {
        if (UserType.valueOf(userType.toUpperCase()) == UserType.LOGGED_IN) {
            loginPage.loginAsValidUser();
        }
    }

    @When("the user enters {string} into the search bar")
    public void the_user_enters_into_the_search_bar(String term) {
        lastSearchedTerm = term;
        searchPage.clearAndTypeSearch(term);
    }

    @When("the user clicks the Search button")
    public void the_user_clicks_the_search_button() {
        searchPage.clickToSearchButton();
    }

    @Then("search results should be displayed")
    public void search_results_should_be_displayed() {
        Assert.assertTrue("Search results container is not displayed",
                searchPage.isSearchResultsDisplayed());
    }

    @Then("at least one related product should be listed")
    public void at_least_one_related_product_should_be_listed() {
        Assert.assertTrue("No products found in results",
                searchPage.getResultItemsCount() > 0);
        Assert.assertTrue(searchPage.anyTitleContains(lastSearchedTerm));
    }

    @When("the user clicks on a product from the search results")
    public void the_user_clicks_on_a_product_from_the_search_results() {
        selectedProductNameFromResults = searchPage.getFirstResultProductName();
        searchPage.clickFirstResultProduct();
    }

    @Then("the product detail page should be displayed")
    public void the_product_detail_page_should_be_displayed() {
        Assert.assertTrue("Product detail page is not displayed",
                searchPage.isProductTitleDisplayed());
    }

    @Then("the displayed product should match the selected product")
    public void the_displayed_product_should_match_the_selected_product() {
        String actualTitle = searchPage.getProductTitleText();
        Assert.assertTrue(
                "Opened product does not match selected results product\n" +
                        "Expected: " + selectedProductNameFromResults + "\n" +
                        "Actual  : " + actualTitle,
                actualTitle.toLowerCase().contains(selectedProductNameFromResults.toLowerCase()));
    }

    @Then("no products should be listed")
    public void no_products_should_be_listed() {
        Assert.assertEquals("Expected 0 products in results, but found some",
                0, searchPage.getResultItemsCount());
    }

    @Then("the {string} message should be displayed")
    public void the_message_should_be_displayed(String string) {
        String actual = searchPage.getNoProductsMessageText();
        String expectedMessage = "Es wurden keine Produkte gefunden, die deiner Auswahl entsprechen.";
        Assert.assertTrue("No-products message not displayed or does not match. Actual: " + actual,
                actual.toLowerCase().contains(expectedMessage.toLowerCase()));
    }

    @When("the user presses the Enter key")
    public void the_user_presses_the_enter_key() {
        searchPage.pressEnterOnSearchBar();
    }

    @Then("predictive search results should be displayed in the dropdown")
    public void predictive_search_results_should_be_displayed_in_the_dropdown() {
        searchPage.waitForPredictiveDropdown();

        Assert.assertTrue("Predictive dropdown is not displayed",
                searchPage.isPredictiveDropdownDisplayed());

        Assert.assertTrue("Predictive dropdown has no items",
                searchPage.getPredictiveItemsCount() > 0);
    }

    @When("the user selects a product from the dropdown")
    public void the_user_selects_a_product_from_the_dropdown() {
        selectedProductNameFromDropdown = searchPage.getFirstPredictiveItemText();
        searchPage.clickFirstPredictiveItem();
    }

    @Then("the displayed product should match the selected dropdown product")
    public void the_displayed_product_should_match_the_selected_dropdown_product() {
        String actualTitle = searchPage.getProductTitleText();

        Assert.assertTrue(
                "Opened product does not match selected dropdown product\n" +
                        "Expected: " + selectedProductNameFromDropdown + "\n" +
                        "Actual  : " + actualTitle,
                actualTitle.toLowerCase()
                        .contains(selectedProductNameFromDropdown.toLowerCase()));
    }
}