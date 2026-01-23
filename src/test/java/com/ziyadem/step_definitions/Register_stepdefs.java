package com.ziyadem.step_definitions;

import com.ziyadem.pages.LoginPage;
import com.ziyadem.pages.RegisterPage;
import com.ziyadem.utilities.BrowserUtils;
import com.ziyadem.utilities.ConfigurationReader;
import com.ziyadem.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Register_stepdefs {

    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();

    @Given("The user is on the main page")
    public void the_user_is_on_the_main_page() {
        Driver.get().get(ConfigurationReader.get("url"));
    }

    @When("The user navigates to the registration page")
    public void the_user_navigates_to_the_registration_page() {
        loginPage.clickToUserIcon();
    }

    @When("The user opens the email page")
    public void the_user_opens_the_email_page() {
        registerPage.openEmailPage();
    }

    @When("The user enters the  email address")
    public void the_user_enters_the_email_address() {
        registerPage.copyEmailAddress();
    }

    @Then("The email should be displayed in the email input field")
    public void the_email_should_be_displayed_in_the_email_input_field() {
        registerPage.enterTheEmail();
    }

    @Then("The user clicks to register button")
    public void the_user_clicks_to_register_button() {
        registerPage.clickToRegisterButton();

    }

    @Then("The user checks the inbox and refreshes the page")
    public void the_user_checks_the_inbox_and_refreshes_the_page() {
        registerPage.openInboxAndRefresh();
    }

    @Then("The user opens the registration email")
    public void the_user_opens_the_registration_email() {
        registerPage.clickToEmailBox();
    }

    @Then("The user opens the password reset link in a new tab")
    public void the_user_opens_the_password_reset_link_in_a_new_tab() {

        registerPage.ctrlClickPasswordResetLink();
    }

    @Then("The user switches to the password creation page")
    public void the_user_switches_to_the_password_creation_page() {
        registerPage.switchToNewTab();
    }

    @When("The user enters and submits a new password")
    public void the_user_enters_and_submits_a_new_password() {
        registerPage.enterNewPassword();
    }

    @Then("The user verifies password creation page is displayed")
    public void the_user_verifies_password_creation_page_is_displayed() {
        registerPage.successfulMessageDisplayed();
    }

    @Then("The user leave required fields empty")
    public void the_user_leave_required_fields_empty() {
        registerPage.emptyRegisterField();
    }

    @Then("The user verifies that the warning pop-up is displayed")
    public void the_user_verifies_that_the_warning_pop_up_is_displayed() {
        registerPage.emptyFieldValidation();
    }

    @When("The user email field enters {string}")
    public void the_user_email_field_enters(String email) {
        registerPage.wrongMailAddress(email);
    }

    @Then("The user verifies that the warning email pop-up is displayed")
    public void the_user_verifies_that_the_warning_email_pop_up_is_displayed() {
        registerPage.emptyFieldValidation();

    }

    @When("The user enters {string} into email field")
    public void the_user_enters_into_email_field(String email) {
        registerPage.wrongMailAddress(email);
    }

    @Then("The user error message should be displayed")
    public void the_user_error_message_should_be_displayed() {
        registerPage.warningMessageDisplayed();
    }
    @Then("The user enter an email address that is already registered")
    public void the_user_enter_an_email_address_that_is_already_registered() {
        registerPage.enterDuplicateEmail();
    }


}
