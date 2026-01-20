package com.ziyadem.pages;

import com.github.javafaker.Faker;
import com.ziyadem.utilities.BrowserUtils;
import com.ziyadem.utilities.ConfigurationReader;
import com.ziyadem.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class LoginPage extends BasePage {

    Faker faker = new Faker();
    @FindBy(xpath = "//input[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//button[@class='woocommerce-button button woocommerce-form-login__submit']")
    private WebElement loginBtn;

    @FindBy(xpath = "//div[@class='message-container container alert-color medium-text-center']")
    private WebElement errorMessage;


    public void enterTheUserName() {
        username.sendKeys(ConfigurationReader.get("email"));
    }

    public void enterThePassword(){
        password.sendKeys(ConfigurationReader.get("password"));
    }

    public void clickToLoginButton(){
        loginBtn.click();
    }

    /**
     * Bu method fake bir mail adresi yazıp hata mesajı almak için.
     */
    public void wrongTheUsername(){
        username.sendKeys(faker.internet().emailAddress());
    }

    /**
     * Bu method fake bir password adresi yazıp hata mesajı almak için.
     */
    public void wrongThePassword(){
        password.sendKeys(faker.internet().password());
    }

    public void errorMessageDisplayed(){
        Assert.assertTrue(errorMessage.isDisplayed());
    }
    public void emptyEmail(){
        username.click();
    }
    public void emptyPassword(){
        password.click();
    }

    /**
     * Testlerini GUEST ve LOGGED_IN ayrimi ile yapmak isteyenlere tek metod ile
     * LOGGED_IN olmasini saglamaktadir ve LOGGED_IN olup olmadigini kontrol etmektedir.
     */
    public void loginAsValidUser() {
        clickToUserIcon();
        enterTheUserName();
        enterThePassword();
        clickToLoginButton();
        isLoginSuccessful();
    }


    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(name = "login")
    private WebElement loginButton;

    /**
     * Navigate to login page
     */
    public void navigateToLoginPage() {
        Driver.get().get(ConfigurationReader.get("url") + "mein-konto/");
        BrowserUtils.waitForPageToLoad(10);
    }

    /**
     * Login with credentials for change password tests
     */
    public void loginForChangePassword() {
        String usernameValue = ConfigurationReader.get("Benutzername");
        String passwordValue = ConfigurationReader.get("Passworth");

        BrowserUtils.waitForVisibility(username, 10);
        username.clear();
        username.sendKeys(usernameValue);

        password.clear();
        password.sendKeys(passwordValue);

        BrowserUtils.waitForClickablility(loginBtn, 10);
        loginBtn.click();
        BrowserUtils.waitForPageToLoad(15);
    }

    /**
     * Check if login was successful
     * Simply check if current URL is not the login page
     */
    public boolean isLoginSuccessful() {
        BrowserUtils.waitForPageToLoad(10);

        String currentUrl = Driver.get().getCurrentUrl();
        String loginUrl = ConfigurationReader.get("url") + "mein-konto/";

        // If URL changes = login is successful
        return !currentUrl.equals(loginUrl);
    }
}


