package utils;

import constants.GlobalParams;
import constants.PropertyConfigs;
import exceptions.TestException;
import helpers.LogHelper;
import helpers.MobileDevice;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class WebDriverSetup {

    private static final ThreadLocal<AppiumDriverLocalService> services = ThreadLocal.withInitial(() -> null);
    private static final String MOBILE_APPIUM_URL = "http://127.0.0.1:4723/wd/hub/";

    public static WebDriver setupWebDriver(String driverType) throws MalformedURLException {
        WebDriver driver;
        String appiumUrl = GlobalParams.IS_FROM_MAVEN ? startAppiumServer() : MOBILE_APPIUM_URL;
        switch (driverType) {
            case PropertyConfigs.IOS:
                driver = new IOSDriver<>(new URL(appiumUrl), getIOSCapabilities());
                break;
            case PropertyConfigs.ANDROID:
                driver = new AndroidDriver<>(new URL(appiumUrl), getAndroidCapabilities());
                break;
            default:
                throw new TestException("Invalid driver type: " + driverType);
        }
        return driver;
    }

    private static DesiredCapabilities getIOSCapabilities() {
        MobileDevice device = MobileDeviceUtil.getMobileDevice();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, device.platformName);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.platformVersion);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, device.name);
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        if (GlobalParams.IS_FROM_MAVEN) {
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
        } else {
            //for debug, to keep session active. Otherwise Appium session will be disconnected after timeout
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 0);
        }
        caps.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
        caps.setCapability(IOSMobileCapabilityType.BUNDLE_ID, ConfigReader.getInstance().getValue(PropertyConfigs.APP_PACKAGE));
        caps.setCapability(MobileCapabilityType.NO_RESET, false);
        caps.setCapability(IOSMobileCapabilityType.RESET_ON_SESSION_START_ONLY, false);
        caps.setCapability(IOSMobileCapabilityType.START_IWDP, startIWDP());
        caps.setCapability(IOSMobileCapabilityType.SHOW_XCODE_LOG, false);
        if (device.isRealDevice) {
            if (!isTcpPortAvailable(8100)) {
                caps.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, getFreePort());
            }
            caps.setCapability(MobileCapabilityType.UDID, device.udid);
        }
        return caps;
    }

    private static DesiredCapabilities getAndroidCapabilities() {
        MobileDevice device = MobileDeviceUtil.getMobileDevice();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, ConfigReader.getInstance().getValue(PropertyConfigs.APP_PACKAGE));
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ConfigReader.getInstance().getValue(PropertyConfigs.APP_ACTIVITY));
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, device.platformName);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.platformVersion);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, device.name);
        if (device.isRealDevice) {
            caps.setCapability(MobileCapabilityType.UDID, device.udid);
        }
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 0);
        caps.setCapability(AndroidMobileCapabilityType.NATIVE_WEB_SCREENSHOT, true);
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        caps.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
        caps.setCapability(MobileCapabilityType.NO_RESET, false);
        caps.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
        caps.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, false);
        caps.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/app_build/com.monefy.app.lite_2021-02-01.apk");
        return caps;
    }

    private static String startAppiumServer() {
        String appiumServiceUrl;
        if (services.get() == null) {
            if (WebDriverUtil.isIOS) {
                stopIWDP();
            }

            AppiumServiceBuilder builder;

            // Build the Appium Service
            LogHelper.getLogger().info("Building and starting the server:");
            builder = new AppiumServiceBuilder();
            builder.withIPAddress(ConfigReader.getInstance().getValue(PropertyConfigs.MOBILE_APPIUM_IP));
            builder.usingAnyFreePort();
            builder.withArgument(GeneralServerFlag.LOG_LEVEL, "warn");
            builder.withArgument(GeneralServerFlag.RELAXED_SECURITY);
            builder.withArgument(() -> "--allow-insecure", "chromedriver_autodownload");

            // Start the server with the builder
            AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
            service.start();
            services.set(service);

            appiumServiceUrl = service.getUrl().toString();
            LogHelper.getLogger().info("Server started with URL - " + appiumServiceUrl);
        } else {
            appiumServiceUrl = services.get().getUrl().toString();
        }
        return appiumServiceUrl;
    }

    public static void stopAppiumServer() {
        try {
            LogHelper.getLogger().info("Trying to stop the server...");
            services.get().stop();
            stopIWDP();
            LogHelper.getLogger().info("Success, Server stopped.");
        } catch (Exception e) {
            LogHelper.getLogger().error("Appium server could not be stopped.");
        }
    }

    private static Integer getFreePort() {
        int freePort = 8200;
        try {
            ServerSocket s = new ServerSocket(0);
            freePort = s.getLocalPort();
            s.close();
        } catch (IOException e) {
            LogHelper.getLogger().error("Unale to generate free port, using default 8200");
        }
        return freePort;
    }

    private static boolean startIWDP() {
        boolean startIWDP = true;
        String[] getIWDP = new String[]{"/bin/bash", "-c", "lsof -t -i tcp:27753"};
        try {
            Process process = Runtime.getRuntime().exec(getIWDP);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s = reader.readLine();
            if (s != null) {
                startIWDP = false;
            }
        } catch (IOException e) {
            throw new TestException("Unable to get IWDP status");
        }
        return startIWDP;
    }

    private static void stopIWDP() {
        String[] killIWDP = new String[]{"/bin/bash", "-c", "pkill iproxy | pkill ios_webkit_debug_proxy"};
        try {
            Runtime.getRuntime().exec(killIWDP);
        } catch (IOException e) {
            throw new TestException("Unable to stop IWDP");
        }
    }

    private static boolean isTcpPortAvailable(int port) {
        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.setReuseAddress(false);
            serverSocket.bind(new InetSocketAddress(InetAddress.getByName("localhost"), port), 1);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
