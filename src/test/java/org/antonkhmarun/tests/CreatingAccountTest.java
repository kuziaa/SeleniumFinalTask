package org.antonkhmarun.tests;

import com.github.javafaker.Faker;
import org.antonkhmarun.config.ConfProperties;
import org.antonkhmarun.enums.Country;
import org.antonkhmarun.enums.State;
import org.antonkhmarun.pages.Authentication;
import org.antonkhmarun.pages.PersonalInformation;
import org.antonkhmarun.supply.RandomDateGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.Select;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreatingAccountTest extends BaseTest{

    public static PersonalInformation personalInformation;
    public static Authentication authentication;
    private final Faker faker = new Faker();

    @BeforeEach
    public void beforeEachTest() {
        personalInformation = new PersonalInformation(driver);
        authentication = new Authentication(driver);
        driver.get(ConfProperties.getProperty("loginPage"));
    }

    @Test
    public void createAccountTest() throws ParseException {
        authentication.inputEmailCreate(faker.internet().emailAddress());
        authentication.clickCreateAccountBtn();

        personalInformation.clickRandomRadioBtnMrMrs();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        fillAllFieldsWithRandomValidData(firstName, lastName);

        personalInformation.clickSubmitAccountBtn();

        assertEquals(firstName + " " + lastName, authentication.getAccountName());

        authentication.logout();
    }

    @Test
    public void createAccountWithoutCustomerFirstName() throws ParseException {
        authentication.inputEmailCreate(faker.internet().emailAddress());
        authentication.clickCreateAccountBtn();

        personalInformation.clickRandomRadioBtnMrMrs();

        fillAllFieldsWithRandomValidData();

        personalInformation.cleanCustomerFirstName();
        personalInformation.clickSubmitAccountBtn();

        String expectedAlertTitle = "There is 1 error";
        String expectedAlertMessage = "firstname is required.";

        assertEquals(expectedAlertTitle, personalInformation.getAlertDangerTitle());
        assertEquals(List.of(expectedAlertMessage), personalInformation.getListAlertDangerMessages());
    }

    @Test
    public void createAccountWithoutCustomerFirstAndLastName() throws ParseException {
        authentication.inputEmailCreate(faker.internet().emailAddress());
        authentication.clickCreateAccountBtn();

        personalInformation.clickRandomRadioBtnMrMrs();

        fillAllFieldsWithRandomValidData();

        personalInformation.cleanCustomerFirstName();
        personalInformation.cleanCustomerLastName();
        personalInformation.clickSubmitAccountBtn();

        String expectedAlertTitle = "There are 2 errors";
        String expectedAlertMessage1 = "lastname is required.";
        String expectedAlertMessage2 = "firstname is required.";

        assertEquals(expectedAlertTitle, personalInformation.getAlertDangerTitle());
        assertEquals(List.of(expectedAlertMessage1, expectedAlertMessage2), personalInformation.getListAlertDangerMessages());
    }

    private void fillAllFieldsWithRandomValidData() throws ParseException {
        personalInformation.inputCustomerFirstName(faker.name().firstName());
        personalInformation.inputCustomerLastName(faker.name().lastName());
        personalInformation.inputPassword(faker.internet().password());

        Select selectYear = personalInformation.getSelectYear();
        Select selectMonth = personalInformation.getSelectMonth();
        Select selectDay = personalInformation.getSelectDay();

        RandomDateGenerator validDate = new RandomDateGenerator();
        selectYear.selectByValue(validDate.getYear());
        selectMonth.selectByValue(String.valueOf(validDate.getMonth()));
        selectDay.selectByValue(String.valueOf(validDate.getDay()));

        personalInformation.clickNewsletterCheckbox();
        personalInformation.clickSpecialOffersCheckbox();
        personalInformation.inputCompany(faker.company().name());
        personalInformation.inputAddressLineOne(faker.address().fullAddress());
        personalInformation.inputAddressLineTwo(faker.address().secondaryAddress());

        Select selectCountry = personalInformation.getSelectCountry();
        selectCountry.selectByVisibleText(Country.getRandomCountry().getCountry());

        personalInformation.inputCity(faker.address().city());

        Select selectState = personalInformation.getSelectState();
        selectState.selectByVisibleText(State.getRandomState().getState());

        personalInformation.inputPostCode(faker.address().zipCode().substring(0, 5));

        personalInformation.inputAdditionalInformation(faker.harryPotter().quote());
        personalInformation.inputHomePhone(faker.phoneNumber().cellPhone());
        personalInformation.inputMobilePhone(faker.phoneNumber().cellPhone());

        personalInformation.clearAddressAlias();
        personalInformation.inputAddressAlias(faker.dog().name());
    }

    private void fillAllFieldsWithRandomValidData(String firstName, String lastName) throws ParseException {
        personalInformation.inputCustomerFirstName(firstName);
        personalInformation.inputCustomerLastName(lastName);
        personalInformation.inputPassword(faker.internet().password());

        Select selectYear = personalInformation.getSelectYear();
        Select selectMonth = personalInformation.getSelectMonth();
        Select selectDay = personalInformation.getSelectDay();

        RandomDateGenerator validDate = new RandomDateGenerator();
        selectYear.selectByValue(validDate.getYear());
        selectMonth.selectByValue(String.valueOf(validDate.getMonth()));
        selectDay.selectByValue(String.valueOf(validDate.getDay()));

        personalInformation.clickNewsletterCheckbox();
        personalInformation.clickSpecialOffersCheckbox();
        personalInformation.inputCompany(faker.company().name());
        personalInformation.inputAddressLineOne(faker.address().fullAddress());
        personalInformation.inputAddressLineTwo(faker.address().secondaryAddress());

        Select selectCountry = personalInformation.getSelectCountry();
        selectCountry.selectByVisibleText(Country.getRandomCountry().getCountry());

        personalInformation.inputCity(faker.address().city());

        Select selectState = personalInformation.getSelectState();
        selectState.selectByVisibleText(State.getRandomState().getState());

        personalInformation.inputPostCode(faker.address().zipCode().substring(0, 5));

        personalInformation.inputAdditionalInformation(faker.harryPotter().quote());
        personalInformation.inputHomePhone(faker.phoneNumber().cellPhone());
        personalInformation.inputMobilePhone(faker.phoneNumber().cellPhone());

        personalInformation.clearAddressAlias();
        personalInformation.inputAddressAlias(faker.dog().name());
    }
}
