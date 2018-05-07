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
    protected String baseURL = ListenerTest.properties.get("JiraUrl");
    protected String username = ListenerTest.properties.get("username");
    protected String password = ListenerTest.properties.get("password");


   protected BasePage() {
        this.driver = RemoteDriverManager.getDriver();
    }

    protected void waitToBePresent(By locator) {
        logger.info("WAIT ELEMENT TO BE PRESENT: " + locator);
        (new WebDriverWait(driver, defaultExplicitWaitInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitToBePresentAndClick(By locator) {
        logger.info("WAIT ELEMENT TO BE PRESENT AND CLICK: " + locator);
        WebElement element = null;

        try {
            element = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                    until(ExpectedConditions.presenceOfElementLocated(locator));
            element.click();
        } catch (StaleElementReferenceException ignored) {
            element = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                    until(ExpectedConditions.presenceOfElementLocated(locator));
            element.click();
        }

    }
    protected void waitToBePresentAndSendSubmit(By locator) {
        logger.info("WAIT ELEMENT TO BE PRESENT AND SUBMIT: " + locator);
        WebElement element = null;

        try {
            element = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                    until(ExpectedConditions.presenceOfElementLocated(locator));
            element.submit();
        } catch (StaleElementReferenceException ignored) {
            element = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                    until(ExpectedConditions.presenceOfElementLocated(locator));
            element.submit();
        }

    }
    protected void waitTillBeAbleToClick(By locator) {
        logger.info("WAIT ELEMENT TO BE CLICKABLE: " + locator);

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

    protected void waitToBePresentAndSendKeys(By locator, String keys) {
        logger.info("WAIT ELEMENT TO BE PRESENT AND SEND KEYS: SEND " + keys + " TO " + locator);

        WebElement element = null;

        try {
            element = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                    until(ExpectedConditions.presenceOfElementLocated(locator));
            element.sendKeys(keys);
        } catch (StaleElementReferenceException ignored) {
            element = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                    until(ExpectedConditions.presenceOfElementLocated(locator));
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

    protected void SelectDropDownItem(By dropDownSelector, By itemSelector) {
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

    protected boolean waitToBePresentAndContainsText(By locator, String text) {
        logger.info("WAIT ELEMENT TO BE PRESENT AND CONTAINS TEXT: " + locator);
        WebElement element = null;

        try {
            element = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                    until(ExpectedConditions.presenceOfElementLocated(locator));

            String result = element.getText();

            if (result.contains(text)) {
                return true;
            } else {
                logger.info("!---FAILED---! THE ELEMENT DOESN'T CONTAIN TEXT: " + locator);
                return false;
            }

        } catch (StaleElementReferenceException ignored) {
            element = (new WebDriverWait(driver, defaultExplicitWaitInSeconds)).
                    until(ExpectedConditions.presenceOfElementLocated(locator));
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
