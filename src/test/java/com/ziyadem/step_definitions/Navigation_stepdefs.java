package com.ziyadem.step_definitions;

import com.ziyadem.pages.NavigationBarPage;
import com.ziyadem.utilities.ConfigurationReader;
import com.ziyadem.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Navigation_stepdefs {

    NavigationBarPage navigationBarPage = new NavigationBarPage();

    @Then("the navigation bar should be visible at the top of the page")
    public void the_navigation_bar_should_be_visible_at_the_top_of_the_page() {
        Assert.assertTrue(
                "Navigation bar is not visible!",
                navigationBarPage.getNavigationBar().isDisplayed());
    }

    @When("the user navigates to another page")
    public void the_user_navigates_to_another_page() {
        Driver.get().navigate().to(ConfigurationReader.get("url") + "/register");
    }

    @Then("the navigation bar should still be visible at the top of the page")
    public void the_navigation_bar_should_still_be_visible_at_the_top_of_the_page() {
        Assert.assertTrue(
                "Navigation bar disappeared after page navigation!",
                navigationBarPage.getNavigationBar().isDisplayed());
    }

    @Then("all main categories should be visible in the navigation bar")
    public void all_main_categories_should_be_visible_in_the_navigation_bar() {

        List<String> actualCategories = navigationBarPage.getMainCategoryNames();
        List<String> expectedCategories = navigationBarPage.getExpectedMainCategoryNames();
        Assert.assertEquals(
                "Main categories in navigation bar do not match expected list!",
                expectedCategories,
                actualCategories
        );
    }

}