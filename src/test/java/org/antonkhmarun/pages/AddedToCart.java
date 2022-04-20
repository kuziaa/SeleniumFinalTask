package org.antonkhmarun.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddedToCart extends BasePage {

    public AddedToCart(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".continue")
    private WebElement continueShoppingBtn;

    @FindBy(css = "a[title='Proceed to checkout']")
    private WebElement proceedToCheckoutBtn;

    public WebElement getContinueShoppingBtn() {
        return continueShoppingBtn;
    }

    public void clickContinueShoppingBtn() {
        continueShoppingBtn.click();
    }

    public void clickProceedToCheckoutBtn() {
        proceedToCheckoutBtn.click();
    }
}
