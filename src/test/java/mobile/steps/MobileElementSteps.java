package mobile.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import mobile.screens.LoginScreen;
import mobile.support.MobileHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.Field;

public class MobileElementSteps {
    private final RemoteWebDriver mobileDriver;
    private final Logger log = LogManager.getLogger(this.getClass().getName());

    public MobileElementSteps(MobileHooks mobileHooks) {
        this.mobileDriver = mobileHooks.getMobileDriver();
        new LoginScreen(mobileHooks.getMobileDriver());
    }

    private WebElement getMobileElement(String elementName) throws Exception {
        Field field;
        try {
            field = MobileHooks.screenClassObject.getDeclaredField(elementName);
        } catch (NoSuchFieldException e) {
            log.info(e.getMessage(), e);
            throw new Exception(elementName + " element not found.");
        }
        field.setAccessible(true);
        return (WebElement) field.get(null);
    }

    @Given("I am on {string} screen")
    public void iAmOnScreen(String screenName) throws Exception {
        MobileHooks.screen = screenName;
        log.info("Activating " + screenName);
        System.out.println("I am here");

        Class<?> screenClassObject;
        try {
            screenClassObject = Class.forName(MobileHooks.elementDirectory + screenName);
        } catch (ClassNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new Exception(screenName + " not found");
        }
        MobileHooks.screenClassObject = screenClassObject;
    }

    @Then("I click {string} mobile element")
    public void iClickMobileElement(String elementName) throws Exception{
        log.info("Going to click" + elementName + " on " + MobileHooks.screen + " screen");
        if (getMobileElement(elementName).isDisplayed())
            getMobileElement(elementName).click();
        log.info("Clicked " + elementName + " successfully.");
    }

}
