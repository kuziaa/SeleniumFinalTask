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

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cart extends BaseTest {

    public static Authentication authentication;
    public static Store store;
    public static AddedToCart addedToCart;

    @BeforeEach
    public void BeforeEachTest() {
        authentication = new Authentication(driver);
        addedToCart = new AddedToCart(driver);
        store = new Store(driver);
        driver.get(ConfProperties.getProperty("loginPage"));
    }

    @Test
    public void AddProductsInCart() {
        String email = ConfProperties.getProperty("testEmail");
        String password = ConfProperties.getProperty("testPassword");

        authentication.login(email, password);

        driver.get(ConfProperties.getProperty("storePage"));

        List<WebElement> allProducts = store.getAllProducts();

        System.out.println(allProducts.size());
        Collections.shuffle(allProducts);
        System.out.println(allProducts);

        List<WebElement> productsForCart = new ArrayList<>();
        productsForCart.add(allProducts.get(0));
        productsForCart.add(allProducts.get(1));
        productsForCart.add(allProducts.get(2));

        List<Double> pricesOfProductsToCart = productsForCart.stream().map(webElement -> store.getProductPrice(webElement)).collect(Collectors.toList());
        System.out.println(pricesOfProductsToCart);

        Actions actions = new Actions(driver);
        actions.moveToElement(productsForCart.get(0)).perform();
        store.clickAddToCartBtn(productsForCart.get(0));
        addedToCart.clickContinueShoppingBtn();

        actions.moveToElement(productsForCart.get(1)).perform();
        store.clickAddToCartBtn(productsForCart.get(1));
        addedToCart.clickContinueShoppingBtn();

        actions.moveToElement(productsForCart.get(2)).perform();
        store.clickAddToCartBtn(productsForCart.get(2));
        addedToCart.clickProceedToCheckoutBtn();
    }
}
