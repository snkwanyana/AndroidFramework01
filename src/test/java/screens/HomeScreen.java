package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeScreen {

    protected AndroidDriver driver;

    @FindBy(xpath = "//android.widget.Button")
    WebElement homeBurgerMenuNative;

    public HomeScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyHomeScreenDisplayed() {
        homeBurgerMenuNative.isDisplayed();
    }

    public void clickHomeBurgerMenu() {
        homeBurgerMenuNative.click();
    }
}
