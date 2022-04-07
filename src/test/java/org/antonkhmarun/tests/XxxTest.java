package org.antonkhmarun.tests;

import org.antonkhmarun.config.ConfProperties;
import org.antonkhmarun.pages.Login;
import org.antonkhmarun.pages.PersonalInformation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XxxTest extends BaseTest{

    public static Login login;
    public static PersonalInformation personalInformation;

    @Test
    public void test1() {
        login = new Login(driver);
        personalInformation = new PersonalInformation(driver);
        driver.get(ConfProperties.getProperty("loginPage"));

        login.inputEmail("xxx@mail.ru");
        login.clickCreateAccountBtn();

        personalInformation.clickRadioBtnMr();


        assertEquals(1, 1);
    }
}
