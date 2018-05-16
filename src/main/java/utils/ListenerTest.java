package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ui.pages.BasePage;
import ui.ui_utils.RemoteWebDriverFactory;
import ui.ui_utils.RemoteDriverManager;


import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ListenerTest implements ITestListener {

    final static Logger logger = Logger.getLogger(ListenerTest.class);
    final static PropertyReader propertyReader = new PropertyReader();
    public static Map<String, String> properties = propertyReader
            .readProperties("jira.properties");

    public void onTestStart(ITestResult iTestResult) {
        String testCaseName = iTestResult.getName();
        logger.info("TEST: " + testCaseName + " STARTED");
    }

    public void onTestSuccess(ITestResult iTestResult) {

        String testCaseName = iTestResult.getName();
        logger.info("TEST: " + testCaseName + " PASSED");
    }

    public void onTestFailure(ITestResult iTestResult) {
        logger.error("TEST: " + iTestResult.getName() + " FAILED");
        logger.error(iTestResult.getThrowable().fillInStackTrace());
    }

    public void onTestSkipped(ITestResult iTestResult) {
        logger.info("TEST: " + iTestResult.getName() + " SKIPPED");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

        // Invoked after the test class is instantiated and before any configuration method is called.
        String[] groups = iTestContext.getIncludedGroups();
        for (String group : groups) {
            if (group.contains("UI")) {
                String browserName = iTestContext.getCurrentXmlTest().getParameter("browserName");
                String implicitWaitInSeconds = iTestContext.getCurrentXmlTest().getParameter("implicitWaitInSeconds");
                WebDriver driver = RemoteWebDriverFactory.createInstance(browserName);
                RemoteDriverManager.setWebDriver(driver);
                logger.info("STARTED on browserName=" + browserName);
                changeImplicitWaitValue(driver, Integer.parseInt(implicitWaitInSeconds));
            }
        }
    }

    public void onFinish(ITestContext iTestContext) {
        // Invoked after all the tests have run and all their Configuration methods have been called.
        WebDriver driver = RemoteDriverManager.getDriver();

        if (driver != null) {
            changeImplicitWaitValue(driver, BasePage.defaultImplicitWaitInSeconds);
            logger.info("Closing browser window");
            RemoteDriverManager.closeDriver();
        }
    }


    private void changeImplicitWaitValue(WebDriver driver, int implicitWaitValueInSeconds) {
        driver.manage().timeouts().implicitlyWait(implicitWaitValueInSeconds, TimeUnit.SECONDS);
        logger.info("IMPLICIT WAIT WAS CHANGED TO: " + implicitWaitValueInSeconds);
    }
}
