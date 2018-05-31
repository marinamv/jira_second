package ui.ui_utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import utils.ListenerTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class RemoteWebDriverFactory {

    final static Logger logger = Logger.getLogger(ListenerTest.class);
    protected String GridURL = ListenerTest.properties.get("GridURL");

    public  WebDriver createInstance(String browserName, boolean isUseGrid){
        logger.info("CREATING browser instance - " + browserName);
        //TODO move hardcoded url to property
        WebDriver driver = null;

        URL hostURL = null;

        try {
            hostURL = new URL(GridURL);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        switch (browserName) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("disable-infobars", "disable-extensions");

                if (isUseGrid) {

                    DesiredCapabilities capability = DesiredCapabilities.chrome();
                    capability.setBrowserName(BrowserType.CHROME);
                    capability.setPlatform(Platform.LINUX);
                    driver = new RemoteWebDriver(hostURL, capability);
                } else {
                    driver = new ChromeDriver(chromeOptions);
                }
                break;

            case "firefox":

                if (isUseGrid) {
                    DesiredCapabilities capability2 = DesiredCapabilities.firefox();
                    capability2.setBrowserName(BrowserType.FIREFOX);
                    capability2.setPlatform(Platform.LINUX);
                    capability2.setCapability("marionette", true);
                    driver = new RemoteWebDriver(hostURL, capability2);
                } else {
                    driver = new FirefoxDriver();
                }
                break;

            case "safari":
                if (isUseGrid) {
                    DesiredCapabilities capability3 = DesiredCapabilities.safari();
                    capability3.setBrowserName(BrowserType.SAFARI);
                    capability3.setPlatform(Platform.MAC);
                    driver = new RemoteWebDriver(hostURL, capability3);
                }else{
                    driver = new SafariDriver();
                }
                break;
                }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
     }

}
