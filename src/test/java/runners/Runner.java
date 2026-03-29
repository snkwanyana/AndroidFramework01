package runners;

import org.testng.annotations.Test;
import utils.Base;
import utils.Screenshots;

public class Runner extends Base {

    /** The Runner class extends the Base class, which provides common setup and utility methods for the test cases.
     * The constructor of the Runner class calls the superclass constructor to ensure that the necessary setup is performed before executing any test methods. */
    public Runner() throws Exception {
        super();
    }

    @Test
    public void homeScreenTest() {
        homeScreen.verifyHomeScreenDisplayed();
        Screenshots.captureScreenshot(appiumDriverFactory.getDriver(), "homeScreenTest");
        homeScreen.clickHomeBurgerMenu();
        Screenshots.captureScreenshot(appiumDriverFactory.getDriver(), "homeBurgerMenuClicked");

    }
}
