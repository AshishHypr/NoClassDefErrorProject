package org.automation.com.driver.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class will create respective Mobile Driver based on platform passed in maven command parameter: mobilePlatform
 */
public class MobileDriverFactory {
    private final Logger log = LogManager.getLogger(this.getClass().getName());
    String mobilePlatform = System.getProperty("mobilePlatform");

    public RemoteWebDriver createdDriver() throws MalformedURLException {
        String mobileAppPath = getMobileAppsPath(mobilePlatform);
        assert mobileAppPath != null : "App is not available.";
        return switch (mobilePlatform.toUpperCase()) {
            case "ANDROID" -> getAndroidDriver(mobileAppPath);
            case "IOS" -> getIosDriver(mobileAppPath);
            default -> throw new IllegalArgumentException(mobilePlatform + " mobile platform is not implemented yet.");
        };
    }

    private RemoteWebDriver getAndroidDriver(String app) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platform", "Android");
        desiredCapabilities.setCapability("appium:automation", "uiautomator2");
        desiredCapabilities.setCapability("appium:app", app);
        log.info("Capabilities: " + desiredCapabilities.toJson());
        return new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
    }

    private RemoteWebDriver getIosDriver(String app) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platform", "iOS");
        desiredCapabilities.setCapability("appium:automation", "XCUITEST");
        desiredCapabilities.setCapability("appium:app", app);
        log.info("Capabilities: " + desiredCapabilities.toJson());
        return new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
    }

    private String getMobileAppsPath(String mobilePlatform) {
        if (mobilePlatform.equalsIgnoreCase("Android"))
            return System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "apps" + File.separator + "ApiDemos-debug.apk";
        else if (mobilePlatform.equalsIgnoreCase("iOS"))
            return System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "apps" + File.separator + "YourApp.ipa";
        else
            return null;
    }
}
