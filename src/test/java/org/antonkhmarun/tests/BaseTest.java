package org.antonkhmarun.tests;

import org.antonkhmarun.support.MyDriver;
import org.antonkhmarun.support.TestResultWatcher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

@ExtendWith(TestResultWatcher.class)
public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void setup() throws MalformedURLException {
        driver = MyDriver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

}
