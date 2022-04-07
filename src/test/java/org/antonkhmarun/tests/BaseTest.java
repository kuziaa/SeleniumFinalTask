package org.antonkhmarun.tests;

import org.antonkhmarun.config.ConfProperties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

//@ExtendWith(TestResultWatcher.class)
public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void setup() throws MalformedURLException {
        driver = getDriver();
        driver.manage().window().maximize();
//        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private static WebDriver getDriver() throws MalformedURLException {
        String environment = System.getProperty("environment", "local");

        switch (environment) {
            case "local": {
                return getLocalDriver();
            }
            case "grid": {
                return getGridDriver();
            }
            case "sauceLab": {
                return getSauceLabDriver();
            }
            default: {
                throw new RuntimeException("Unexpected environment: " + environment);
            }
        }
    }

    private static WebDriver getLocalDriver() {
        String localBrowserName = System.getProperty("browser", "chrome");

        switch (localBrowserName) {
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
                return new ChromeDriver();
            }
            case "firefox": {
                System.setProperty("webdriver.geco.driver", ConfProperties.getProperty("firefox"));
                return new FirefoxDriver();
            }
            default: {
                throw new RuntimeException("Unexpected browser name: " + localBrowserName);
            }
        }
    }

    private static WebDriver getGridDriver() throws MalformedURLException {
        String gridBrowserName = System.getProperty("browser", "chrome");
        String gridPlatformName = System.getProperty("platform", "windows10");
        String gridLocalHost = ConfProperties.getProperty("gridLocalHost");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        switch (gridBrowserName) {
            case "chrome": {
                desiredCapabilities.setBrowserName("chrome");
                break;
            }
            case "firefox": {
                desiredCapabilities.setBrowserName("firefox");
                break;
            }
            default: {
                throw new RuntimeException("Unexpected browser name: " + gridBrowserName);
            }
        }

        switch (gridPlatformName) {
            case "windows10": {
                desiredCapabilities.setPlatform(Platform.WIN10);
                break;
            }
            case "windows8.1": {
                desiredCapabilities.setPlatform(Platform.WIN8_1);
                break;
            }
            default: {
                throw new RuntimeException("Unexpected platform name: " + gridPlatformName);
            }
        }

        return new RemoteWebDriver(new URL(gridLocalHost), desiredCapabilities);
    }

    private static WebDriver getSauceLabDriver() throws MalformedURLException {
        String sauceBrowserName = System.getProperty("browser", "chrome");
        String sauceBrowserVersion = System.getProperty("browserVersion", "latest");
        String saucePlatformName = System.getProperty("platform", "windows 10");

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", ConfProperties.getProperty("sauceUserName"));
        sauceOptions.setCapability("accessKey", ConfProperties.getProperty("sauceAccessKey"));

        String sauceUrl = ConfProperties.getProperty("sauceUrl");

        switch (sauceBrowserName) {
            case "chrome": {
                ChromeOptions browserOptions = new ChromeOptions();
                browserOptions.setPlatformName(saucePlatformName);
                browserOptions.setBrowserVersion(sauceBrowserVersion);
                browserOptions.setCapability("sauce:options", sauceOptions);
                return new RemoteWebDriver(new URL(sauceUrl), browserOptions);
            }
            case "firefox": {
                FirefoxOptions browserOptions = new FirefoxOptions();
                browserOptions.setPlatformName(saucePlatformName);
                browserOptions.setBrowserVersion(sauceBrowserVersion);
                browserOptions.setCapability("sauce:options", sauceOptions);
                return new RemoteWebDriver(new URL(sauceUrl), browserOptions);
            }
            case "edge": {
                EdgeOptions browserOptions = new EdgeOptions();
                browserOptions.setPlatformName(saucePlatformName);
                browserOptions.setBrowserVersion(sauceBrowserVersion);
                browserOptions.setCapability("sauce:options", sauceOptions);
                return new RemoteWebDriver(new URL(sauceUrl), browserOptions);
            }
            default:
                throw new RuntimeException("Unexpected browser name: " + sauceBrowserName);
        }
    }
}
