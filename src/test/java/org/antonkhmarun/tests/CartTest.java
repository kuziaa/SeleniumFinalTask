package org.antonkhmarun.tests;

import org.antonkhmarun.config.ConfProperties;
import org.antonkhmarun.pages.AddedToCart;
import org.antonkhmarun.pages.Authentication;
import org.antonkhmarun.pages.Cart;
import org.antonkhmarun.pages.Store;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

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
        assertTrue(numOfProdToCart < store.getAmountOfProductsOnPage());

        List<WebElement> productsForCart = store.getNRandomProducts(numOfProdToCart);
        double sumPricesOfProductsInCart = store.getSumProductsPrice(productsForCart);

        store.addProductsToCart(productsForCart, wait);

        driver.get(ConfProperties.getProperty("cartPage"));

        assertEquals(numOfProdToCart, cart.getAmountOfProductsInCart());

        Double expectedTotalPriceInCart = sumPricesOfProductsInCart + cart.getTotalShippingPrice() + cart.getTotalTaxPrice();
        expectedTotalPriceInCart = (double) Math.round(expectedTotalPriceInCart * 100) / 100;

        assertEquals(expectedTotalPriceInCart, cart.getTotalPrice());
    }

    @AfterEach
    public void cleanUp() {
        driver.get(ConfProperties.getProperty("cartPage"));
        cart.delAllProductsFromCart(wait);
        cart.logout();
    }
}
