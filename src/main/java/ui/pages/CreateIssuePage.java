package ui.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CreateIssuePage extends BasePage{
    private DashboardPage dashboardPage;
    @FindBy(id = "create-issue-dialog")
    private WebElement createIssueDialog;
    @FindBy(xpath = "//*[contains(@class,'aui-message-success')]")
    private WebElement successPopUp;
    @FindBy(xpath = "//a[@class='issue-created-key issue-link']")
    private WebElement newIssueLinkOnSuccessPopup;
    @FindBy(id = "project-field")
    private WebElement fieldProjectLocator;
    @FindBy(id = "issuetype-field")
    private WebElement issueTypeLocator;
    @FindBy(id = "summary")
    private WebElement summaryLocator;
    @FindBy(id = "description")
    private WebElement descriptionFieldLocator;
    @FindBy(xpath = "//ul[@class='tabs-menu']/li[@data-mode='source']")
    private WebElement descriptionTextModeTab;
    @FindBy(id = "priority-field")
    private WebElement priorityFieldDefault;
    @FindBy(id = "assign-to-me-trigger")
    private WebElement assignToMeButtonLocator;
    @FindBy(id = "create-issue-submit")
    private WebElement submitButtonLocator;


    public CreateIssuePage(){
        PageFactory.initElements(driver, this);
        dashboardPage = new DashboardPage();
    }

    public CreateIssuePage clickCreateAndWaitForDialog() {
        dashboardPage.clickCreateButton();
        waitToBePresent(createIssueDialog);
        return this;
    }

    public CreateIssuePage submitNewTicketAndOpenIt() {
        waitToBePresentAndClick(submitButtonLocator);
        waitToBePresent(successPopUp);
        waitToBePresentAndClick(newIssueLinkOnSuccessPopup);
        return this;
    }

    public CreateIssuePage enterProject(String  projectId) {
        waitToBePresentAndClick(fieldProjectLocator);
        fieldProjectLocator.clear();
        waitToBePresentAndSendKeys(fieldProjectLocator, projectId);
        fieldProjectLocator.sendKeys(Keys.TAB);
        return this;
    }

    public CreateIssuePage enterIssueTypeBug(String issueType) {
        waitToBePresentAndClick(issueTypeLocator);
        issueTypeLocator.clear();
        //issueTypeLocator.sendKeys(issueType);
        waitToBePresentAndSendKeys(issueTypeLocator, issueType);
        waitToBePresent(issueTypeLocator);
        issueTypeLocator.sendKeys(Keys.TAB);
        return this;
    }

    public CreateIssuePage enterIssueTypeStory(String issueType) {
        waitToBePresentAndClick(issueTypeLocator);
        issueTypeLocator.clear();
        issueTypeLocator.sendKeys(issueType);
        //waitToBePresentAndSendKeys(issueTypeLocator, issueType);
        issueTypeLocator.sendKeys(Keys.TAB);
        return this;
    }

    public CreateIssuePage fillSummary(String summary) {
        waitToBePresentAndClick(summaryLocator);
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
        waitToBePresentAndClick(priorityFieldDefault);
        priorityFieldDefault.clear();
        waitToBePresentAndSendKeys(priorityFieldDefault, priority);
        priorityFieldDefault.sendKeys(Keys.TAB);
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
