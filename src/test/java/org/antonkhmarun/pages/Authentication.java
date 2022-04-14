package org.antonkhmarun.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class Authentication extends BasePage {

    public Authentication(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#email_create")
    private WebElement emailCreateField;

    @FindBy(css = "#SubmitCreate")
    private WebElement createAccountBtn;

    @FindBy(css = "#email")
    private WebElement emailField;

    @FindBy(css = "#passwd")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@name='SubmitLogin']")
    private WebElement submitLoginBtn;

    @FindBy(css = ".alert-danger")
    private WebElement alertDanger;

    @FindBy(css = ".alert-danger > p")
    private WebElement alertDangerTitle;

    @FindBy(css = ".alert-danger > ol > li")
    private List<WebElement> alertDangerMessages;

    public void inputEmailCreate(String email) {
        emailCreateField.sendKeys(email);
    }

    public void clickCreateAccountBtn() {
        createAccountBtn.click();
    }

    public void inputEmail(String email) {
        emailField.sendKeys(email);
    }

    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSubmitLoginBtn() {
        submitLoginBtn.click();
    }

    public void login(String email, String password) {
        inputEmail(email);
        inputPassword(password);
        clickSubmitLoginBtn();
    }

    public String getAlertDangerTitle() {
        return alertDangerTitle.getText();
    }

    public List<String> getListAlertDangerMessages() {
        return alertDangerMessages.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
