package ui;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import ui.enums.IssueStatuses;
import ui.pages.*;
import utils.ListenerTest;

import static org.testng.Assert.assertEquals;



public class WorkflowStory {

    LoginPage loginPage;
    IssuePage issuePage;
    CreateIssuePage createIssuePage;
    DashboardPage dashboardPage;
    protected String issueTypeStory = ListenerTest.properties.get("issueTypeStory");
    protected String storySummary = ListenerTest.properties.get("storySummary");

    @BeforeGroups(groups = "UI")
    public void setUp() {
        loginPage = new LoginPage();
        issuePage = new IssuePage();
        dashboardPage = new DashboardPage();

        loginPage.open();
        assertEquals(loginPage.isOnThePage(), true);
        loginPage.enterUsername().enterPassword().clickLogin();
        assertEquals(dashboardPage.isOnThePage(), true);
    }

    @Test(priority = 11, groups = "UI")
    public void checkStoryStatusOpen() {
        createIssuePage = new CreateIssuePage();
        createIssuePage
                .clickCreateAndWaitForDialog()
                .enterIssueTypeBug(issueTypeStory)
                .fillSummary(storySummary)
                .submitNewTicketAndOpenIt();

        assertEquals(issuePage.status.getText(), IssueStatuses.OPEN.toString(), "Issue status should be Open: ");
    }

    @Test(priority = 12, groups = {"UI"}, dependsOnMethods = {"checkStoryStatusOpen"})
    public void checkStoryStatusInProgress() {

        issuePage.clickStartProgressBtn()
                 .checkStatus("In Progress");
        assertEquals(issuePage.status.getText(), IssueStatuses.IN_PROGRESS.toString(), "Issue status should be In Progress: ");
    }

    @Test(priority = 13, groups = {"UI"}, dependsOnMethods = {"checkStoryStatusInProgress"})
    public void checkStoryStatusStopProgress() {
        issuePage.clickStopProgressBtn()
                 .checkStatus("Open");
        assertEquals(issuePage.status.getText(), IssueStatuses.OPEN.toString(), "Issue status should be Open: ");
    }

    @Test(priority = 14, groups = {"UI"}, dependsOnMethods = {"checkStoryStatusStopProgress"})
    public void checkStoryStatusResolved() {
        issuePage.clickResolveIssueBtn()
                .selectResolution()
                .clickWorkflowSubmitOnPopUp()
                .checkStatus("Resolved");
        assertEquals(issuePage.status.getText(), IssueStatuses.RESOLVED.toString(), "Issue status should be Resolved: ");
    }

    @Test(priority = 15, groups = {"UI"}, dependsOnMethods = {"checkStoryStatusResolved"})
    public void checkStoryStatusReopened() {
        issuePage.clickReopenIssueBtn()
                .clickWorkflowSubmitOnPopUp()
                .checkStatus("Reopened");
        assertEquals(issuePage.status.getText(), IssueStatuses.REOPENED.toString(), "Issue status should be Reopened: ");
    }

    @Test(priority = 16, groups = {"UI"}, dependsOnMethods = {"checkStoryStatusReopened"})
    public void checkStoryStatusClosed() {
        issuePage.clickCloseIssueBtn()
                 .selectResolution()
                 .clickWorkflowSubmitOnPopUp()
                 .checkStatus("Closed");
        assertEquals(issuePage.status.getText(), IssueStatuses.CLOSED.toString(), "Issue status should be Closed: ");
    }
}

