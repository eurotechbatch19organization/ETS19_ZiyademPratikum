package com.ziyadem.pages;

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

