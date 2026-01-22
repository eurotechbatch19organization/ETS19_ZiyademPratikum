package com.ziyadem.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NavigationBarPage extends BasePage{

    @FindBy(xpath = "//div[@id='wide-nav']")
    private WebElement navigationBar;

    public WebElement getNavigationBar() {return navigationBar;}

    @FindBy(xpath = "//div[@id='wide-nav']//a[contains(@class,'nav-top-link')]")
    private List<WebElement> mainCategories;

    public List<WebElement> getMainCategories() {return mainCategories;}



    /**
     * Navigasyon bar’da görüntülenen ana kategori isimlerini
     * sayfadan alır ve String listesi olarak döndürür.
     */
    public List<String> getMainCategoryNames() {
        List<String> expectedCategories = getExpectedMainCategoryNames();
        List<String> actualCategories = new ArrayList<>();

        for (WebElement category : mainCategories) {
            String text = category.getText().trim();
            if (expectedCategories.contains(text)) {
                actualCategories.add(text);
            }
        }
        return actualCategories;
    }

    /**
     * Ana menüde olması beklenen kategori isimlerini döner
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

}
