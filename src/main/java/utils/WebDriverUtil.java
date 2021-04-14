package utils;

import constants.GlobalParams;
import constants.PropertyConfigs;
import helpers.DateHelper;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class WebDriverUtil {
    private static final ThreadLocal<WebDriver> drivers = ThreadLocal.withInitial(() -> null);

    public static boolean isAndroid = false;
    public static boolean isIOS = false;

    static {
        switch (GlobalParams.SELENIUM_DRIVER) {
            case PropertyConfigs.IOS:
                isIOS = true;
                break;
            case PropertyConfigs.ANDROID:
                isAndroid = true;
        }
    }

    public static WebDriver getDriver() {
        return drivers.get();
    }

    public static MobileDriver getMobileDriver() {
        return (MobileDriver) getDriver();
    }

    public static AndroidDriver getAndroidDriver() {
        return (AndroidDriver) getMobileDriver();
    }

    public static IOSDriver getIOSDriver() {
        return (IOSDriver) getMobileDriver();
    }

    public static void setDriver(WebDriver driver) {
        drivers.set(driver);
    }

    public static void closeDriverSession() {
        closeApp();
        drivers.get().quit();
        drivers.set(null);
    }

    public static JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    public static WebElement getElement(By locator) {
        return getDriver().findElement(locator);
    }

    public static WebElement getElement(WebElement parent, By locator) {
        return parent.findElement(locator);
    }

    public static List<WebElement> getElements(By locator) {
        return getDriver().findElements(locator);
    }

    public static List<WebElement> getElements(WebElement parent, By locator) {
        return parent.findElements(locator);
    }

    public static String writeScreenshotToFile(byte[] screen, String nameTemplate, String path) {
        try {
            if (path == null) {
                path = "./target/screenshots/" + nameTemplate + "_" + DateHelper.getTodaysDateTime() + ".png";
            }
            FileUtils.writeByteArrayToFile(new File(path), screen);

        } catch (IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }

        return path;
    }

    public static byte[] getScreenshot() {
        return ((TakesScreenshot) getMobileDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void setImplicitWait() {
        getDriver().manage().timeouts().implicitlyWait(GlobalParams.WAIT_SELENIUM_IMPLICIT, TimeUnit.SECONDS);
    }

    public static List<WebElement> getElementsOrEmpty(WebElement parent, By locator) {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> temp = getElements(parent, locator);
        setImplicitWait();
        return temp;
    }

    public static List<WebElement> getElementsOrEmpty(By locator) {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> temp = getElements(locator);
        setImplicitWait();
        return temp;
    }

    public static void click(WebElement element) {
        element.click();
    }

    public static String getValue(WebElement element) {
        return element.getText().trim();
    }

    public static List<String> getStringListFromWebElementsList(List<WebElement> elements) {
        return elements.stream().map(WebElement::getText).map(String::trim).collect(Collectors.toList());
    }

    public static void lockDevice() {
        if (isIOS) getIOSDriver().lockDevice();
        else getAndroidDriver().lockDevice();
    }

    public static void unlockDevice() {
        if (isIOS) getIOSDriver().unlockDevice();
        else getAndroidDriver().unlockDevice();
    }

    public static void back() {
        getMobileDriver().navigate().back();
    }

    public static void launchApp() {
        getMobileDriver().launchApp();
    }

    public static void closeApp() {
        getMobileDriver().closeApp();
    }
}
