package com.ziyadem.pages;


import com.ziyadem.utilities.BrowserUtils;
import com.ziyadem.utilities.ConfigurationReader;
import com.ziyadem.utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
     * Bu metod Shopping Cart ikonuna hower yapar.
     */
    public void hoverShoppingCart(){
        actions.moveToElement(shoppingCart).perform();
    }


}










