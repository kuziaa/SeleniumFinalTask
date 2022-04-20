package org.antonkhmarun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Cart extends BasePage {

    public Cart(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".cart_item")
    private List<WebElement> productsInCart;

    @FindBy(css = ".cart-info > .price")
    private List<WebElement> pricesOfProductsInCart;

    @FindBy(css = "#total_shipping")
    private WebElement totalShipping;

    @FindBy(css = "#total_tax")
    private WebElement totalTax;

    @FindBy(css = "#total_price")
    private WebElement totalPrice;

    public List<WebElement> getProductsInCart() {
        return productsInCart;
    }

    public double getTotalShippingPrice() {
        return Double.parseDouble(totalShipping.getText().trim().substring(1));
    }

    public double getTotalTaxPrice() {
        return Double.parseDouble(totalTax.getText().trim().substring(1));
    }

    public double getTotalPrice() {
        return Double.parseDouble(totalPrice.getText().trim().substring(1));
    }

    public void delProductFromCart(WebElement product) {
        product.findElement(By.xpath(".//a[@class='cart_quantity_delete']")).click();
    }

    public void delAllProductsFromCart(WebDriverWait wait) {
        getProductsInCart().forEach(product -> {
            delProductFromCart(product);
            wait.until(ExpectedConditions.invisibilityOfAllElements(product));
        });
    }

    public int getAmountOfProductsInCart() {
        return getProductsInCart().size();
    }
}
