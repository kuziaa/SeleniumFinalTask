package org.antonkhmarun.tests;

import com.github.javafaker.Faker;
import org.antonkhmarun.config.ConfProperties;
import org.antonkhmarun.pages.Authentication;
import org.antonkhmarun.pages.MyWishlist;
import org.antonkhmarun.pages.Product;
import org.antonkhmarun.pages.Store;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.WeakHashMap;

import static org.junit.jupiter.api.Assertions.*;


public class Wishlist extends BaseTest {

    public static Authentication authentication;
    public static Store store;
    public static Product product;
    public static MyWishlist myWishlist;
    private final Faker faker = new Faker();
    private final Random random = new Random();

    @BeforeEach
    public void beforeEachTest() {
        authentication = new Authentication(driver);
        store = new Store(driver);
        product = new Product(driver);
        myWishlist = new MyWishlist(driver);
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

        List<WebElement> wishlists = myWishlist.getWishlists();

        assertEquals(1, wishlists.size());

        WebElement wishlist = wishlists.get(0);

        assertEquals("My wishlist", myWishlist.getWishlistName(wishlist));
        assertEquals("1", myWishlist.getWishListQuantity(wishlist));

        myWishlist.clickDeleteWishlistBtn(wishlist);

        Alert al = driver.switchTo().alert();
        al.accept();

        myWishlist.logout();
    }

    @Test
    public void manuallyCreatedWishlistTest() {
        driver.get(ConfProperties.getProperty("loginPage"));
        String email = ConfProperties.getProperty("testEmail");
        String password = ConfProperties.getProperty("testPassword");
        authentication.login(email, password);

        driver.get(ConfProperties.getProperty("myWishlistsPage"));

        String newWishListName = faker.dog().name();
        myWishlist.inputNewWishlistName(newWishListName);
        myWishlist.clickSubmitWishlistBtn();

        List<WebElement> wishlists1 = myWishlist.getWishlists();
        assertEquals(1, wishlists1.size());

        WebElement wishlist1 = wishlists1.get(0);
        assertEquals(newWishListName, myWishlist.getWishlistName(wishlist1));
        assertEquals("0", myWishlist.getWishListQuantity(wishlist1));

        driver.get(ConfProperties.getProperty("storePage"));
        List<String> allProductsLinks = store.getAllProductsLinks();
        driver.get(allProductsLinks.get(random.nextInt(allProductsLinks.size())));

        product.clickWishlistBtn();

        driver.get(ConfProperties.getProperty("myWishlistsPage"));

        List<WebElement> wishlists2 = myWishlist.getWishlists();
        assertEquals(1, wishlists2.size());

        WebElement wishlist2 = wishlists1.get(0);
        assertEquals(newWishListName, myWishlist.getWishlistName(wishlist2));
        assertEquals("1", myWishlist.getWishListQuantity(wishlist2));

        myWishlist.clickDeleteWishlistBtn(wishlist2);

        Alert al = driver.switchTo().alert();
        al.accept();

        myWishlist.logout();
    }
}
