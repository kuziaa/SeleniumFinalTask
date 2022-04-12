package org.antonkhmarun.tests;

import org.antonkhmarun.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Double> getPricesOfProductsInCart() {
        return pricesOfProductsInCart.stream().map(product -> Double.parseDouble(product.getText().trim().substring(1))).collect(Collectors.toList());
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

}
