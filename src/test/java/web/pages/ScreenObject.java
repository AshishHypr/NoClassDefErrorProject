package web.pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class ScreenObject {

    public ScreenObject(RemoteWebDriver mobileDriver) {
        PageFactory.initElements(mobileDriver, this);
    }
}
