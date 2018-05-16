package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class CreateIssuePage extends BasePage{
    private DashboardPage dashboardPage;

    private By createIssueDialog = By.id("create-issue-dialog");
    private By successPopUp = By.xpath("//*[contains(@class,'aui-message-success')]");
    private By newIssueLinkOnSuccessPopup = By.xpath("//a[@class='issue-created-key issue-link']");
    private By fieldProjectLocator = By.id("project-field");
    private By issueTypeLocator = By.id("issuetype-field");
    private By summaryLocator = By.id("summary");
    private By descriptionFieldLocator = By.id("description");
    private By descriptionTextModeTab = By.xpath("//ul[@class='tabs-menu']/li[@data-mode='source']");
    private By priorityFieldDefault = By.id("priority-field");
    private By assignToMeButtonLocator = By.id("assign-to-me-trigger");
    private By submitButtonLocator = By.id("create-issue-submit");


    public CreateIssuePage(){
       // this.driver = RemoteDriverManager.getDriver();
        dashboardPage = new DashboardPage();
    }

    public CreateIssuePage clickCreateAndWaitForDialog() {
        dashboardPage.clickCreateButton();
        waitToBePresent(createIssueDialog);
        return this;
    }

    public CreateIssuePage submitNewTicketAndOpenIt() {
        waitTillBeAbleToClick(submitButtonLocator);
        waitToBePresent(successPopUp);
        waitToBePresentAndClick(newIssueLinkOnSuccessPopup);
        return this;
    }

    public CreateIssuePage enterProject(String projectId) {
        waitTillBeAbleToClick(fieldProjectLocator);
        driver.findElement(fieldProjectLocator).clear();
        waitToBePresentAndSendKeys(fieldProjectLocator, projectId);
        driver.findElement(fieldProjectLocator).sendKeys(Keys.TAB);
        return this;
    }

    public CreateIssuePage enterIssueType(String issueType) {
        waitTillBeAbleToClick(issueTypeLocator);
        driver.findElement(issueTypeLocator).clear();
        waitToBePresentAndSendKeys(issueTypeLocator, issueType);
        driver.findElement(issueTypeLocator).sendKeys(Keys.TAB);
        return this;
    }

    public CreateIssuePage fillSummary(String summary) {
        waitTillBeAbleToClick(summaryLocator);
        waitToBePresentAndSendKeys(summaryLocator, summary);
        return this;
    }

    public CreateIssuePage switchDescriptionToTextMode(){
        waitToBePresentAndClick(descriptionTextModeTab);
        return this;
    }

    public CreateIssuePage fillDescription(String issueDescription) {
        waitToBePresentAndSendKeys(descriptionFieldLocator, issueDescription);
        return this;
    }

    public CreateIssuePage selectPriority(String priority) {
        waitTillBeAbleToClick(priorityFieldDefault);
        driver.findElement(priorityFieldDefault).clear();
        waitToBePresentAndSendKeys(priorityFieldDefault, priority);
        driver.findElement(priorityFieldDefault).sendKeys(Keys.TAB);
        return this;
    }

    public CreateIssuePage clickAssignToMeButton() {
        waitToBePresentAndClick(assignToMeButtonLocator);
        return this;
    }

    public CreateIssuePage clickSubmitButton() {
        waitToBePresentAndClick(submitButtonLocator);
        return this;
    }

}
