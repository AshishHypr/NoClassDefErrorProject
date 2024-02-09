package web.support;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.com.driver.driver.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebHooks {
    public static Class<?> screenClassObject;
    public static String screen;
    public static String elementDirectory = "web.pages.";

    private RemoteWebDriver webDriver;
    private final Logger log = LogManager.getLogger(this.getClass().getName());

    public RemoteWebDriver getWebDriver() {
        return webDriver;
    }

    @Before
    public void before(Scenario scenario) {
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        try {
            webDriver = webDriverFactory.createdWebDriver();
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            log.info("Unable to create web driver");
        }
        webDriver.manage().window().maximize();
        webDriver.get("https://www.mozilla.org/en-US/");
    }

    @After
    public void after(Scenario scenario) {
        webDriver.quit();
    }
}
