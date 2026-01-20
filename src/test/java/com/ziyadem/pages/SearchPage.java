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
    public List<WebElement> productTitles;

    @FindBy(css = ".search-results")
    public WebElement searchResultsContainer;

    @FindBy(css = ".search-results .product")
    public List<WebElement> resultItems;

    @FindBy(css = "h1.product-title")
    private WebElement productTitle;

    @FindBy(css = ".search-results .product .woocommerce-loop-product__title")
    private List<WebElement> resultItemTitles;

    private By predictiveItems = By.cssSelector(".autocomplete-suggestions .autocomplete-suggestion");

    @FindBy(css = ".message-container.container.medium-text-center")
    public WebElement noProductsMessage;

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

    public String getFirstResultProductName() {
        String name = resultItemTitles.get(0).getText().trim();
        System.out.println("firstProductName = " + name);
        return name;
    }

    public void clickFirstResultProduct() {
        resultItems.get(0).click();
    }

    public boolean isProductTitleDisplayed() {
        try {
            System.out.println("productTitle.getText() = " + productTitle.getText());
            return productTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getProductTitleText() {
        return productTitle.getText();
    }

    public String getSearchInputValue() {
        String searchInputValue = searchBox.getAttribute("value");
        System.out.println("searchInputValue = " + searchInputValue);
        return searchBox.getAttribute("value").trim();
    }

    public String getNoProductsMessageText() {
        return noProductsMessage.getText();
    }

    public void pressEnterOnSearchBar() {
        searchBox.sendKeys(Keys.ENTER);
    }

    public boolean isPredictiveDropdownDisplayed() {

        try {
            return BrowserUtils.waitForVisibility(predictiveResultsContainer, 5).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


    public int getPredictiveItemsCount() {

        try {
            BrowserUtils.waitForVisibility(predictiveResultsContainer, 5);
        } catch (TimeoutException e) {
            return 0;
        }
        return Driver.get().findElements(predictiveResultsContainer).size();
    }

    public String getFirstPredictiveItemText() {
        String raw = Driver.get().findElements(predictiveItems).get(0).getText().trim();
        return raw.split("\\R")[0].trim();
    }

    public void clickFirstPredictiveItem() {
        List<WebElement> items = Driver.get().findElements(predictiveItems);
        items.get(0).click();
    }

    public void waitForPredictiveDropdown() {
        BrowserUtils.waitForVisibility(predictiveResultsContainer, 5);
    }
}
