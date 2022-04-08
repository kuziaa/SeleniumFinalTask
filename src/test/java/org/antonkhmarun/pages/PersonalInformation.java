package org.antonkhmarun.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PersonalInformation {

    public WebDriver driver;

    public PersonalInformation(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "#id_gender1")
    private WebElement radioBtnMr;

    @FindBy(css = "#id_gender2")
    private WebElement radioBtnMrs;

    @FindBy(css = "#customer_firstname")
    private WebElement customerFirstNameField;

    @FindBy(css = "#customer_lastname")
    private WebElement customerLastNameField;

    @FindBy(css = "#passwd")
    private WebElement passwordField;

    @FindBy(css = "#days")
    private WebElement selectDay;

    @FindBy(css = "#months")
    private WebElement selectMonth;

    @FindBy(xpath = "//select[@name='years']")
    private WebElement selectYear;

    @FindBy(xpath = "//input[@name='newsletter']")
    private WebElement newsletterCheckbox;

    @FindBy(xpath = "//input[@name='optin']")
    private WebElement specialOffersCheckbox;

    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement firstnameField;

    @FindBy(xpath = "//input[@name='lastname']")
    private WebElement lastnameField;

    @FindBy(xpath = "//input[@name='company']")
    private WebElement companyField;

    @FindBy(xpath = "//input[@name='address1']")
    private WebElement addressLineOneField;

    @FindBy(xpath = "//input[@name='address2']")
    private WebElement addressLineTwoField;

    @FindBy(xpath = "//input[@name='city']")
    private WebElement cityField;

    @FindBy(xpath = "//select[@name='id_state']")
    private WebElement selectState;

    @FindBy(xpath = "//input[@name='postcode']")
    private WebElement postcodeField;

    @FindBy(xpath = "//select[@name='id_country']")
    private WebElement selectCountry;

    @FindBy(xpath = "//textarea[@name='other']")
    private WebElement additionalInformationField;

    @FindBy(xpath = "//input[@name='phone']")
    private WebElement homePhoneField;

    @FindBy(xpath = "//input[@name='phone_mobile']")
    private WebElement mobilePhoneField;

    @FindBy(xpath = "//input[@name='alias']")
    private WebElement addressAliasField;

    @FindBy(css = "#submitAccount")
    private WebElement submitAccountBtn;


    public void clickRadioBtnMr() {
        radioBtnMr.click();
    }

    public void clickRadioBtnMrs() {
        radioBtnMrs.click();
    }

    public void inputCustomerFirstName(String firstName) {
        customerFirstNameField.sendKeys(firstName);
    }

    public void inputCustomerLastName(String lastName) {
        customerLastNameField.sendKeys(lastName);
    }

    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    public Select getSelectDay() {
        return new Select(selectDay);
    }

    public Select getSelectMonth() {
        return new Select(selectMonth);
    }

    public Select getSelectYear() {
        return new Select(selectYear);
    }

    public void clickNewsletterCheckbox() {
        newsletterCheckbox.click();
    }

    public void clickSpecialOffersCheckbox() {
        specialOffersCheckbox.click();
    }

    public void inputFirstname(String firstname) {
        firstnameField.sendKeys(firstname);
    }

    public void inputLastname(String lastname) {
        lastnameField.sendKeys(lastname);
    }

    public void inputCompany(String company) {
        companyField.sendKeys(company);
    }

    public void inputAddressLineOne(String address) {
        addressLineOneField.sendKeys(address);
    }

    public void inputAddressLineTwo(String address) {
        addressLineTwoField.sendKeys(address);
    }

    public void inputCity(String city) {
        cityField.sendKeys(city);
    }

    public Select getSelectCountry() {
        return new Select(selectCountry);
    }

    public Select getSelectState() {
        return new Select(selectState);
    }

    public void inputPostCode(String postCode) {
        postcodeField.sendKeys(postCode);
    }

    public void inputAdditionalInformation(String additionalInformation) {
        additionalInformationField.sendKeys(additionalInformation);
    }

    public void inputHomePhone(String phone) {
        homePhoneField.sendKeys(phone);
    }

    public void inputMobilePhone(String phone) {
        mobilePhoneField.sendKeys(phone);
    }

    public void inputAddressAlias(String addressAlias) {
        addressAliasField.sendKeys(addressAlias);
    }

    public void pressSubmitAccountBtn() {
        submitAccountBtn.click();
    }
}
