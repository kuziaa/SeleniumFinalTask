package org.antonkhmarun.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccount {

    public WebDriver driver;

    public MyAccount(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = ".page-heading")
    private WebElement pageHeading;

    public String getPageHeadingText() {
        return pageHeading.getText();
    }
}
