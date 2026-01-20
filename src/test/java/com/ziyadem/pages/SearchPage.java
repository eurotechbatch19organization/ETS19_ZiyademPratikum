package com.ziyadem.pages;

import com.ziyadem.utilities.BrowserUtils;
import com.ziyadem.utilities.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//input[@id='woocommerce-product-search-field-0']")
    private WebElement searchBox;

    @FindBy(xpath = "(//i[@class='icon-search'])[1]")
    private WebElement searchButton;

    @FindBy(css = ".products .product .woocommerce-loop-product__title")
    private List<WebElement> productTitles;

    @FindBy(css = ".search-results")
    private WebElement searchResultsContainer;

    @FindBy(css = ".search-results .product")
    private List<WebElement> resultItems;

    @FindBy(css = "h1.product-title")
    private WebElement productTitle;

    @FindBy(css = ".search-results .product .woocommerce-loop-product__title")
    private List<WebElement> resultItemTitles;

    @FindBy(css = ".message-container.container.medium-text-center")
    private WebElement noProductsMessage;

    private By predictiveItems = By.cssSelector(".autocomplete-suggestions .autocomplete-suggestion");

    private By predictiveResultsContainer = By.cssSelector(".autocomplete-suggestions");

    /**
     * Bu method web sayfasında ki 'Search Box'a tıklar.
     */
    public void clickToSearchBox() {
        searchBox.click();
    }

    /**
     * Bu method web sayfasında ki SearchBox'taki 'Search Button'a tıklar.
     */
    public void clickToSearchButton() {
        BrowserUtils.waitFor(5);
        searchButton.click();
    }

    /**
     * Bu method 'Search Box'a girilmis bir metin varsa siler ve
     * girilen texti arama metni olarak yazar.
     */
    public void clearAndTypeSearch(String text) {
        clickToSearchBox();
        searchBox.clear();
        searchBox.sendKeys(text);
    }

    /**
     * Bu method 'Search Box'a girilmis bir ürün tiklandikdat sonra
     * girilen ürün ile ilgili sayfanin acilip acilmadigini kontrol eder.
     */
    public boolean isSearchResultsDisplayed() {
        return searchResultsContainer.isDisplayed();
    }

    public int getResultItemsCount() {
        BrowserUtils.waitFor(5);
        return resultItems.size();
    }

    /**
     * public boolean anyTitleContains(String lastSearchedTerm) {
     * for (WebElement e : productTitles) {
     * String title = e.getText().toLowerCase();
     * if (title.contains(lastSearchedTerm.toLowerCase())) {
     * return true;}
     * }
     * return false;
     * }
     * Buradaki map () bir dönüstürme metodu, webelementi --> String e dönüstürüyor
     * stream () ise bir koleksiyonu (List) alir ve üzerinde zincirleme işlemler yapmamizi sağlar.
     * Özetle bu metod productTitle listini dolasarak ürünlerin isimlerini search te arattigimiz
     * kelimeyi icerip icermedigini bulur. Bulursa aninda durur ve True gönderir. Yoksa False gönderir.
     */
    public boolean anyTitleContains(String lastSearchedTerm) {
        BrowserUtils.waitFor(3);
        return productTitles.stream()
                .map(e -> e.getText().toLowerCase())
                .anyMatch(t -> t.contains(lastSearchedTerm.toLowerCase()));
    }

    /**
     * Bu metod searchte aratilan ürün girildikten sonra acilan sayfada yer alan ürünlerden ilkinin
     * adini alir
     */
    public String getFirstResultProductName() {
        return resultItemTitles.get(0).getText().trim();
    }

    /**
     * Bu metod searchte aratilan ürün girildikten sonra acilan sayfada yer alan ürünlerden ilkine tiklar
     */
    public void clickFirstResultProduct() {
        resultItems.get(0).click();
    }

    /**
     * Bu metod searchte aratilan ürün girildikten sonra tiklandiginda sayfanin acilip acilmadigini teyid eder
     */
    public boolean isProductTitleDisplayed() {
        try {
            return productTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Bu metod searchte aratilan ürün girildikten sonra tiklandiginda acilan sayfada ilk ürün
     * tiklandiktan sonra ürünün basligini alir
     */
    public String getProductTitleText() {
        return productTitle.getText();
    }

    /**
     * Bu metod searchBox'a olmayan bir ürün girilip aratildiginda ekrana cikan bulunamadi mesajini alir
     */
    public String getNoProductsMessageText() {
        return noProductsMessage.getText();
    }

    /**
     * Bu metod searchBox a girilen üründen sonra Enter'a basilmasini saglar
     */
    public void pressEnterOnSearchBar() {
        searchBox.sendKeys(Keys.ENTER);
    }

    /**
     * Bu metod searchBox'ta en az 3 harflik kelime girildikten sonra acilan dropdown listesinin
     * acilip acilmadigini teyid eder
     */
    public boolean isPredictiveDropdownDisplayed() {
        try {
            return BrowserUtils.waitForVisibility(predictiveResultsContainer, 5).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * bu metod searchBox'ta acilan dropdown listesinin eleman sayisinin 0 dan büyük oldugunu döner
     */
    public int getPredictiveItemsCount() {
        try {
            BrowserUtils.waitForVisibility(predictiveResultsContainer, 5);
        } catch (TimeoutException e) {
            return 0;
        }
        return Driver.get().findElements(predictiveResultsContainer).size();
    }

    /**
     * bu metod searchBox ta acilan dropdown listesinin ilk ürünün textini alir
     */
    public String getFirstPredictiveItemText() {
        String raw = Driver.get().findElements(predictiveItems).get(0).getText().trim();
        return raw.split("\\R")[0].trim();
    }

    /**
     * bu metod searchBox ta acilan dropdown listesinin ilk ürününe tiklar
     */
    public void clickFirstPredictiveItem() {
        List<WebElement> items = Driver.get().findElements(predictiveItems);
        items.get(0).click();
    }

    /**
     * bu metod searchBox ta acilan dropdown listesi icin bekleme yapar
     */
    public void waitForPredictiveDropdown() {
        BrowserUtils.waitForVisibility(predictiveResultsContainer, 5);
    }
}
