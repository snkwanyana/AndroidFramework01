package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AppiumDriverFactory {

    protected static AppiumDriver driver;
    WebDriverWait wait;
    protected static AppiumDriverFactory instance;

    public AppiumDriverFactory(Properties properties) {
        /** This method can be used to initialize the AppiumDriver with desired capabilities and launch the app.
         * The actual implementation will depend on the specific requirements of your test cases and the app you are testing.
         * You can set the desired capabilities, such as platform name, automation name, app package, and app activity,
         * and then create an instance of AppiumDriver to start the session. */
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(properties.getProperty("platforName"));
        options.setAutomationName(properties.getProperty("automationName"));
        options.setAppPackage(properties.getProperty("appPackage"));
        options.setAppActivity(properties.getProperty("appActivity"));

        /** Initialize the AndroidDriver with the specified options and handle any potential exceptions */
        try {
            driver = new AndroidDriver(new URL(properties.getProperty("appiumServerURL")), options);
        } catch (MalformedURLException e) {
            // Log the error and throw a runtime exception
            throw new RuntimeException("Failed to initialize AppiumDriver", e);
        }

        if ("chrome".equalsIgnoreCase(properties.getProperty("appType"))) {
            options.autoGrantPermissions();
            String mobileWebURL = properties.getProperty("webUrl");
            driver.get(mobileWebURL);
        }
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    /**
     * This method implements the singleton pattern to ensure that only one instance of AppiumDriverFactory is created during the test execution.
     */
    public static AppiumDriverFactory getInstance(Properties config) {
        if (instance == null) {
            instance = new AppiumDriverFactory(config);
        }
        return instance;
    }

    /**
     * This method provides access to the AppiumDriver instance, allowing other classes to interact with the driver and perform actions on the app.
     */
    public static AppiumDriver getDriver() {
        return driver;
    }

    /**
     * This method is responsible for quitting the AppiumDriver session and cleaning up resources after the tests are completed.
     */
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            instance = null; // Reset the instance to allow for a new session
        }
    }
}
