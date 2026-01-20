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

public class ShoppingPage extends BasePage{

    @FindBy(xpath = "(//a[@href='https://ziyadem.de/produkt-kategory/kuruyemis'])[1]")
    private WebElement product;

    @FindBy(xpath = "//button[@class='single_add_to_cart_button button alt']")
    private WebElement addToCart;

    @FindBy(xpath = "//input[@value='+']")
    private WebElement plusButton;

    @FindBy(xpath = "//input[@value='-']")
    private WebElement minusButton;

    @FindBy(xpath = "//a[text()='Warenkorb anzeigen']")
    private WebElement viewShoppingCartBtn;

    public void clickViewShoppingCartBtn(){
        viewShoppingCartBtn.click();
    }


    /**
     * Bu metod ürün artırma + butonuna tıklar.
     */
    public void clickPlusButton(){
        plusButton.click();
    }

    /**
     * Bu metod ürün eksiltme - butonuna tıklar.
     */
    public void clickMinusButton(){
        minusButton.click();
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
     * Bu method geçici sadece kuruyemis menusune tıklar.
     */

    public void clickToAnyProduct(){
        product.click();
    }

    /**
     * Bu method verilen productName e sahip urune tiklar
     *
     * @param productName
     */
    public void clickToProduct(String productName) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(30));

        // Önce ürünlerin gelmesini bekle
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("p.product-title a")
        ));

        List<WebElement> products =
                Driver.get().findElements(By.cssSelector("p.product-title a"));

        for (WebElement product : products) {
            String text = product.getText().trim();

            if (text.contains(productName)) {
                ((JavascriptExecutor) Driver.get())
                        .executeScript("arguments[0].scrollIntoView({block:'center'});", product);

                wait.until(ExpectedConditions.elementToBeClickable(product));
                BrowserUtils.clickWithJS(product);
                return;
            }
        }

        throw new RuntimeException("Ürün bulunamadı: " + productName);
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

    /**
     * Bu metod ürün adedini kontrol eder.
     * @param productName
     * @param expectedQty
     */

    public void verifyProductQuantity(String productName, int expectedQty) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(20));

        // Sepette ürünlerin gelmesini bekle
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("tr.cart_item")
        ));

        List<WebElement> cartRows =
                Driver.get().findElements(By.cssSelector("tr.cart_item"));

        for (WebElement row : cartRows) {
            String name = row.findElement(By.cssSelector("td.product-name")).getText();

            if (name.contains(productName)) {
                WebElement qtyInput =
                        row.findElement(By.cssSelector("td.product-quantity input"));

                int actualQty = Integer.parseInt(qtyInput.getAttribute("value"));

                Assert.assertEquals(
                        "Ürün adedi hatalı: " + productName,
                        expectedQty,
                        actualQty
                );
                return;
            }
        }

        Assert.fail("Sepette ürün bulunamadı: " + productName);
    }

}
