package mobileBasic;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class StartingUpMobileApp {

    protected AppiumDriver driver;

    @Test
    public void launchApp() throws MalformedURLException {
        /** The following capabilities are from AppiumOptions class, which is the new way to set capabilities in Appium 8.x. However,
         * since the AndroidDriver constructor still accepts DesiredCapabilities, we can use either approach.
         * The UiAutomator2Options is a subclass of AppiumOptions and provides a more fluent API for setting Android-specific capabilities.
         * The DesiredCapabilities is the older way to set capabilities and is still supported for backward compatibility. In this example,
         * we will use both approaches to demonstrate how to set capabilities for launching an Android app with Appium. */
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.ndosi.automation.qa");
        options.setAppActivity("com.ndosi.automation.MainActivity");
        /** =========The following capabilities are from Selenium romote class========= */
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", System.getProperty("user.dir") + "/src/test/resources/apps/app-qa-release.apk");

        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/"), caps);

    }
}
