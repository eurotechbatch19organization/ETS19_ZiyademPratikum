package com.ziyadem.pages;

import com.ziyadem.utilities.BrowserUtils;
import com.ziyadem.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.ziyadem.utilities.Driver.driver;

public class ProductPage extends BasePage{

    @FindBy(xpath = "//button[@class='single_add_to_cart_button button alt']")
    private WebElement addToCart;

    @FindBy(xpath = "//input[@value='+']")
    private WebElement plusButton;

    @FindBy(xpath = "//input[@class='ux-quantity__button ux-quantity__button--minus button minus is-form']")
    private WebElement minusButton;

    @FindBy(xpath = "//a[text()='Warenkorb anzeigen']")
    private WebElement viewShoppingCartBtn;

    @FindBy(xpath = "//button[@name='update_cart']")
    private WebElement updateCart;

    @FindBy(xpath = "//input[@name='quantity']")
    private WebElement quantity;

    By quantityLocator = By.xpath("//input[@name='quantity']");

    // Ürün sayfasındaki son seçilen miktarı burada tutacağız
    private int urunSayfasiMiktari;

    By productQuantityInput = By.cssSelector("form.cart input.qty");

    // Miktar değiştirildikten sonra çağrılır
    public void urunMiktariniKaydet() {
        WebElement input = driver.findElement(productQuantityInput);
        this.urunSayfasiMiktari = Integer.parseInt(input.getAttribute("value"));
    }

    public int getUrunSayfasiMiktari() {
        return urunSayfasiMiktari;
    }

    public int getQuantity(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement quantity = wait.until(
                ExpectedConditions.presenceOfElementLocated(quantityLocator)
        );

        String text = quantity.getAttribute("value").trim();
        return Integer.parseInt(text);
    }

    /**
     * Bu metod ürün artırma + butonuna tıklar.
     */
    public void clickPlusButton(){
        plusButton.click();
    }
    /**
     * Bu metod ürün artırma + butonuna tıklar ve ürün miktarını doğrular
     */
    public void clickPlusAndValidate(){
        int before = getQuantity();
        plusButton.click();
        int after = getQuantity();
        Assert.assertEquals(after,before+1);
    }
    /**
     * Bu metod ürün eksiltme - butonuna tıklar ve ürün miktarını doğrular
     */
    public void clickMinusAndValidate() {
        int before = getQuantity();
        minusButton.click();
        int after = getQuantity();

        Assert.assertEquals(after, before - 1);
    }
    /**
     * Bu metod miktar alanını günceller
     */
    public void setUpdateCart(int quantity){
        this.quantity.clear();
        this.quantity.sendKeys(String.valueOf(quantity));
    }
    /**
     * Bu metod alışveriş sepetini güncelle butonuna tıklar.
     */
    public void clickUpdateCart(){
        updateCart.click();
    }
    /**
     * Bu metod alışveriş sepetini görüntüle butonuna tıklar.
     */
    public void clickViewShoppingCartBtn(){
        viewShoppingCartBtn.click();
    }
    /**
     * Bu metod sepete ekle butonuna tıklar.
     */
    public void clickAddToCart(){
        addToCart.click();
    }
    /**
     * Bu metod url'in alışveriş sayfasında olduğunu kontrol ediyor.
     */
    public void verifyShoppingCartUrl(){
        String expectedUrl= "https://ziyadem.de/einkaufswagen";
        String actualUrl= Driver.get().getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
    }
    /**
     * Bu metod ürün sepete eklenmiş mi kontrol eder.
     * @param productName
     */
    public void verifyProductIsInCart(String productName) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(20));

        // Sepette ürünlerin gelmesini bekle
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("td.product-name a")
        ));

        List<WebElement> cartProducts =
                Driver.get().findElements(By.cssSelector("td.product-name a"));

        for (WebElement product : cartProducts) {
            String text = product.getText().trim();

            if (text.contains(productName)) {
                // Ürün bulundu → test geçer
                return;
            }
        }
        // Buraya geldiysek ürün yok demektir → test fail
        Assert.fail("Sepette ürün bulunamadı: " + productName);
    }

}
