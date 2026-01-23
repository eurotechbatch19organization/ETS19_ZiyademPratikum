package com.ziyadem.pages;

import com.ziyadem.utilities.ConfigurationReader;
import com.ziyadem.utilities.Driver;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class RegisterPage extends BasePage {

    private String mainWindow;
    private String emailWindow;
    private String email;
    private String passwordWindow;

    @FindBy(xpath = "//h3[text()='Registrieren']")
    private WebElement registerForm;
    @FindBy(xpath = "//input[@id='reg_email']")
    private WebElement registerMail;
    @FindBy(xpath = "//button[@name='register']")
    private WebElement registerBtn;
    @FindBy(css = "[class='from']")
    private WebElement mailBox;
    @FindBy(xpath = "//input[@id='password_1']")
    private WebElement resetPassword_1;
    @FindBy(xpath = "//input[@id='password_2']")
    private WebElement resetPassword_2;
    @FindBy(xpath = "//button[text()='Speichern']")
    private WebElement newPasswordBtn;
    @FindBy(xpath = "//div[@class='message-container container success-color medium-text-center']")
    private WebElement successfulMessage;

    @FindBy(css = "[class='message-container container alert-color medium-text-center']")
    private WebElement warningMessage;


    /**
     * Bu method sayfamız olan Ziyademden
     * mail adresini alacağımız diğer bir sayfaya geçiş yapıyor
     */
    public void openEmailPage() {
        mainWindow = Driver.get().getWindowHandle();
        Driver.get().switchTo().newWindow(WindowType.TAB);
        emailWindow = Driver.get().getWindowHandle();
        Driver.get().get("https://www.minuteinbox.com/");
    }

    /**
     * Bu method mail adresinin bulunduğu sayfaya gidiyor
     * locator bulunana kadar bekliyor ve email adresini alıyor.
     */
    public void copyEmailAddress() {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));

        WebElement emailElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email"))
        );
        email = emailElement.getText();
        System.out.println("email = " + email);
    }

    /**
     * Açılan yeni sayfadan alınan email adresi ana sayfada bulunan register
     * alanına yazıldı.
     */
    public void enterTheEmail() {
        Driver.get().switchTo().window(mainWindow);
        registerMail.sendKeys(email);
    }
    public void clickToRegisterButton() {
        registerBtn.click();
    }
    public void openInboxAndRefresh() {
        Driver.get().switchTo().window(emailWindow);
        Driver.get().navigate().refresh();
    }
    public void clickToEmailBox() {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(15));

        WebElement mail = wait.until(
                ExpectedConditions.elementToBeClickable(mailBox)
        );
        mail.click();
    }

    /**
     * Burada yaptığımız locator iframe içinde ve href linki var
     * iframe içine giriyor linke tıklıyor ve bu linki ekstra bir tab içinde çalıştırmamıza
     * gerek kalmıyor.
     */
    public void ctrlClickPasswordResetLink() {
        Driver.get().switchTo().frame(0);

        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(15));

        WebElement link = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.linkText("Set your new password.")));

        String href = link.getAttribute("href");

        // iframe'den çık
        Driver.get().switchTo().defaultContent();

        // direkt linke git
        Driver.get().get(href);
    }
    public void switchToNewTab() {
        Set<String> windows = Driver.get().getWindowHandles();

        for (String window : windows) {
            if (!window.equals(mainWindow) && !window.equals(emailWindow)) {
                passwordWindow = window;
                Driver.get().switchTo().window(window);
                break;
            }
        }
    }
    public void enterNewPassword() {
        resetPassword_1.sendKeys("Irem.2113");
        resetPassword_2.sendKeys("Irem.2113");
        newPasswordBtn.click();
    }
    public void successfulMessageDisplayed() {
        Assert.assertTrue(successfulMessage.isDisplayed());
    }
    public void emptyRegisterField(){
        registerMail.click();
    }

    /**
     * Burada yaptığımız yanlış veya eksik bilgi girdiğimiz alanda çıkan uyarı pop-up ı
     * doğrulamak. Burada ki pop-up ın textini inspect edemediğimiz için getAttribute ile
     * pop-up çıktığını doğruluyoruz.
     */
    public void emptyFieldValidation(){
        String validationMessage = registerMail.getAttribute("validationMessage");
        Assert.assertFalse(validationMessage.isEmpty());
        System.out.println("validationMessage = " + validationMessage);
    }
    public void wrongMailAddress(String email){
        registerMail.sendKeys(email);
    }
    public void warningMessageDisplayed(){
        Assert.assertTrue(warningMessage.isDisplayed());
    }
    public void enterDuplicateEmail(){
        registerMail.sendKeys(ConfigurationReader.get("email"));
    }

}
