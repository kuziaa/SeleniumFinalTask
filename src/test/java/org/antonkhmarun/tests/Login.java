package org.antonkhmarun.tests;

import org.antonkhmarun.config.ConfProperties;
import org.antonkhmarun.pages.Authentication;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class Login extends BaseTest {

    public static Authentication authentication;

    @BeforeEach
    public void BeforeEachTest() {
        authentication = new Authentication(driver);
        driver.get(ConfProperties.getProperty("loginPage"));
    }

    @Test
    public void loginWithCorrectData() {
        String email = ConfProperties.getProperty("testEmail");
        String password = ConfProperties.getProperty("testPassword");

        authentication.login(email, password);

        assertEquals("Anton Khmarun", authentication.getAccountName());
        authentication.logout();
    }

    @Test
    public void loginWithoutEmail() {
        String password = ConfProperties.getProperty("testPassword");

        authentication.inputPassword(password);
        authentication.clickSubmitLoginBtn();

        String expectedAlertTitle = "There is 1 error";
        String expectedAlertMessage1 = "An email address required.";

        assertEquals(expectedAlertTitle, authentication.getAlertDangerTitle());
        assertEquals(List.of(expectedAlertMessage1), authentication.getListAlertDangerMessages());
    }
}
