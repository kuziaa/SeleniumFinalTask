package org.antonkhmarun.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Product extends BasePage {

    public Product(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#wishlist_button")
    private WebElement wishlistBtn;

    public void clickWishlistBtn() {
        wishlistBtn.click();
    }
}
