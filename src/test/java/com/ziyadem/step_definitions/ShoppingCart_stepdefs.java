package com.ziyadem.step_definitions;

import com.ziyadem.pages.LoginPage;
import com.ziyadem.pages.ShoppingPage;
import com.ziyadem.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShoppingCart_stepdefs {

    LoginPage loginPage = new LoginPage();
    ShoppingPage shoppingPage= new ShoppingPage();

    @When("The user clicks the shopping cart icon")
    public void the_user_clicks_the_shopping_cart_icon() {
        loginPage.clickShoppingCart();
    }
    @Then("The user verifies that shopping page is open")
    public void the_user_verifies_that_shopping_page_is_open() {

        shoppingPage.verifyShoppingCartUrl();
    }
    @When("The user clicks {string} product")
    public void the_user_clicks_product(String productName) {
        shoppingPage.clickToAnyProduct();
        shoppingPage.clickToProduct(productName);

    }
    @When("The user clicks the add to cart icon")
    public void the_user_clicks_the_add_to_cart_icon() {
        shoppingPage.clickAddToCart();

    }
    @Then("The user verifies the product {string} is in the shopping cart")
    public void the_user_verifies_the_product_is_in_the_shopping_cart(String productName) {
        loginPage.clickShoppingCart();
        shoppingPage.verifyProductIsInCart(productName);

    }
    @When("The user clicks any product")
    public void the_user_clicks_any_product() {
        shoppingPage.clickToAnyProduct();
    }
    @When("The user increases the product quantity by clicking the + button")
    public void the_user_increases_the_product_quantity_by_clicking_the_button() {
           shoppingPage.clickPlusAndValidate();

    }
    @When("The user views the shopping cart")
    public void the_user_views_the_shopping_cart() {
        shoppingPage.clickViewShoppingCartBtn();
        BrowserUtils.waitFor(2);
    }

    @When("The user decreases the product quantity by clicking the – button")
    public void the_user_decreases_the_product_quantity_by_clicking_the_button() {
        BrowserUtils.waitFor(2);
        shoppingPage.clickMinusAndValidate();
    }

    @Then("The user clicks the update shopping cart button")
    public void the_user_clicks_the_update_shopping_cart_button() {
        shoppingPage.clickUpdateCart();
    }

    @Then("The user hovers over the cart icon and verifies that the cart contents are displayed quickly")
    public void the_user_hovers_over_the_cart_icon_and_verifies_that_the_cart_contents_are_displayed_quickly() {
        loginPage.hoverShoppingCart();
    }

    @Then("The user enters a quantity of {int} in the product quantity field")
    public void the_user_enters_a_quantity_of_in_the_product_quantity_field(int quantity) {
        shoppingPage.setUpdateCart(quantity);
    }





}
