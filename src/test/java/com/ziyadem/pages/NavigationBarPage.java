package com.ziyadem.pages;

import com.ziyadem.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NavigationBarPage extends BasePage{

    @FindBy(xpath = "//div[@id='wide-nav']")
    private WebElement navigationBar;

    @FindBy(xpath = "//div[@id='wide-nav']//a[contains(@class,'nav-top-link')]")
    private List<WebElement> mainCategories;

    @FindBy(xpath = "//div[@id='wide-nav']//li[contains(@class,'menu-item-has-children')]/a")
    private List<WebElement> categoriesWithSubmenu;

    @FindBy(xpath = "//div[@id='wide-nav']//li[not(contains(@class,'menu-item-has-children'))]/a[contains(@class,'nav-top-link')]")
    private List<WebElement> categoriesWithoutSubmenu;


    /**
     * Navigasyon bar WebElement’ini döndürür
     */
    public WebElement getNavigationBar() {return navigationBar;}

    /**
     * Navigasyon bar’da bulunan tüm ana kategorileri döndürür
     */
    public List<WebElement> getMainCategories() {return mainCategories;}

    /**
     * Alt menüsü bulunan (hover edilebilen)
     * ana kategorileri döndürür
     */
    public List<WebElement> getCategoriesWithSubmenu() {return categoriesWithSubmenu;}

    /**
     * Alt menüsü OLMAYAN ana kategorileri döndürür
     */
    public List<WebElement> getCategoriesWithoutSubmenu() {return categoriesWithoutSubmenu;}

    /**
     * Ana menüde olması beklenen
     * kategori isimlerini döndürür
     */
    public List<String> getExpectedMainCategoryNames() {
        return Arrays.asList(
                "Bal Çeşitleri",
                "Şarküteri",
                "Bakım ürünleri & Takviyeler",
                "Özel Tatlar",
                "Kuruyemiş",
                "Yağlar",
                "Çay & Kahve",
                "Meyve Kuruları",
                "Baharat"
        );
    }

    /**
     * Navigasyon bar’da gerçekten görüntülenen
     * ana kategori isimlerini String listesi olarak döndürür
     *
     * Not:
     * Test kapsamı dışındaki "Angebote" filtrelenir
     */
    public List<String> getActualMainCategoryNames() {
        List<String> names = new ArrayList<>();
        for (WebElement category : mainCategories) {
            String text = category.getText().trim();
            if (!text.isEmpty() && !text.equalsIgnoreCase("Angebote")) {
                names.add(text);
            }
        }
        return names;
    }

    /**
     * Verilen element üzerine mouse hover işlemi yapar
     */
    public void hover(WebElement element) {
        new Actions(Driver.get()).moveToElement(element).perform();
    }

    /**
     * Verilen ana kategoriye ait
     * dropdown (sub menu) elementini döndürür
     */
    public WebElement getDropdown(WebElement category) {
        return category.findElement(By.xpath("following-sibling::ul"));
    }

    /**
     * Verilen WebElement’e tıklama işlemi yapar
     */
    public void click(WebElement element) {element.click();}

    /**
     * Tarayıcıda bir önceki sayfaya geri döner
     */
    public void navigateBack() {Driver.get().navigate().back();}

    /**
     * Mevcut sayfanın URL’ini döndürür
     */
    public String getCurrentUrl() {return Driver.get().getCurrentUrl();}

    /**
     * Mevcut sayfanın title bilgisini döndürür
     */
    public String getPageTitle() {return Driver.get().getTitle();}

    /**
     * Alt menüsü bulunan ilk ana kategoriye hover yapar
     * ve bu kategoriye ait ilk sub kategori linkini döndürür
     */
    public WebElement getFirstSubCategoryLink() {
        WebElement firstCategory = categoriesWithSubmenu.get(0);
        hover(firstCategory);
        return getDropdown(firstCategory).findElement(By.xpath(".//a"));
    }

    /**
     * Verilen ana kategori için dropdown menünün
     * görünür olmasını bekler ve döndürür
     */
    public WebElement waitForDropdownToBeVisible(WebElement category) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(5));
        return wait.until(
                ExpectedConditions.visibilityOf(
                        getDropdown(category))
        );
    }

    /**
     * Hover yapılan kategorinin dropdown’undaki
     * rastgele (ilk) subcategory linkini tıklar
     */
    public void clickAnySubCategoryLink() {
        WebElement category = categoriesWithSubmenu.get(0);
        hover(category);
        WebElement dropdown = waitForDropdownToBeVisible(category);
        dropdown.findElement(By.xpath(".//a")).click();
    }

    /**
     * Açılan sayfanın yanlış veya hatalı
     * (404 / boş / alakasız) olmadığını kontrol eder
     */
    public boolean isNotWrongCategoryPage() {
        String url = getCurrentUrl().toLowerCase();
        String title = getPageTitle().toLowerCase();

        return !url.contains("404")
                && !title.contains("404")
                && !title.isBlank();
    }

    /**
     * Alt menüsü olan tüm kategoriler için
     * dropdown menülerin belirtilen süre içinde
     * açıldığını doğrular
     */
    public void verifyDropdownsOpenWithinOneSecond() {
        for (WebElement category : categoriesWithSubmenu) {
            hover(category);
            WebDriverWait wait = new WebDriverWait(
                    Driver.get(),
                    Duration.ofSeconds(1));
            WebElement dropdown = wait.until(
                    ExpectedConditions.visibilityOf(getDropdown(category)));
            Assert.assertTrue(dropdown.isDisplayed());
        }
    }

    /**
     * Alt menüsü olan ilk kategoriye güvenli şekilde hover yapar
     * dropdown tamamen açılınca ilk subcategory linkine tıklar
     *
     * Bu method suite run sırasında oluşan
     * stale / not interactable problemlerini önlemek için yazılmıştır
     */
    public void openFirstSubCategorySafely() {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(5));
        Actions actions = new Actions(Driver.get());

        // kategori HER SEFERİNDE yeniden bulunur
        WebElement category = wait.until(
                ExpectedConditions.visibilityOf(categoriesWithSubmenu.get(0)));
        actions.moveToElement(category)
                .pause(Duration.ofMillis(300))
                .perform();
        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOf(getDropdown(category)));
        WebElement subCategory = wait.until(
                ExpectedConditions.elementToBeClickable(
                        dropdown.findElement(By.xpath(".//a")))
        );
        subCategory.click();
    }





}









