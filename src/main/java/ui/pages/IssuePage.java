package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;


public class IssuePage extends BasePage {
    public IssuePage(){
        PageFactory.initElements(driver,this);
    }

    private By issueAssigneeField = By.id("issue_summary_assignee_" + username);
    private String pageURL = JiraURL + "/browse/%s";
    @FindBy(id = "project-name-val")
    private WebElement projectIdLocator;
    @FindBy(id = "summary-val")
    private WebElement issueSummaryLocator;
    @FindBy(id = "type-val")
    private WebElement issueTypeField;
    @FindBy(id = "priority-val")
    private WebElement issuePriorityField;
    @FindBy(id = "description-val")
    private WebElement issueDescriptionLocator;

    //---Attachment
    @FindBy(xpath = "//*[@duitype='dndattachment/dropzones/AttachmentsDropZone']//*[@class='issue-drop-zone__button']")
    private WebElement browseButton;
    private String fileName = "//*[@class='attachment-content js-file-attachment']//*[contains(text(),'%s')]";

    @FindBy(xpath = "//*[@class='trigger-label'][contains(text(), 'Bug opened')]")
    private WebElement btnBugOpened;
    @FindBy(xpath = "//*[@class='trigger-label'][contains(text(), 'Bug in progress')]")
    private WebElement btnBugInProgress;
    @FindBy(xpath = "//*[@class='trigger-label'][contains(text(), 'Bug fixed')]")
    private WebElement btnBugFixed;
    @FindBy(xpath = "//*[@class='trigger-label'][contains(text(), 'Bug in Testing')]")
    private WebElement btnBugInTesting;
    @FindBy(xpath = "//*[@class='trigger-label'][contains(text(), 'Bug reopened')]")
    private WebElement btnBugReopened;
    @FindBy(xpath = "//*[@class='trigger-label'][contains(text(), 'Bug closed')]")
    private WebElement btnBugClosed;

    //BugStatus
    @FindBy(xpath = "//*[@id='status-val']")
    public WebElement status;



    //StoryWflButtons
    @FindBy(xpath = "//*[@class='toolbar-item']//*[text()='Start Progress']")
    private WebElement btnStoryStartProgress;
    @FindBy(xpath = "//*[@class='toolbar-item']//*[text()='Resolve Issue']")
    private WebElement btnStoryResolveIssue;
    @FindBy(xpath = "//*[@class='toolbar-item']//*[text()='Reopen Issue']")
    private WebElement btnStoryReopenIssue;
    @FindBy(xpath = "//*[@class='toolbar-item']//*[text()='Close Issue']")
    private WebElement btnStoryCloseIssue;
    @FindBy(xpath = "//*[@class='toolbar-item']//*[text()='Stop Progress']")
    private WebElement btnStoryStopProgress;

    @FindBy(id = "resolution")
    private WebElement resolutionDropDownMenu;
    @FindBy(id = "issue-workflow-transition-submit")
    private WebElement getBtnStoryResolveIssue;

    public boolean isProjectIdCorrect(String project) {
        return waitToBePresentAndContainsText(projectIdLocator, project);
    }

    public boolean isIssueSummaryCorrect(String summary) {
        return waitToBePresentAndContainsText(issueSummaryLocator, summary);
    }

    public boolean isIssueTypeCorrect(String issueType) {
        return waitToBePresentAndContainsText(issueTypeField, issueType);
    }

    public boolean isIssuePriorityCorrect(String priority) {
        return waitToBePresentAndContainsText(issuePriorityField, priority);
    }

    public boolean isIssueDescriptionCorrect(String  description) {
        return waitToBePresentAndContainsText(issueDescriptionLocator, description);
    }

    public boolean isIssueAssigneeCorrect() {
        if (driver.findElement(issueAssigneeField) == null) {
            return false;
        }
        return true;
    }
    //TODO need to adaptive to @FindBy
    /*public boolean isAttachmentPresent(String file) {
        String selector = String.format(fileName, file);
        return waitToBePresentAndContainsText(selector, file);
    }*/

    public IssuePage clickBugOpenedBtn() {
        waitToBePresentAndClick(btnBugOpened);
        return this;
    }

    public IssuePage clickBugInProgressBtn() {
        waitToBePresentAndClick(btnBugInProgress);
        return this;
    }

    public IssuePage clickBugFixedBtn() {
        waitToBePresentAndClick(btnBugFixed);
        return this;
    }


    public IssuePage clickBugInTestingBtn() {
        waitToBePresentAndClick(btnBugInTesting);
        return this;
    }


    public IssuePage clickBugReopenedBtn() {
        waitToBePresentAndClick(btnBugReopened);
        return this;
    }


    public IssuePage clickBugClosedBtn() {
        waitToBePresentAndClick(btnBugClosed);
        return this;
    }

    public IssuePage clickStartProgressBtn() {
        btnStoryStartProgress.click();
        return this;
    }

    public IssuePage clickResolveIssueBtn() {
        waitToBePresentAndClick(btnStoryResolveIssue);
        return this;
    }

    public IssuePage clickStopProgressBtn() {
        waitToBePresentAndClick(btnStoryStopProgress);
        return this;
    }

    public IssuePage clickCloseIssueBtn() {
        waitToBePresentAndClick(btnStoryCloseIssue);
        return this;
    }

    public IssuePage clickReopenIssueBtn() {
        waitToBePresentAndClick(btnStoryReopenIssue);
        return this;
    }

    public IssuePage selectResolution() {
        waitToBePresentAndClick(resolutionDropDownMenu);
        resolutionDropDownMenu.sendKeys("Done");
        resolutionDropDownMenu.click();
        return this;
    }

    public IssuePage checkStatus(String expectedStatus){
        waitToBePresentAndContainsText(status, expectedStatus);
        return this;
    }

    public IssuePage clickWorkflowSubmitOnPopUp(){
        waitToBePresentAndClick(getBtnStoryResolveIssue);
        return this;
    }

    public boolean isOnThePage(String issueId) {

        String url = String.format(pageURL, issueId);
        return super.isOnThePage(url);

    }

    public IssuePage clickBrowseButton() {
        driver.findElement((By) browseButton).click();
        return this;
    }

    public IssuePage setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(stringSelection, null);
        return this;
    }

    public IssuePage selectItem() throws AWTException {

        Robot rb = new Robot();
        rb.delay(1000);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.delay(300);
        rb.keyPress(KeyEvent.VK_V);
        rb.delay(300);
        rb.keyRelease(KeyEvent.VK_V);
        rb.delay(700);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.delay(300);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.delay(300);
        rb.keyRelease(KeyEvent.VK_ENTER);
        rb.delay(300);

        return this;
    }

}
