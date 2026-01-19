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

    @FindBy(xpath = "//button[@name='save_account_details']")
    private WebElement saveChangesButton;

    @FindBy(xpath = "//div[contains(@class,'woocommerce-message')]")
    private WebElement successMessage;

    /**
     * Check if user is on change password page
     */
    public boolean isOnChangePasswordPage() {
        BrowserUtils.waitForVisibility(currentPasswordField, 10);
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

    /**
     * Leave a specific password field blank
     */
    public void leaveFieldBlank(String fieldName) {
        if (fieldName.equals("Current Password")) {
            BrowserUtils.waitForVisibility(currentPasswordField, 10);
            currentPasswordField.clear();
        } else if (fieldName.equals("New Password")) {
            BrowserUtils.waitForVisibility(newPasswordField, 10);
            newPasswordField.clear();
        } else if (fieldName.equals("Confirm New Password")) {
            BrowserUtils.waitForVisibility(confirmPasswordField, 10);
            confirmPasswordField.clear();
        }
    }

    /**
     * Click Save Changes button
     */
    public void clickSaveChanges() {
        BrowserUtils.waitForClickablility(saveChangesButton, 10);
        BrowserUtils.scrollToElement(saveChangesButton);
        BrowserUtils.clickWithJS(saveChangesButton);
        BrowserUtils.waitForPageToLoad(10);
    }

    /**
     * Verify success message is displayed
     */
    public void verifySuccessMessage(String expectedMessage) {
        BrowserUtils.waitForVisibility(successMessage, 10);
        String actualMessage = successMessage.getText().trim();
        Assert.assertTrue("Success message not found. Expected: " + expectedMessage +
                        ", Actual: " + actualMessage,
                actualMessage.contains(expectedMessage));
    }
}