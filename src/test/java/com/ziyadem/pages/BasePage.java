package com.ziyadem.pages;
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

    @FindBy(xpath = "//a[@title='Anmelden']")
    private WebElement userIcon;

    /**
     * Bu method web sayfasında ki kullanıcı ikonuna tıklar.
     */
    public void clickToUserIcon(){
        userIcon.click();
    }
}









