package com.ziyadem.pages;

import com.ziyadem.enums.UserType;
import com.ziyadem.utilities.BrowserUtils;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage {

    LoginPage loginPage = new LoginPage();

    @FindBy(xpath = "//input[@id='woocommerce-product-search-field-0']")
    private WebElement searchBox;

    @FindBy(xpath = "(//i[@class='icon-search'])[1]")
    private WebElement searchButton;

    @FindBy(css = ".products .product .woocommerce-loop-product__title")
    public List<WebElement> productTitles;

    @FindBy(css = ".search-results")
    public WebElement searchResultsContainer;

    @FindBy(css = ".search-results .product")
    public List<WebElement> resultItems;


    //**************************************
    @FindBy(css = "input[type='search']")
    public WebElement searchInput;

    @FindBy(css = ".predictive-search")
    public WebElement predictiveDropdown;

    @FindBy(css = ".predictive-search .item")
    public List<WebElement> predictiveItems;

    @FindBy(css = ".woocommerce-info, .no-products, .woocommerce-no-products-found")
    public WebElement noProductsMessage;


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


    //****************************************************


    public String getFirstResultProductName() {
        return resultItems.get(0).getText();
    }

    public void clickFirstResultProduct() {
        resultItems.get(0).click();
    }

    public void pressEnterOnSearchBar() {
        searchInput.sendKeys(Keys.ENTER);
    }

    public boolean isPredictiveDropdownDisplayed() {
        return predictiveDropdown.isDisplayed();
    }

    public int getPredictiveItemsCount() {
        return predictiveItems.size();
    }

    public String getFirstPredictiveItemText() {
        return predictiveItems.get(0).getText();
    }

    public void clickFirstPredictiveItem() {
        predictiveItems.get(0).click();
    }

    public String getNoProductsMessageText() {
        return noProductsMessage.getText();
    }
}
