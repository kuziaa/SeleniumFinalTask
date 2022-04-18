package org.antonkhmarun.support;

import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestResultWatcher implements TestWatcher {

    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        saveScreenshot(getDriver(context));
        getDriverInfo(context);
    }

    private WebDriver getDriver(ExtensionContext context) throws NoSuchFieldException, IllegalAccessException {
        Object test = context.getRequiredTestInstance();
        Field field = test.getClass().getSuperclass().getDeclaredField("driver");
        field.setAccessible(true);
        WebDriver driver = (WebDriver) field.get(test);
        return (WebDriver) field.get(test);
    }

    public String getDateAndTime() {
        Date date = new Date();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        return formatter.format(date);
    }

    @Attachment(value = "Environment info:")
    private StringBuilder getDriverInfo(ExtensionContext context) throws NoSuchFieldException, IllegalAccessException {
        WebDriver driver = getDriver(context);
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        return new StringBuilder()
                .append("Browser name: ").append(capabilities.getBrowserName()).append("\n")
                .append("Browser version: ").append(capabilities.getBrowserVersion()).append("\n")
                .append("Platform name : ").append(capabilities.getPlatformName()).append("\n")
                .append("Local date and time: ").append(getDateAndTime());
    }

    @Attachment(value = "Failed test screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        ScreenshotMaker screenshotMaker = new ScreenshotMaker(driver);
        return screenshotMaker.takeScreenshotAsBytes();
    }
}
