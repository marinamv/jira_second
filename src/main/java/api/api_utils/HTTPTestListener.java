package api.api_utils;

import api.Authorization;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.PropertyReader;

import java.util.Map;

public class HTTPTestListener implements ITestListener {
    final static PropertyReader propertyReader = new PropertyReader();
    public static Map<String, String> propertiesJira = propertyReader
            .readProperties("jira.properties");
    static final Logger logger = Logger.getLogger(HTTPTestListener.class);

    public void onTestStart(ITestResult iTestResult) {
        logger.info("===== '" + iTestResult.getName()+ "' test started =====");
    }

    public void onTestSuccess(ITestResult iTestResult) {

    }

    public void onTestFailure(ITestResult iTestResult) {

    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {
        Authorization.loginToJIRA();
    }

    public void onFinish(ITestContext iTestContext) {

    }
}
