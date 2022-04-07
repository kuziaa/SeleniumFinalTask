package org.antonkhmarun.tests;

import com.github.javafaker.Faker;
import org.antonkhmarun.config.ConfProperties;
import org.antonkhmarun.pages.Login;
import org.antonkhmarun.pages.PersonalInformation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatingAccount extends BaseTest{

    public static Login login;
    public static PersonalInformation personalInformation;
    Faker facker = new Faker();

//    @ParameterizedTest
//    @CsvFileSource(resources = "")
    @Test
    public void allFieldsWithCorrectData() {
        login = new Login(driver);
        personalInformation = new PersonalInformation(driver);
        driver.get(ConfProperties.getProperty("loginPage"));

        login.inputEmail(facker.internet().emailAddress());
        login.clickCreateAccountBtn();

        personalInformation.clickRadioBtnMr();

        personalInformation.


        assertEquals(1, 1);
    }

    private void fillAllFieldsWithRandomCorrectData() {
        personalInformation.inputCustomerFirstName(facker.name().firstName());
        personalInformation.inputCustomerLastName(facker.name().lastName());
        personalInformation.inputPassword(facker.internet().password());
    }
}
