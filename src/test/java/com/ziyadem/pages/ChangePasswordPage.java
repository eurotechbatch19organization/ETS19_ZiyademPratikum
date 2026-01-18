package com.ziyadem.pages;

import com.ziyadem.utilities.BrowserUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangePasswordPage extends BasePage {
    @FindBy(id = "password_current")
    private WebElement currentPasswordField;

    @FindBy(id = "password_1")
    private WebElement newPasswordField;

    @FindBy(id = "password_2")
    private WebElement confirmPasswordField;

    /**
     * Check if user is on change password page
     */
    public boolean isOnChangePasswordPage() {
        BrowserUtils.waitFor(2);
        return currentPasswordField.isDisplayed() &&
                newPasswordField.isDisplayed() &&
                confirmPasswordField.isDisplayed();
    }

    /**
     * Verify Current Password field is visible
     */
    public void verifyCurrentPasswordFieldVisible() {
        BrowserUtils.waitForVisibility(currentPasswordField, 10);
        Assert.assertTrue("Current Password field is not visible",
                currentPasswordField.isDisplayed());
    }

    /**
     * Verify New Password field is visible
     */
    public void verifyNewPasswordFieldVisible() {
        BrowserUtils.waitForVisibility(newPasswordField, 10);
        Assert.assertTrue("New Password field is not visible",
                newPasswordField.isDisplayed());
    }

    /**
     * Verify Confirm Password field is visible
     */
    public void verifyConfirmPasswordFieldVisible() {
        BrowserUtils.waitForVisibility(confirmPasswordField, 10);
        Assert.assertTrue("Confirm Password field is not visible",
                confirmPasswordField.isDisplayed());
    }
}

