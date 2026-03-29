package utils;

import io.appium.java_client.android.AndroidDriver;
import screens.HomeScreen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class Base {

    protected AppiumDriverFactory appiumDriverFactory;
    protected Properties config;

    public HomeScreen homeScreen;
        public Base() throws FileNotFoundException {
            config = new Properties();
            FileInputStream configFile = new FileInputStream("src/test/resources/properties/nativeAndroid.properties");
            try {
                config.load(configFile);
            } catch (Exception e) {
                throw new RuntimeException("Failed to load configuration properties", e);
            }
            // Initialize the AppiumDriverFactory with the desired app package and activity
            appiumDriverFactory = AppiumDriverFactory.getInstance(config);
            // Initialize the HomeScreen page object using the AppiumDriver from the factory
            homeScreen = new HomeScreen((AndroidDriver) AppiumDriverFactory.getDriver());
        }

        public void tearDown() {
            if (AppiumDriverFactory.getDriver() != null) {
                AppiumDriverFactory.getDriver().quit();
            }
        }
}
