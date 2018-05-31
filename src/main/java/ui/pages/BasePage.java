package ui.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.ui_utils.RemoteDriverManager;
import utils.ListenerTest;

public class BasePage {
    protected WebDriver driver;
    final static Logger logger = Logger.getLogger(BasePage.class);
    private int defaultExplicitWaitInSeconds = 10;
    public static int defaultImplicitWaitInSeconds = 10;
    protected String JiraURL = ListenerTest.properties.get("JiraUrl");
    protected String username = ListenerTest.properties.get("username");
    protected String password = ListenerTest.properties.get("password");


   protected BasePage() {
        this.driver = RemoteDriverManager.getDriver();
    }


    protected void waitToBePresent(WebElement locator) {
        logger.info("WAIT ELEMENT TO BE PRESENT: " + locator);
        (new WebDriverWait(driver, defaultExplicitWaitInSeconds))
                .until(ExpectedConditions.visibilityOf(locator));
    }

    protected void waitToBePresentAndClick(WebElement locator) {
        logger.info("WAIT ELEMENT TO BE PRESENT AND CLICK: " + locator);
        WebElement element = null;

        try {
            element = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                    until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (StaleElementReferenceException ignored) {
            element = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                    until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        }

    }

     protected void waitToBePresentAndSendSubmit(WebElement locator) {
        logger.info("WAIT ELEMENT TO BE PRESENT AND SUBMIT: " + locator);
         WebElement element;

         try {
             element = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                     until(ExpectedConditions.elementToBeClickable(locator));
             element.submit();
         } catch (StaleElementReferenceException ignored) {
             element = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                     until(ExpectedConditions.elementToBeClickable(locator));
             element.submit();
         }
     }

    protected void waitToBePresentAndSendKeys(WebElement locator, String keys) {
        logger.info("WAIT ELEMENT TO BE PRESENT AND SEND KEYS: SEND " + keys + " TO " + locator);

        WebElement element;

        try {
            element = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                    until(ExpectedConditions.elementToBeClickable(locator));
            element.sendKeys(keys);
        } catch (StaleElementReferenceException ignored) {
            element = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                    until(ExpectedConditions.elementToBeClickable(locator));
            element.sendKeys(keys);
        }
    }

    protected boolean isOnThePage(String expectedURL) {
        boolean result;
        result = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                until(ExpectedConditions.urlToBe(expectedURL));

        if (result == true) {
            logger.info("IS ON THE PAGE: " + expectedURL);
        } else {
            logger.info("YOU ARE NOT ON THE PAGE: " + expectedURL);
            logger.info("YOU ARE ON THE PAGE: " + driver.getCurrentUrl());
        }
        return result;

    }

    protected void SelectDropDownItem(WebElement dropDownSelector, WebElement itemSelector) {
        waitToBePresentAndClick(dropDownSelector);
        waitToBePresentAndClick(itemSelector);
    }

    protected boolean isElementPresent(By locator) {
        logger.info("WAIT ELEMENT TO BE PRESENT: " + locator);
        try {
            (new WebDriverWait(driver, defaultExplicitWaitInSeconds))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean waitToBePresentAndContainsText(WebElement locator, String text) {
        logger.info("WAIT ELEMENT TO BE PRESENT AND CONTAINS TEXT: " + locator);
        WebElement element = null;

        try {
            element = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                    until(ExpectedConditions.elementToBeClickable(locator));

            String result = element.getText();

            // TODO doe we really need to check text if we alredy found element using xpath and contains?

            if (result.contains(text)) {
                return true;
            } else {
                logger.info("!---FAILED---! THE ELEMENT DOESN'T CONTAIN TEXT: " + locator);
                return false;
            }

        } catch (StaleElementReferenceException ignored) {
            element = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                    until(ExpectedConditions.elementToBeClickable(locator));
            String result = element.getText();

            if (result.contains(text)) {
                return true;
            } else {
                logger.info("!---FAILED---! THE ELEMENT DOESN'T CONTAIN TEXT: " + locator);
                return false;
            }
        }

    }
}
