package org.antonkhmarun.tests;

import com.github.javafaker.Faker;
import org.antonkhmarun.config.ConfProperties;
import org.antonkhmarun.pages.Authentication;
import org.antonkhmarun.pages.Wishlist;
import org.antonkhmarun.pages.Product;
import org.antonkhmarun.pages.Store;
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

        List<String> allProductsLinks = store.getAllProductsLinks();
        driver.get(allProductsLinks.get(random.nextInt(allProductsLinks.size())));

        product.clickWishlistBtn();

        driver.get(ConfProperties.getProperty("myWishlistsPage"));

        List<WebElement> wishlists = wishlist.getWishlists();

        assertEquals(1, wishlists.size());

        WebElement wishlist = wishlists.get(0);

        assertEquals("My wishlist", WishlistTest.wishlist.getWishlistName(wishlist));
        assertEquals("1", WishlistTest.wishlist.getWishListQuantity(wishlist));

        WishlistTest.wishlist.clickDeleteWishlistBtn(wishlist);

        Alert al = driver.switchTo().alert();
        al.accept();

        WishlistTest.wishlist.logout();
    }

    @Test
    public void manuallyCreatedWishlistTest() {
        driver.get(ConfProperties.getProperty("loginPage"));
        String email = ConfProperties.getProperty("testEmail");
        String password = ConfProperties.getProperty("testPassword");
        authentication.login(email, password);

        driver.get(ConfProperties.getProperty("myWishlistsPage"));

        String newWishListName = faker.dog().name();
        wishlist.inputNewWishlistName(newWishListName);
        wishlist.clickSubmitWishlistBtn();

        List<WebElement> wishlists1 = wishlist.getWishlists();
        assertEquals(1, wishlists1.size());

        WebElement wishlist1 = wishlists1.get(0);
        assertEquals(newWishListName, wishlist.getWishlistName(wishlist1));
        assertEquals("0", wishlist.getWishListQuantity(wishlist1));

        driver.get(ConfProperties.getProperty("storePage"));
        List<String> allProductsLinks = store.getAllProductsLinks();
        driver.get(allProductsLinks.get(random.nextInt(allProductsLinks.size())));

        product.clickWishlistBtn();

        driver.get(ConfProperties.getProperty("myWishlistsPage"));

        List<WebElement> wishlists2 = wishlist.getWishlists();
        assertEquals(1, wishlists2.size());

        WebElement wishlist2 = wishlists1.get(0);
        assertEquals(newWishListName, wishlist.getWishlistName(wishlist2));
        assertEquals("1", wishlist.getWishListQuantity(wishlist2));

        wishlist.clickDeleteWishlistBtn(wishlist2);

        Alert al = driver.switchTo().alert();
        al.accept();

        wishlist.logout();
    }
}
