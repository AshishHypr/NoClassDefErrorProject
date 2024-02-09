package mobile.screens;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class ScreenObject {

    public ScreenObject(RemoteWebDriver mobileDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(mobileDriver), this);
    }
}
