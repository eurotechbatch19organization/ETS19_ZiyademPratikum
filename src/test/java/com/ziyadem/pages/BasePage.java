package com.ziyadem.pages;

import com.ziyadem.utilities.Driver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {
    {
        PageFactory.initElements(Driver.get(), this);
    }
    Actions actions=new Actions(Driver.get());
    @FindBy(xpath = "//a[@title='Anmelden']")
    private WebElement userIcon;

    @FindBy(xpath = "//a[@title='Warenkorb']")
    private WebElement shoppingCart;

    @FindBy(css = "li.cart-item.has-dropdown")
    private WebElement shoppingCartDropdown;

    /**
     * Bu method web sayfasında ki kullanıcı ikonuna tıklar.
     */
    public void clickToUserIcon(){
        userIcon.click();
    }

    /**
     * Bu method web sayfasında ki shopping cart ikonuna tıklar.
     */
    public void clickShoppingCart(){
        shoppingCart.click();
    }
    /**
     * Shopping Cart ikonunun üzerine hover yapar ve dropdown açıldığını doğrular.
     * @return true eğer dropdown görünürse, false değilse
     */
    public boolean hoverShoppingCart2() {
        try {
            // Hover işlemi
            actions.moveToElement(shoppingCart).perform();

            // Dropdown'ın görünür olmasını bekle
            WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(shoppingCartDropdown));

            return true; // Hover başarılı
        } catch (TimeoutException e) {
            return false; // Hover başarısız
        }
    }






}










