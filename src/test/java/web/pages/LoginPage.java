package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends ScreenObject {

    @FindBy(how = How.ID, using = "lets_begin_button")
    public static WebElement letsBeginButton;

    public LoginPage(RemoteWebDriver webDriver) {
        super(webDriver);
    }
}
