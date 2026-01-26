package com.ziyadem.pages;

import com.ziyadem.utilities.BrowserUtils;
import com.ziyadem.utilities.ConfigurationReader;
import com.ziyadem.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(xpath = "//ul[@class='woocommerce-error message-wrapper']//div[@class='message-container container alert-color medium-text-center']")
    private WebElement errorMessage;

    private static final int DEFAULT_TIMEOUT = 15;

    /**
     * Check if user is on change password page
     */
    public boolean isOnChangePasswordPage() {
        return BrowserUtils.waitForVisibility(currentPasswordField, DEFAULT_TIMEOUT).isDisplayed() &&
                newPasswordField.isDisplayed() &&
                confirmPasswordField.isDisplayed();
    }

    /**
     * Verify Current Password field is visible
     */
    public void verifyCurrentPasswordFieldVisible() {
        Assert.assertTrue("Current Password field is not visible",
                BrowserUtils.waitForVisibility(currentPasswordField, DEFAULT_TIMEOUT).isDisplayed());
    }

    /**
     * Verify New Password field is visible
     */
    public void verifyNewPasswordFieldVisible() {
        Assert.assertTrue("New Password field is not visible",
                BrowserUtils.waitForVisibility(newPasswordField, DEFAULT_TIMEOUT).isDisplayed());
    }

    /**
     * Verify Confirm Password field is visible
     */
    public void verifyConfirmPasswordFieldVisible() {
        Assert.assertTrue("Confirm Password field is not visible",
                BrowserUtils.waitForVisibility(confirmPasswordField, DEFAULT_TIMEOUT).isDisplayed());
    }

    /**
     * Leave a specific password field blank
     */
    public void leaveFieldBlank(String fieldName) {
        WebElement field = getFieldByName(fieldName);
        BrowserUtils.waitForVisibility(field, DEFAULT_TIMEOUT).clear();
    }

    /**
     * Click Save Changes button
     */
    public void clickSaveChanges() {
        WebElement button = BrowserUtils.waitForClickablility(saveChangesButton, DEFAULT_TIMEOUT);
        BrowserUtils.scrollToElement(button);
        BrowserUtils.clickWithJS(button);
    }

    /**
     * Verify success message is displayed
     */
    public void verifySuccessMessage(String expectedMessage) {
        String actualMessage = BrowserUtils.waitForVisibility(successMessage, DEFAULT_TIMEOUT).getText().trim();
        Assert.assertTrue("Success message not found. Expected: " + expectedMessage + ", Actual: " + actualMessage,
                actualMessage.contains(expectedMessage));
    }

    /**
     * Enter current password
     */
    public void enterCurrentPassword(String password) {
        fillField(currentPasswordField, password);
    }

    /**
     * Enter new password
     */
    public void enterNewPassword(String newPassword) {
        fillField(newPasswordField, newPassword);
    }

    /**
     * Enter confirm password
     */
    public void enterConfirmPassword(String confirmPassword) {
        fillField(confirmPasswordField, confirmPassword);
    }

    /**
     * Reset password back to original
     *
     * @param currentPassword  - the password that is currently set (new password)
     * @param originalPassword - the original password to restore
     */
    public void resetPasswordToOriginal(String currentPassword, String originalPassword) {
        enterCurrentPassword(currentPassword);
        enterNewPassword(originalPassword);
        enterConfirmPassword(originalPassword);
        clickSaveChanges();
        System.out.println("✓ Password reset from '" + currentPassword + "' back to '" + originalPassword + "'");
    }

    /**
     * Verify user session is still active
     */
    public void verifyUserSessionActive() {
        String accountDetailsUrl = ConfigurationReader.get("url") + "mein-konto/edit-account/";
        Driver.get().get(accountDetailsUrl);

        BrowserUtils.waitForPageToLoad(10);

        PageFactory.initElements(Driver.get(), this);

        BrowserUtils.waitForVisibility(currentPasswordField, 10);
        Assert.assertTrue("User is not on account details page",
                Driver.get().getCurrentUrl().contains("edit-account"));

        System.out.println("✓ User session is active");
        System.out.println("  - On account details page");
        System.out.println("  - Not redirected to login page");
    }

    /**
     * Verify error message is displayed
     */
    public void verifyErrorMessage(String expectedMessage) {
        String actualMessage = BrowserUtils.waitForVisibility(errorMessage, DEFAULT_TIMEOUT).getText().trim();

        Assert.assertTrue("Error message not found. Expected: '" + expectedMessage +
                        "', Actual: '" + actualMessage + "'",
                actualMessage.contains(expectedMessage));

        System.out.println("✓ Error message verified: " + actualMessage);
    }

    // Helper methods
    private void fillField(WebElement field, String text) {
        WebElement element = BrowserUtils.waitForVisibility(field, ChangePasswordPage.DEFAULT_TIMEOUT);
        element.clear();
        element.sendKeys(text);
    }

    private WebElement getFieldByName(String fieldName) {
        switch (fieldName) {
            case "Current Password":
                return currentPasswordField;
            case "New Password":
                return newPasswordField;
            case "Confirm New Password":
                return confirmPasswordField;
            default:
                throw new IllegalArgumentException("Unknown field: " + fieldName);
        }
    }
}
