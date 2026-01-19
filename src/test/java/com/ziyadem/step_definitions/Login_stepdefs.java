package com.ziyadem.step_definitions;

import com.ziyadem.pages.BasePage;
import com.ziyadem.pages.LoginPage;
import com.ziyadem.utilities.ConfigurationReader;
import com.ziyadem.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login_stepdefs {
    LoginPage loginPage = new LoginPage();

    @Given("The user is on the main login page")
    public void the_user_is_on_the_main_login_page() {
        Driver.get().get(ConfigurationReader.get("url"));
        loginPage.clickToUserIcon();
    }

    @When("The user enters valid email")
    public void the_user_enters_valid_email() {
        loginPage.enterTheUserName();
    }

    @When("The user enters valid password")
    public void the_user_enters_valid_password() {
        loginPage.enterThePassword();
    }

    @Then("The user clicks to login button")
    public void the_user_clicks_to_login_button() {
        loginPage.clickToLoginButton();
    }

    @When("The user enters an wrong email or username")
    public void the_user_enters_an_wrong_email_or_username() {
        loginPage.wrongTheUsername();
    }

    @Then("The login error message should be displayed")
    public void the_login_error_message_should_be_displayed() {
        loginPage.errorMessageDisplayed();
    }
    @When("The user enters an wrong password")
    public void the_user_enters_an_wrong_password() {
        loginPage.wrongThePassword();
    }
    @When("The user leaves the email or username field blank")
    public void the_user_leaves_the_email_or_username_field_blank() {
        loginPage.emptyEmail();
    }
    @When("The user leaves the password field blank")
    public void the_user_leaves_the_password_field_blank() {
        loginPage.emptyPassword();
    }
}