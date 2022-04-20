package org.antonkhmarun.tests;

import com.github.javafaker.Faker;
import org.antonkhmarun.config.ConfProperties;
import org.antonkhmarun.pages.Authentication;
import org.antonkhmarun.pages.Wishlist;
import org.antonkhmarun.pages.Product;
import org.antonkhmarun.pages.Store;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


public class WishlistTest extends BaseTest {

    public static Authentication authentication;
    public static Store store;
    public static Product product;
    public static Wishlist wishlist;
    private final Faker faker = new Faker();
    private final Random random = new Random();

    @BeforeEach
    public void beforeEachTest() {
        authentication = new Authentication(driver);
        store = new Store(driver);
        product = new Product(driver);
        wishlist = new Wishlist(driver);
    }

    @Test
    public void autoCreateWishlistTest() {
        driver.get(ConfProperties.getProperty("loginPage"));
        String email = ConfProperties.getProperty("testEmail");
        String password = ConfProperties.getProperty("testPassword");
        authentication.login(email, password);

        driver.get(ConfProperties.getProperty("storePage"));

        driver.get(store.getRandomProductLink());

        product.clickWishlistBtn();

        driver.get(ConfProperties.getProperty("myWishlistsPage"));

        List<WebElement> wishlists = wishlist.getWishlists();

        assertEquals(1, wishlist.getAmountOfWishlists());

        WebElement wishlist1 = wishlists.get(0);

        assertEquals("My wishlist", wishlist.getWishlistName(wishlist1));
        assertEquals("1", wishlist.getWishListQuantity(wishlist1));
    }

    @Test
    public void manuallyCreatedWishlistTest() {
        driver.get(ConfProperties.getProperty("loginPage"));
        String email = ConfProperties.getProperty("testEmail");
        String password = ConfProperties.getProperty("testPassword");
        authentication.login(email, password);

        driver.get(ConfProperties.getProperty("myWishlistsPage"));

        String wishListName = faker.dog().name();
        wishlist.inputNewWishlistName(wishListName);
        wishlist.clickSubmitWishlistBtn();

        List<WebElement> allWishlists = wishlist.getWishlists();
        assertEquals(1, wishlist.getAmountOfWishlists());

        WebElement justCreatedWishlist = allWishlists.get(0);
        assertEquals(wishListName, wishlist.getWishlistName(justCreatedWishlist));
        assertEquals("0", wishlist.getWishListQuantity(justCreatedWishlist));

        driver.get(ConfProperties.getProperty("storePage"));
        driver.get(store.getRandomProductLink());

        product.clickWishlistBtn();

        driver.get(ConfProperties.getProperty("myWishlistsPage"));

        allWishlists = wishlist.getWishlists();
        assertEquals(1, allWishlists.size());

        WebElement newWishlist = allWishlists.get(0);
        assertEquals(wishListName, wishlist.getWishlistName(newWishlist));
        assertEquals("1", wishlist.getWishListQuantity(newWishlist));
    }

    @AfterEach
    public void cleanUp() {
        driver.get(ConfProperties.getProperty("myWishlistsPage"));
        wishlist.deleteAllWishlists(wait);
        wishlist.logout();
    }
}
