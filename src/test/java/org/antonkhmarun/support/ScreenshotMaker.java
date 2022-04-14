package org.antonkhmarun.support;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotMaker {

    WebDriver driver;

    public ScreenshotMaker(WebDriver driver) {
        this.driver = driver;
    }

    public byte[] takeScreenshotAsBytes() {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        return scrShot.getScreenshotAs(OutputType.BYTES);
    }
}
