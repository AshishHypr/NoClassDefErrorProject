package web.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import web.pages.HomePage;
import web.pages.LoginPage;
import web.support.WebHooks;

import java.lang.reflect.Field;

public class WebElementSteps {
    private final RemoteWebDriver webDriver;
    private final Logger log = LogManager.getLogger(this.getClass().getName());

    public WebElementSteps(WebHooks webHooks) {
        this.webDriver = webHooks.getWebDriver();
        new LoginPage(webHooks.getWebDriver());
        new HomePage(webHooks.getWebDriver());
    }

    private WebElement getWebElement(String elementName) throws Exception {
        Field field;
        try {
            field = WebHooks.screenClassObject.getDeclaredField(elementName);
        } catch (NoSuchFieldException e) {
            log.info(e.getMessage(), e);
            throw new Exception(elementName + " element not found.");
        }
        field.setAccessible(true);
        return (WebElement) field.get(null);
    }

    @Given("I am on {string} web page")
    public void iAmOnScreen(String screenName) throws Exception {
        WebHooks.screen = screenName;
        log.info("Activating " + screenName);

        Class<?> screenClassObject;
        try {
            screenClassObject = Class.forName(WebHooks.elementDirectory + screenName);
        } catch (ClassNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new Exception(screenName + " not found");
        }
        WebHooks.screenClassObject = screenClassObject;
    }

    @Then("I click {string} web element")
    public void iClickWebElement(String elementName) throws Exception{
        log.info("Going to click" + elementName + " on " + WebHooks.screen + " screen");
        Thread.sleep(5000);
        if (getWebElement(elementName).isDisplayed())
            getWebElement(elementName).click();
        log.info("Clicked " + elementName + " successfully.");
    }

    @Then("I enter {string} text in {string} web element")
    public void iEnterInWebElement(String text, String elementName) throws Exception {
        log.info("Going to enter text in " + elementName + " on " + WebHooks.screen + " screen");
        Thread.sleep(5000);
        if (getWebElement(elementName).isDisplayed()) {
            getWebElement(elementName).sendKeys(text);
        }
        log.info("Entered text in  " + elementName + " successfully.");
    }

    @Then("I verify that {string} text is displayed in {string} web element")
    public void iVerifyThatTextIsDisplayedInWebElement(String text, String elementName) throws Exception {
        log.info("Going to validate text in " + elementName + " on " + WebHooks.screen + " screen");
        Thread.sleep(5000);
        String fetchedText = null;
        if (getWebElement(elementName).isDisplayed()) {
            fetchedText = getWebElement(elementName).getText();
            log.info("Fetched Text: " + fetchedText);
        }
        Assertions.assertEquals(fetchedText, text, "Not matched.");
        log.info("Entered text in  " + elementName + " successfully.");
    }

}
