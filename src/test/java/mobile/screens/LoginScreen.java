package mobile.screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginScreen extends ScreenObject {

    @AndroidFindBy(id = "lets_begin_button")
    @iOSXCUITFindBy(id = "lets_begin_button")
    public static WebElement letsBeginButton;

    public LoginScreen(RemoteWebDriver mobileDriver) {
        super(mobileDriver);
    }
}
