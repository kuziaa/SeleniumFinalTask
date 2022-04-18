package org.antonkhmarun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Wishlist extends BasePage {

    public Wishlist(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "tbody > tr")
    private List<WebElement> wishlists;

    @FindBy(css = "#name")
    private WebElement newWishlistNameField;

    @FindBy(css = "#submitWishlist")
    private WebElement submitWishlistBtn;

    public List<WebElement> getWishlists() {
        return wishlists;
    }

    public String getWishlistName(WebElement wishlist) {
        return wishlist.findElement(By.cssSelector("td:first-child > a")).getText();
    }

    public String getWishListQuantity(WebElement wishlist) {
        return wishlist.findElement(By.cssSelector("td:nth-child(2)")).getText().trim();
    }

    public void clickDeleteWishlistBtn(WebElement wishlist) {
        wishlist.findElement(By.cssSelector(".wishlist_delete > a")).click();
    }

    public void inputNewWishlistName(String newWishlistName) {
        newWishlistNameField.sendKeys(newWishlistName);
    }

    public void clickSubmitWishlistBtn() {
        submitWishlistBtn.click();
    }
}
