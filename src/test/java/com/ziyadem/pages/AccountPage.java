package com.ziyadem.pages;

import com.ziyadem.utilities.BrowserUtils;
import com.ziyadem.utilities.ConfigurationReader;
import com.ziyadem.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AccountPage extends BasePage {

    /**
     * Navigate directly to Account Details page
     */
    public void clickAccountDetailsPage() {
        String url = ConfigurationReader.get("url") + "mein-konto/edit-account/";
        Driver.get().get(url);
        BrowserUtils.waitForPageToLoad(10);
        System.out.println("✓ Navigated to Account Details: " + url);
    }

    /**
     * Logout from the system
     */
    public void logout() {
        // Navigate to main account page
        String accountUrl = ConfigurationReader.get("url") + "mein-konto/";
        Driver.get().get(accountUrl);
        BrowserUtils.waitForPageToLoad(10);

        // Get logout link href and navigate
        WebElement abmeldenLink = Driver.get().findElement(By.partialLinkText("Abmelden"));
        BrowserUtils.waitForVisibility(abmeldenLink, 10);

        String logoutHref = abmeldenLink.getAttribute("href");
        Driver.get().get(logoutHref);
        BrowserUtils.waitForPageToLoad(10);
    }
}


