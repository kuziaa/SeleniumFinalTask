package org.antonkhmarun.tests;

import com.github.javafaker.Faker;
import org.antonkhmarun.config.ConfProperties;
import org.antonkhmarun.enums.Country;
import org.antonkhmarun.enums.State;
import org.antonkhmarun.pages.Login;
import org.antonkhmarun.pages.MyAccount;
import org.antonkhmarun.pages.PersonalInformation;
import org.antonkhmarun.supply.RandomDataGenerator;
import org.antonkhmarun.supply.RandomDateGenerator;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.Select;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatingAccount extends BaseTest{

    public static Login login;
    public static PersonalInformation personalInformation;
    public static MyAccount myAccount;
    Faker faker = new Faker();

//    @ParameterizedTest
//    @CsvFileSource(resources = "")
    @Test
    public void allFieldsWithCorrectData() throws ParseException {
        login = new Login(driver);
        personalInformation = new PersonalInformation(driver);
        myAccount = new MyAccount(driver);
        driver.get(ConfProperties.getProperty("loginPage"));

        login.inputEmail(faker.internet().emailAddress());
        login.clickCreateAccountBtn();

        personalInformation.clickRadioBtnMr();

        fillAllFieldsWithRandomValidData();

        assertEquals("Mu account", myAccount.getPageHeadingText());
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
        personalInformation.inputFirstname(faker.name().firstName());
        personalInformation.inputLastname(faker.name().lastName());
        personalInformation.inputCompany(faker.company().name());
        personalInformation.inputAddressLineOne(faker.address().fullAddress());
        personalInformation.inputAddressLineTwo(faker.address().secondaryAddress());

        Select selectCountry = personalInformation.getSelectCountry();
        selectCountry.selectByVisibleText(Country.getRandomCountry().getCountry());

        personalInformation.inputCity(faker.address().city());

        Select selectState = personalInformation.getSelectState();
        selectState.selectByVisibleText(State.getRandomState().getState());

        personalInformation.inputPostCode(faker.address().zipCode());

        personalInformation.inputAdditionalInformation(faker.harryPotter().quote());
        personalInformation.inputHomePhone(faker.phoneNumber().cellPhone());
        personalInformation.inputMobilePhone(faker.phoneNumber().cellPhone());
        personalInformation.inputAddressAlias(faker.dog().name());
    }
}
