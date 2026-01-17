package com.ziyadem.step_definitions;

import com.ziyadem.pages.AccountPage;
import com.ziyadem.pages.ChangePasswordPage;
import com.ziyadem.pages.LoginPage;
import com.ziyadem.utilities.BrowserUtils;
import com.ziyadem.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ChangePassword_stepdefs {

    LoginPage loginPage = new LoginPage();
    AccountPage accountPage = new AccountPage();
    ChangePasswordPage changePasswordPage = new ChangePasswordPage();

    @Given("user opens browser")
    public void user_opens_browser() {
        System.out.println("✓ Browser opened: " + Driver.get().getClass().getSimpleName());
    }

    @Given("user navigates to {string}")
    public void user_navigates_to(String string) {
        loginPage.navigateToLoginPage();
        System.out.println("✓ Navigated to login page");
    }

    @When("user logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        loginPage.loginForChangePassword();
        BrowserUtils.waitFor(2);
        System.out.println("✓ User logged in successfully");
    }

    @When("user clicks account details page")
    public void user_clicks_account_details_page() {
        accountPage.clickAccountDetailsPage();
        BrowserUtils.waitFor(1);
        System.out.println("✓ Clicked on Account Details page");
    }

    @Then("user is on the change password page")
    public void user_is_on_the_change_password_page() {
        Assert.assertTrue("User is not on change password page",
                changePasswordPage.isOnChangePasswordPage());
        System.out.println("✓ User is on Change Password page");
    }

    @Then("user should see Current Password field")
    public void user_should_see_current_password_field() {
        changePasswordPage.verifyCurrentPasswordFieldVisible();
        System.out.println("✓ Current Password field is visible");
    }

    @Then("user should see New Password field")
    public void user_should_see_new_password_field() {
        changePasswordPage.verifyNewPasswordFieldVisible();
        System.out.println("✓ New Password field is visible");
    }

    @Then("user should see Confirm New Password field")
    public void user_should_see_confirm_new_password_field() {
        changePasswordPage.verifyConfirmPasswordFieldVisible();
        System.out.println("✓ Confirm New Password field is visible");

    }


}


