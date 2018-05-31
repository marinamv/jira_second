package ui;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import ui.enums.IssueStatuses;
import ui.pages.*;
import utils.ListenerTest;

import static org.testng.Assert.assertEquals;

public class WorkflowBug {
    LoginPage loginPage;
    IssuePage issuePage;
    CreateIssuePage createIssuePage;
    DashboardPage dashboardPage;
    protected String issueTypeBug = ListenerTest.properties.get("issueTypeBug");
    protected String bugSummary = ListenerTest.properties.get("bugSummary");


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

    @Test(priority = 1, groups = "UI")
    public void checkBugStatusNew() {
        createIssuePage = new CreateIssuePage();
        createIssuePage
                .clickCreateAndWaitForDialog()
                .enterIssueTypeBug(issueTypeBug)
                .fillSummary(bugSummary)
                .submitNewTicketAndOpenIt();

        assertEquals(issuePage.status.getText(), IssueStatuses.NEW.toString(), "Issue status should be New: ");
    }

    @Test(priority = 2, groups = {"UI"}, dependsOnMethods = {"checkBugStatusNew"})
    public void checkBugStatusOpen() {
        issuePage.clickBugOpenedBtn()
                .checkStatus("Open");
        assertEquals(issuePage.status.getText(), IssueStatuses.OPEN.toString(), "Issue status should be Open: ");
    }

    @Test(priority = 3, groups = {"UI"}, dependsOnMethods = {"checkBugStatusOpen"})
    public void checkBugStatusInProgress() {
        issuePage.clickBugInProgressBtn()
                .checkStatus("In Progress");
        assertEquals(issuePage.status.getText(), IssueStatuses.IN_PROGRESS.toString(), "Issue status should be In Progress: ");
    }

    @Test(priority = 4, groups = {"UI"}, dependsOnMethods = {"checkBugStatusInProgress"})
    public void checkBugStatusFixed() {
        issuePage.clickBugFixedBtn()
                .checkStatus("Fixed");
        assertEquals(issuePage.status.getText(), IssueStatuses.FIXED.toString(), "Issue status should be Fixed: ");
    }

    @Test(priority = 5, groups = {"UI"}, dependsOnMethods = {"checkBugStatusFixed"})
    public void checkBugStatusInTesting() {
        issuePage.clickBugInTestingBtn()
                .checkStatus("In Testing");
        assertEquals(issuePage.status.getText(), IssueStatuses.IN_TESTING.toString(), "Issue status should be In Testing: ");
    }

    @Test(priority = 6, groups = {"UI"}, dependsOnMethods = {"checkBugStatusInTesting"})
    public void checkBugStatusReopened() {
        issuePage.clickBugReopenedBtn()
                .checkStatus("Reopened");
        assertEquals(issuePage.status.getText(), IssueStatuses.REOPENED.toString(), "Issue status should be Reopened: ");
    }

    @Test(priority = 7, groups = {"UI"}, dependsOnMethods = {"checkBugStatusReopened"})
    public void checkBugStatusDone() {
        issuePage.clickBugClosedBtn()
                .checkStatus("Done");
        assertEquals(issuePage.status.getText(),IssueStatuses.DONE.toString(), "Issue status should be Done: ");
    }
}
