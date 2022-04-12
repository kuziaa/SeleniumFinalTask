package org.antonkhmarun.tests;

import com.beust.ah.A;
import org.antonkhmarun.config.ConfProperties;
import org.antonkhmarun.pages.AddedToCart;
import org.antonkhmarun.pages.Authentication;
import org.antonkhmarun.pages.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest extends BaseTest {

    public static Authentication authentication;
    public static Store store;
    public static Cart cart;
    public static AddedToCart addedToCart;

    @BeforeEach
    public void BeforeEachTest() {
        authentication = new Authentication(driver);
        addedToCart = new AddedToCart(driver);
        cart = new Cart(driver);
        store = new Store(driver);
        driver.get(ConfProperties.getProperty("loginPage"));
    }

    @Test
    public void AddProductsToCart() {
        String email = ConfProperties.getProperty("testEmail");
        String password = ConfProperties.getProperty("testPassword");
        authentication.login(email, password);

        driver.get(ConfProperties.getProperty("storePage"));
        int numOfProdToCart = 3;

        List<WebElement> allProducts = store.getAllProducts();
        assertTrue(numOfProdToCart < allProducts.size());

        List<Integer> allProductsIndexes = IntStream.range(0, allProducts.size()).boxed().collect(Collectors.toList());
        Collections.shuffle(allProductsIndexes);

        List<WebElement> productsForCart = IntStream.range(0, numOfProdToCart).mapToObj(i -> allProducts.get(allProductsIndexes.get(i))).collect(Collectors.toList());

        List<Double> pricesOfProductsInCart = productsForCart.stream().map(webElement -> store.getProductPrice(webElement)).collect(Collectors.toList());
        double sumPricesOfProductsInCart = pricesOfProductsInCart.stream().reduce(0.0, Double::sum);
        System.out.println(pricesOfProductsInCart);

        productsForCart.forEach(product -> {
            Actions actions = new Actions(driver);
            actions.moveToElement(product).perform();
            store.clickAddToCartBtn(product);
            wait.until(ExpectedConditions.elementToBeClickable(addedToCart.getContinueShoppingBtn()));
            actions.moveToElement(addedToCart.getContinueShoppingBtn()).perform();
            addedToCart.getContinueShoppingBtn().click();
        });

        List<WebElement> productsInCart = cart.getProductsInCart();

        assertEquals(numOfProdToCart, productsInCart.size());

        Double expectedTotalPriceInCart = sumPricesOfProductsInCart + cart.getTotalShippingPrice() + cart.getTotalTaxPrice();
        assertEquals(expectedTotalPriceInCart, cart.getTotalPrice());
    }
}
