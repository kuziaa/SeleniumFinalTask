package org.antonkhmarun.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public WebDriver driver;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = ".account")
    private WebElement accountName;

    @FindBy(css = ".header_user_info > .logout")
    private WebElement logoutBtn;

    public String getAccountName() {
        return accountName.getText();
    }

    public void logout() {
        logoutBtn.click();
    }

    public String getLogoutLink() {
        return logoutBtn.getAttribute("href");
    }
}
