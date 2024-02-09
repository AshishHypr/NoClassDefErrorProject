package org.automation.com.driver.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * This class will create respective Web Driver based on browser name passed in maven command parameter: webBrowser
 */
public class WebDriverFactory {

    String webBrowser = System.getProperty("webBrowser");

    public RemoteWebDriver createdWebDriver() {
        switch (webBrowser) {
            case "Chrome":
                return getChromeDriver();
            case "Firefox" :
                return getFirefoxDriver();
            default:
                throw new IllegalArgumentException(webBrowser + " browser is not implemented yet.");
        }
    }

    private RemoteWebDriver getChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
    }

    private RemoteWebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
