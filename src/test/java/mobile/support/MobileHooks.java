package mobile.support;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.com.driver.driver.MobileDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

public class MobileHooks {
    public static Class<?> screenClassObject;
    public static String screen;
    public static String elementDirectory = "mobile.screens.";

    private RemoteWebDriver mobileDriver;
    private final Logger log = LogManager.getLogger(this.getClass().getName());

    public RemoteWebDriver getMobileDriver() {
        return mobileDriver;
    }

    @Before
    public void before(Scenario scenario) {
        MobileDriverFactory mobileDriverFactory = new MobileDriverFactory();
        try {
            mobileDriver = mobileDriverFactory.createdDriver();
            log.info("Driver creation successful.");
        } catch (MalformedURLException e) {
            log.info(e.getMessage(), e);
            log.info("Unable to create mobile driver");
        }
    }

    @After
    public void after(Scenario scenario) {
        mobileDriver.quit();
    }
}
