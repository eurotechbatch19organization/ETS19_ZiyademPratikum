package com.ziyadem.pages;

import com.ziyadem.utilities.BrowserUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.DriverManager;
import java.time.Duration;


import static com.ziyadem.utilities.Driver.driver;

public class ShoppingCartPage extends BasePage{

    private static final int DEFAULT_TIMEOUT = 10;

    By cartQuantityInput = By.cssSelector("input.qty");

    @FindBy(xpath = "//a[@class='remove']")
    private WebElement removeBtn;

    @FindBy(xpath = "//div[contains(@class,'woocommerce-message')]")
    private WebElement removeSuccessMessage;

    /**
     * Bu metod remove işleminden sonra çıkan mesaj ekranda görünene kadar bekle
     * ve içeriğinin doğru olduğunu doğrular.
     * @param expectedText
     */
    public void verifyRemoveMessageContains(String expectedText) {
        WebElement message = BrowserUtils.waitForVisibility(removeSuccessMessage, DEFAULT_TIMEOUT);

        Assert.assertTrue(
                "Remove message does not contain expected text",
                message.getText().contains(expectedText)
        );
    }
    /**
     * Bu metod sepetteki ürünü siler.
     */
    public void clickRemoveBtn(){
        removeBtn.click();
    }
    /**
     * Bu metod sepetteki ürün adedini getirir.
     * @return
     */
    private int getSepetMiktari() {
        WebElement input = driver.findElement(cartQuantityInput);
        return Integer.parseInt(input.getAttribute("value"));
    }
    /**
     * Bu metod urun miktarları esit mi kontrol eder.
     * @param urunSayfasiMiktari
     */
    public void urunMiktarlariEsitMi(int urunSayfasiMiktari) {
        int sepetMiktari = getSepetMiktari();

        Assert.assertEquals(
                "Ürün sayfasındaki miktar ile sepetteki miktar eşit değil!",
                urunSayfasiMiktari,
                sepetMiktari
        );
    }


}
