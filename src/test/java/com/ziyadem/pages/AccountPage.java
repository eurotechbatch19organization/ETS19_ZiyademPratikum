package com.ziyadem.pages;

import com.ziyadem.utilities.BrowserUtils;
import com.ziyadem.utilities.ConfigurationReader;
import com.ziyadem.utilities.Driver;
import org.openqa.selenium.By;

public class AccountPage extends BasePage {

    private static final int DEFAULT_TIMEOUT = 20;

    /**
     * Navigate directly to Account Details page
     */
    public void clickAccountDetailsPage() {
        String url = ConfigurationReader.get("url") + "mein-konto/edit-account/";
        Driver.get().get(url);

        BrowserUtils.waitForPageToLoad(DEFAULT_TIMEOUT);
        BrowserUtils.waitFor(5);
        BrowserUtils.waitForVisibility(By.id("password_current"), DEFAULT_TIMEOUT);

        System.out.println("✓ Navigated to Account Details: " + url);
    }

    /**
     * Logout from the system
     */
    public void logout() {
        String accountUrl = ConfigurationReader.get("url") + "mein-konto/";
        Driver.get().get(accountUrl);
        BrowserUtils.waitForPageToLoad(DEFAULT_TIMEOUT);

        String logoutHref = Driver.get()
                .findElement(By.partialLinkText("Abmelden"))
                .getAttribute("href");

        Driver.get().get(logoutHref);
        BrowserUtils.waitForPageToLoad(DEFAULT_TIMEOUT);
    }
}