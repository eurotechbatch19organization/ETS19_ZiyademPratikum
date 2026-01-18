package com.ziyadem.pages;

import com.github.javafaker.Faker;
import com.ziyadem.utilities.ConfigurationReader;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    Faker faker = new Faker();
    @FindBy(xpath = "//input[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//button[@class='woocommerce-button button woocommerce-form-login__submit']")
    private WebElement loginBtn;

    @FindBy(xpath = "//div[@class='message-container container alert-color medium-text-center']")
    private WebElement errorMessage;

    public void enterTheUserName(){
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
}
import com.ziyadem.utilities.BrowserUtils;
import com.ziyadem.utilities.ConfigurationReader;
import com.ziyadem.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

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
     * Login with credentials from configuration.properties for changing password
     */
    public void loginForChangePassword() {
        String username = ConfigurationReader.get("Benutzername");
        String password = ConfigurationReader.get("Passworth");

        BrowserUtils.waitForVisibility(usernameInput, 10);
        usernameInput.clear();
        usernameInput.sendKeys(username);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginButton.click();

        BrowserUtils.waitForPageToLoad(15);
        BrowserUtils.waitFor(3);
    }
}

