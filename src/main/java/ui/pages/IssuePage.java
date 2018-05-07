package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;


public class IssuePage extends BasePage{
    private By projectIdLocator = By.id("project-name-val");
    private By issueSummaryLocator = By.id("summary-val");
    private By issueTypeField = By.id("type-val");
    private By issuePriorityField = By.id("priority-val");
    private By issueAssigneeField = By.id("issue_summary_assignee_" + username);
    private By issueDescriptionLocator = By.id("description-val");
    private String pageURL = baseURL + "/browse/%s";
    //---Attachment
    private By browseButton = By.xpath("//*[@duitype='dndattachment/dropzones/AttachmentsDropZone']//*[@class='issue-drop-zone__button']");
    private String fileName = "//*[@class='attachment-content js-file-attachment']//*[contains(text(),'%s')]";
    private By btnBugOpened = By.xpath("//*[@class='trigger-label'][contains(text(), 'Bug opened')]");
    private By btnBugInProgress = By.xpath("//*[@class='trigger-label'][contains(text(), 'Bug in progress')]");
    private By btnBugFixed = By.xpath("//*[@class='trigger-label'][contains(text(), 'Bug fixed')]");
    private By btnBugInTesting = By.xpath("//*[@class='trigger-label'][contains(text(), 'Bug in Testing')]");
    private By btnBugReopened = By.xpath("//*[@class='trigger-label'][contains(text(), 'Bug reopened')]");
    private By btnBugClosed = By.xpath("//*[@class='trigger-label'][contains(text(), 'Bug closed')]");
    private By statusBugNew = By.xpath("//*[@id='status-val']//*[text()='New']");
    private By statusBugOpen = By.xpath("//*[@id='status-val']//*[text()='Open']");
    private By statusBugInProgress = By.xpath("//*[@id='status-val']//*[contains(text(), 'In Progress')]");
    private By statusBugFixed = By.xpath("//*[@id='status-val']//*[contains(text(), 'Fixed')]");
    private By statusBugInTesting = By.xpath("//*[@id='status-val']//*[text()='In Testing']");
    private By statusBugReopened = By.xpath("//*[@id='status-val']//*[text()='Reopened']");
    private By statusBugDone = By.xpath("//*[@id='status-val']//*[text()='Done']");
    private By btnStoryStartProgress = By.xpath("//*[@class='toolbar-item']//*[text()='Start Progress']");
    private By btnStoryResolveIssue = By.xpath("//*[@class='toolbar-item']//*[text()='Resolve Issue']");
    private By btnStoryReopenIssue = By.xpath("//*[@class='toolbar-item']//*[text()='Reopen Issue']");
    private By btnStoryCloseIssue = By.xpath("//*[@class='toolbar-item']//*[text()='Close Issue']");
    private By btnStoryStopProgress = By.xpath("//*[@class='toolbar-item']//*[text()='Stop Progress']");
    private By statusStoryOpen = By.xpath("//*[@id='status-val']//*[text()='Open']");
    private By statusStoryInProgress = By.xpath("//*[@id='status-val']//*[text()='In Progress']");
    private By statusStoryResolved = By.xpath("//*[@id='status-val']//*[text()='Resolved']");
    private By statusStoryReopened = By.xpath("//*[@id='status-val']//*[text()='Reopened']");
    private By statusStoryClosed = By.xpath("//*[@id='status-val']//*[text()='Closed']");
    private By resolutionDropDownMenu = By.id("resolution");
    private By getBtnStoryResolveIssue = By.id("issue-workflow-transition-submit");

    public boolean isProjectIdCorrect(String projectId) {
        return waitToBePresentAndContainsText(projectIdLocator, projectId);
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

    public boolean isIssueDescriptionCorrect(String description) {
        return waitToBePresentAndContainsText(issueDescriptionLocator, description);
    }

    public boolean isIssueAssigneeCorrect() {
        if (driver.findElement(issueAssigneeField) == null) {
            return false;
        }
        return true;
    }
    public boolean isAttachmentPresent(String file) {
        String selector = String.format(fileName, file);
        return waitToBePresentAndContainsText(By.xpath(selector), file);
    }

    public IssuePage clickBugOpenedBtn() {
        driver.findElement(btnBugOpened).click();
        return this;
    }
    public boolean isBugOpenedBtnPresent() {
        By buttonSelector = btnBugOpened;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public IssuePage clickBugInProgressBtn() {
        waitToBePresentAndClick(btnBugInProgress);
        return this;
    }

    public boolean isInProgressBtnPresent() {
        By buttonSelector = btnBugInProgress;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public IssuePage clickBugFixedBtn() {
        waitTillBeAbleToClick(btnBugFixed);
        return this;
    }

    public boolean isBugFixedBtnPresent() {
        By buttonSelector = btnBugFixed;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public IssuePage clickBugInTestingBtn() {
        waitTillBeAbleToClick(btnBugInTesting);
        return this;
    }

    public boolean isBugInTestingBtnPresent() {
        By buttonSelector = btnBugInTesting;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public IssuePage clickBugReopenedBtn() {
        waitTillBeAbleToClick(btnBugReopened);
        return this;
    }

    public boolean isBugReopenedBtnPresent() {
        By buttonSelector = btnBugReopened;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public IssuePage clickBugClosedBtn() {
        waitTillBeAbleToClick(btnBugClosed);
        return this;
    }

    public boolean isBugClosedBtnPresent() {
        By buttonSelector = btnBugInProgress;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isBugStatusNew() {
        By buttonSelector = statusBugNew;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isBugStatusOpen() {
        By buttonSelector = statusBugOpen;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isBugStatusInProgress() {
        By buttonSelector = statusBugInProgress;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isBugStatusFixed() {
        By buttonSelector = statusBugFixed;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isBugStatusReopened() {
        By buttonSelector = statusBugReopened;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isBugStatusInTesting() {
        By buttonSelector = statusBugInTesting;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isBugStatusDone() {
        By buttonSelector = statusBugDone;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public IssuePage clickStartProgressBtn() {
        driver.findElement(btnStoryStartProgress).click();
        return this;
    }
    public boolean isStartProgressBtnPresent() {
        By buttonSelector = btnStoryStartProgress;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public IssuePage clickResolveIssueBtn() {
        waitTillBeAbleToClick(btnStoryResolveIssue);
        return this;
    }

    public boolean isResolveIssuePresent() {
        By buttonSelector = btnStoryResolveIssue;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public IssuePage clickStopProgressBtn() {
        waitTillBeAbleToClick(btnStoryStopProgress);
        return this;
    }

    public boolean isStopProgressBtnPresent() {
        By buttonSelector = btnStoryStopProgress;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public IssuePage clickCloseIssueBtn() {
        waitTillBeAbleToClick(btnStoryCloseIssue);
        return this;
    }

    public boolean isCloseIssueBtnPresent() {
        By buttonSelector = btnStoryCloseIssue;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public IssuePage clickReopenIssueBtn() {
        waitTillBeAbleToClick(btnStoryReopenIssue);
        return this;
    }

    public boolean isReopenIssueBtnPresent() {
        By buttonSelector = btnStoryReopenIssue;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isStoryStatusOpen() {
        By buttonSelector = statusStoryOpen;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isStoryStatusInProgress() {
        By buttonSelector = statusStoryInProgress;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isStoryStatusResolved() {
        By buttonSelector = statusStoryResolved;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isStoryStatusReopened() {
        By buttonSelector = statusStoryReopened;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isStoryStatusClosed() {
        By buttonSelector = statusStoryClosed;
        try {
            driver.findElement(buttonSelector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public IssuePage selectResolution(String resolution) {
        waitTillBeAbleToClick(resolutionDropDownMenu);
        waitToBePresentAndSendKeys(resolutionDropDownMenu, resolution);
        driver.findElement(resolutionDropDownMenu).sendKeys(Keys.TAB);
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
        driver.findElement(browseButton).click();
        return this;
    }

    public IssuePage setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(stringSelection, null);
        return this;
    }

    public IssuePage robot() throws AWTException {

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
