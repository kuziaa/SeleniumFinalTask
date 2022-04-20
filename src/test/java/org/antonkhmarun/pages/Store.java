package org.antonkhmarun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public String getRandomProductLink() {
        List<String> allProductsLinks = getAllProductsLinks();
        Collections.shuffle(allProductsLinks);
        return allProductsLinks.get(0);
    }

    public List<WebElement> getAllProducts() {
        return allProducts;
    }

    public List<WebElement> getNRandomProducts(int n) {
        List<Integer> allProductsIndexes = IntStream.range(0, getAmountOfProductsOnPage()).boxed().collect(Collectors.toList());
        Collections.shuffle(allProductsIndexes);

        return IntStream.range(0, n).mapToObj(i -> getAllProducts().get(allProductsIndexes.get(i))).collect(Collectors.toList());
    }

    public int getAmountOfProductsOnPage() {
        return getAllProducts().size();
    }

    public void clickAddToCartBtn(WebElement product) {
        product.findElement(By.cssSelector(".ajax_add_to_cart_button")).click();
    }

    public double getProductPrice(WebElement product) {
        String priceAsString = product.findElement(By.xpath(".//div[@class='right-block']//span[@itemprop='price']")).getText().trim().substring(1);
        return Double.parseDouble(priceAsString);
    }

    public List<Double> getProductsPrices(List<WebElement> products) {
        return products.stream().map(this::getProductPrice).collect(Collectors.toList());
    }

    public double getSumProductsPrice(List<WebElement> products) {
        return getProductsPrices(products).stream().reduce(0.0, Double::sum);
    }

    public void addProductToCart(WebElement product, WebDriverWait wait) {
        Actions actions = new Actions(driver);
        AddedToCart addedToCart = new AddedToCart(driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", product);
        actions.moveToElement(product).perform();
        clickAddToCartBtn(product);
        wait.until(ExpectedConditions.elementToBeClickable(addedToCart.getContinueShoppingBtn()));
        addedToCart.clickContinueShoppingBtn();
    }

    public void addProductsToCart(List<WebElement> products, WebDriverWait wait) {
        products.forEach(product -> addProductToCart(product, wait));
    }
}
