package com.ziyadem.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.ziyadem.utilities.Driver.driver;

public class ShoppingCartPage extends BasePage{

    By cartQuantityInput = By.cssSelector("input.qty");

    private int getSepetMiktari() {
        WebElement input = driver.findElement(cartQuantityInput);
        return Integer.parseInt(input.getAttribute("value"));
    }

    public void urunMiktarlariEsitMi(int urunSayfasiMiktari) {
        int sepetMiktari = getSepetMiktari();

        Assert.assertEquals(
                "Ürün sayfasındaki miktar ile sepetteki miktar eşit değil!",
                urunSayfasiMiktari,
                sepetMiktari
        );
    }
}
