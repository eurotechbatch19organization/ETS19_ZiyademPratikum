package com.ziyadem.step_definitions;

import com.ziyadem.pages.LoginPage;
import com.ziyadem.pages.ProductPage;
import com.ziyadem.pages.ShoppingCartPage;
import com.ziyadem.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShoppingCart_stepdefs {

    LoginPage loginPage = new LoginPage();
    ProductPage productPage= new ProductPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

    @When("The user clicks the shopping cart icon")
    public void the_user_clicks_the_shopping_cart_icon() {
        loginPage.clickShoppingCart();
    }
    @Then("The user verifies that shopping page is open")
    public void the_user_verifies_that_shopping_page_is_open() {
        productPage.verifyShoppingCartUrl();
    }
    @When("The user clicks the add to cart icon")
    public void the_user_clicks_the_add_to_cart_icon() {
       productPage.clickAddToCart();
    }
    @Then("The user verifies the product {string} is in the shopping cart")
    public void the_user_verifies_the_product_is_in_the_shopping_cart(String productName) {
        productPage.verifyProductIsInCart(productName);
    }
    @When("The user increases the product quantity by clicking the + button")
    public void the_user_increases_the_product_quantity_by_clicking_the_button() {
           productPage.clickPlusAndValidate();
    }
    @When("The user views the shopping cart")
    public void the_user_views_the_shopping_cart() {
        productPage.clickViewShoppingCartBtn();
    }
    @When("The user decreases the product quantity by clicking the – button")
    public void the_user_decreases_the_product_quantity_by_clicking_the_button() {
        productPage.clickMinusAndValidate();
    }
    @Then("The user clicks the update shopping cart button")
    public void the_user_clicks_the_update_shopping_cart_button() {
        productPage.clickUpdateCart();
    }

    @Then("The user hovers over the cart icon and verifies that the cart contents are displayed quickly")
    public void the_user_hovers_over_the_cart_icon_and_verifies_that_the_cart_contents_are_displayed_quickly() {
        loginPage.hoverShoppingCart2();
    }
    @Then("The user enters a quantity of {int} in the product quantity field")
    public void the_user_enters_a_quantity_of_in_the_product_quantity_field(int quantity) {
        productPage.setUpdateCart(quantity);
        productPage.urunMiktariniKaydet();
    }
    @When("The user increases the product quantity by clicking the + button in the shopping cart page")
    public void the_user_increases_the_product_quantity_by_clicking_the_button_in_the_shopping_cart_page() {
         productPage.clickPlusButton();
         productPage.urunMiktariniKaydet();
    }
    @When("The quantity on the product page should be the same as the quantity in the cart")
    public void the_quantity_on_the_product_page_should_be_the_same_as_the_quantity_in_the_cart() {
       shoppingCartPage.urunMiktarlariEsitMi(productPage.getUrunSayfasiMiktari());
    }
    @Then("The user removes the added product from the shopping cart")
    public void the_user_removes_the_added_product_from_the_shopping_cart() {
       shoppingCartPage.clickRemoveBtn();
       shoppingCartPage.verifyRemoveMessageContains("entfernt");
    }

}
