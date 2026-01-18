package com.ziyadem.pages;

import com.ziyadem.utilities.BrowserUtils;
import com.ziyadem.utilities.ConfigurationReader;
import com.ziyadem.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage{
    @FindBy(xpath = "//a[contains(text(),'Kontodetails')]")
    private WebElement kontodetailsLink;

    /**
     * Navigate directly to Account Details page
     */

    public void clickAccountDetailsPage() {
        BrowserUtils.waitFor(3);

        String url = ConfigurationReader.get("url") + "mein-konto/edit-account/";
        Driver.get().get(url);

        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.waitFor(2);

        System.out.println("✓ Navigated to Account Details: " + url);
    }
}




