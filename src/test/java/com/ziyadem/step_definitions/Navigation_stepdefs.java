package com.ziyadem.step_definitions;

import com.ziyadem.enums.UserType;
import com.ziyadem.pages.LoginPage;
import com.ziyadem.pages.NavigationBarPage;
import com.ziyadem.utilities.ConfigurationReader;
import com.ziyadem.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Navigation_stepdefs {

    NavigationBarPage navigationBarPage = new NavigationBarPage();
    String homeUrl;

    @Then("the navigation bar should be visible")
    public void the_navigation_bar_should_be_visible() {
        Assert.assertTrue(navigationBarPage.getNavigationBar().isDisplayed());
    }

    @When("the user navigates to another page")
    public void the_user_navigates_to_another_page() {
        Driver.get().navigate().to(ConfigurationReader.get("url") + "/register");
    }

    @Then("the navigation bar should still be visible")
    public void the_navigation_bar_should_still_be_visible() {
        Assert.assertTrue(navigationBarPage.getNavigationBar().isDisplayed());
    }

    @Then("all main categories should be displayed")
    public void all_main_categories_should_be_displayed() {
        Assert.assertEquals(
                navigationBarPage.getExpectedMainCategoryNames(),
                navigationBarPage.getActualMainCategoryNames()
        );
    }

    @When("the user hovers over all categories with submenus")
    public void the_user_hovers_over_all_categories_with_submenus() {
        for (WebElement category : navigationBarPage.getCategoriesWithSubmenu()) {
            navigationBarPage.hover(category);
        }
    }

    @Then("dropdown menus should be visible")
    public void dropdown_menus_should_be_visible() {
        for (WebElement category : navigationBarPage.getCategoriesWithSubmenu()) {
            navigationBarPage.hover(category);
            WebElement dropdown = navigationBarPage.waitForDropdownToBeVisible(category);
            Assert.assertTrue(
                    "Dropdown is not visible for category: " + category.getText(),
                    dropdown.isDisplayed()
            );
        }
    }

    @When("the user clicks on each category without submenu")
    public void the_user_clicks_on_each_category_without_submenu() {
        homeUrl = navigationBarPage.getCurrentUrl();
        int size = navigationBarPage.getCategoriesWithoutSubmenu().size();
        for (int i = 0; i < size; i++) {
            List<WebElement> categories = navigationBarPage.getCategoriesWithoutSubmenu();
            WebElement category = categories.get(i);
            navigationBarPage.click(category);

            Assert.assertNotEquals(homeUrl, navigationBarPage.getCurrentUrl());
            Assert.assertFalse(navigationBarPage.getPageTitle().isBlank());
            navigationBarPage.navigateBack();
        }
    }


    @Then("the related category pages should be opened")
    public void the_related_category_pages_should_be_opened() {
        // doğrulama yukarıda yapıldı
    }

    @When("the user hovers over a category with submenu")
    public void the_user_hovers_over_a_category_with_submenu() {
        navigationBarPage.hover(
                navigationBarPage.getCategoriesWithSubmenu().get(0)
        );
    }

    @When("clicks the first subcategory link")
    public void clicks_the_first_subcategory_link() {
        navigationBarPage.openFirstSubCategorySafely();
    }

    @Then("a valid product listing or landing page should be opened")
    public void a_valid_product_listing_or_landing_page_should_be_opened() {
        String url = navigationBarPage.getCurrentUrl().toLowerCase();
        String title = navigationBarPage.getPageTitle().toLowerCase();

        Assert.assertFalse(url.contains("404"));
        Assert.assertFalse(title.contains("404"));
        Assert.assertFalse(title.isBlank());
    }

    @When("clicks any subcategory link")
    public void clicks_any_subcategory_link() {
        navigationBarPage.clickAnySubCategoryLink();
    }

    @Then("the category page should not be a wrong, empty or 404 page")
    public void the_category_page_should_not_be_a_wrong_empty_or_404_page() {
        Assert.assertTrue(navigationBarPage.isNotWrongCategoryPage());

    }
    @When("the user hovers over each main category with submenu")
    public void the_user_hovers_over_each_main_category_with_submenu() {
        //then adımında doğrulama yapılıyor
    }

    @Then("the dropdown menu should appear within 1 second")
    public void the_dropdown_menu_should_appear_within_one_second() {
        navigationBarPage.verifyDropdownsOpenWithinOneSecond();
    }





}