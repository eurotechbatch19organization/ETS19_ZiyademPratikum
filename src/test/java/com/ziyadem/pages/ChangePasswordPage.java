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

    /**
     * Check if user is on change password page
     */
    public boolean isOnChangePasswordPage() {
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.waitFor(2);
        BrowserUtils.waitForVisibility(currentPasswordField, 20);
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
        BrowserUtils.waitForPageToLoad(15);
        BrowserUtils.waitFor(2);

        PageFactory.initElements(Driver.get(), this);
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

    /**
     * Enter current password
     */
    public void enterCurrentPassword(String password) {
        BrowserUtils.waitForVisibility(currentPasswordField, 10);
        currentPasswordField.clear();
        currentPasswordField.sendKeys(password);
    }

    /**
     * Enter new password
     */
    public void enterNewPassword(String newPassword) {
        BrowserUtils.waitForVisibility(newPasswordField, 10);
        newPasswordField.clear();
        newPasswordField.sendKeys(newPassword);
    }

    /**
     * Enter confirm password
     */
    public void enterConfirmPassword(String confirmPassword) {
        BrowserUtils.waitForVisibility(confirmPasswordField, 10);
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
    }

    /**
     * Reset password back to original
     * @param currentPassword - the password that is currently set (new password)
     * @param originalPassword - the original password to restore
     */
    public void resetPasswordToOriginal(String currentPassword, String originalPassword) {
        BrowserUtils.waitForPageToLoad(15);
        BrowserUtils.waitFor(3);
        PageFactory.initElements(Driver.get(), this);
        BrowserUtils.waitForVisibility(currentPasswordField, 20);

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
        BrowserUtils.waitForPageToLoad(15);
        BrowserUtils.waitFor(3);

        BrowserUtils.waitForVisibility(currentPasswordField, 15);
        Assert.assertTrue("User session is not active",
                currentPasswordField.isDisplayed());

        String currentUrl = Driver.get().getCurrentUrl();
        Assert.assertTrue("User is not on account details page",
                currentUrl.contains("edit-account"));

        System.out.println("✓ User session is active");
        System.out.println("  - Still on change password page");
        System.out.println("  - Not redirected to login");
    }
}