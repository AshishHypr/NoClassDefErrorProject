package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends ScreenObject {

    @FindBy(how = How.XPATH, using = "//div[@id='outer-wrapper']/header/div/h1")
    public static WebElement mozillaHomeHeader;

    public HomePage(RemoteWebDriver webDriver) {
        super(webDriver);
    }
}
