package org.antonkhmarun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class Store extends BasePage {

    public Store(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//ul[@id='homefeatured']//div[@class='product-container']")
    private List<WebElement> allProducts;

    @FindBy(xpath = "//ul[@id='homefeatured']//div[@class='product-container']//a[@class='product_img_link']")
    private List<WebElement> productsImgLinks;

    public List<String> getAllProductsLinks() {
        return productsImgLinks.stream().map(element -> element.getAttribute("href")).collect(Collectors.toList());
    }

    public List<WebElement> getAllProducts() {
        return allProducts;
    }

    public void clickAddToCartBtn(WebElement product) {
        product.findElement(By.cssSelector(".ajax_add_to_cart_button")).click();
    }

    public double getProductPrice(WebElement product) {
        String priceAsString = product.findElement(By.xpath("//div[@class='right-block']//span[@itemprop='price']")).getText().trim().substring(1);
        return Double.parseDouble(priceAsString);
    }
}
