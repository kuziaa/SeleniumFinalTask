package org.antonkhmarun.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

    public WebDriver driver;

    public Login(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "#email_create")
    private WebElement emailField;

    @FindBy(css = "#SubmitCreate")
    private WebElement createAccountBtn;

    public void inputEmail(String email) {
        emailField.sendKeys(email);
    }

    public void clickCreateAccountBtn() {
        createAccountBtn.click();
    }
}
