package org.antonkhmarun.tests;

import com.github.javafaker.Faker;
import org.antonkhmarun.config.ConfProperties;
import org.antonkhmarun.enums.Country;
import org.antonkhmarun.enums.State;
import org.antonkhmarun.pages.Authentication;
import org.antonkhmarun.pages.PersonalInformation;
import org.antonkhmarun.support.RandomDateGenerator;
import org.junit.jupiter.api.AfterEach;
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
        personalInformation.fillAllFieldsWithRandomValidData(firstName, lastName);

        personalInformation.clickSubmitAccountBtn();

        assertEquals(firstName + " " + lastName, authentication.getAccountName());
    }

    @Test
    public void createAccountWithoutCustomerFirstName() throws ParseException {
        authentication.inputEmailCreate(faker.internet().emailAddress());
        authentication.clickCreateAccountBtn();

        personalInformation.clickRandomRadioBtnMrMrs();

        personalInformation.fillAllFieldsWithRandomValidData();

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

        personalInformation.fillAllFieldsWithRandomValidData();

        personalInformation.cleanCustomerFirstName();
        personalInformation.cleanCustomerLastName();
        personalInformation.clickSubmitAccountBtn();

        String expectedAlertTitle = "There are 2 errors";
        String expectedAlertMessage1 = "lastname is required.";
        String expectedAlertMessage2 = "firstname is required.";

        assertEquals(expectedAlertTitle, personalInformation.getAlertDangerTitle());
        assertEquals(List.of(expectedAlertMessage1, expectedAlertMessage2), personalInformation.getListAlertDangerMessages());
    }

    @AfterEach
    public void cleanUp() {
        authentication.logout();
    }
}
