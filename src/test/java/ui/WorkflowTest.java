package ui;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pages.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WorkflowTest {

    LoginPage loginPage;
    IssuePage issuePage;
    CreateIssuePage createIssuePage;
    DashboardPage dashboardPage;

    @BeforeGroups(groups = "SKIP")
    public void setUp() {
        loginPage = new LoginPage();
        issuePage = new IssuePage();
        dashboardPage = new DashboardPage();

        loginPage.open();
        assertEquals(loginPage.isOnThePage(), true);
        loginPage.enterUsername().enterPassword().clickLogin();
        assertEquals(dashboardPage.isOnThePage(), true);
    }
    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        createIssuePage = new CreateIssuePage();
        createIssuePage.clickCreateAndWaitForDialog();
    }

    @Test(groups = "UI")
    public void checkBugWorkflow() {
        String issueType = "Bug";
        String storySummary = "MMazur Bug";
        createIssuePage
                .enterIssueType(issueType)
                .fillSummary(storySummary)
                .submitNewTicketAndOpenIt();

        assertTrue(issuePage.isBugStatusNew());
        assertTrue(issuePage.isBugOpenedBtnPresent());

        issuePage.clickBugOpenedBtn();
        assertTrue(issuePage.isInProgressBtnPresent());
        assertTrue(issuePage.isBugStatusOpen());

        issuePage.clickBugInProgressBtn();
        assertTrue(issuePage.isBugFixedBtnPresent());
        assertTrue(issuePage.isBugStatusInProgress());

        issuePage.clickBugFixedBtn();
        assertTrue(issuePage.isBugInTestingBtnPresent());
        assertTrue(issuePage.isBugStatusFixed());

        issuePage.clickBugInTestingBtn();
        assertTrue(issuePage.isBugReopenedBtnPresent());
        assertTrue(issuePage.isBugStatusInTesting());

        issuePage.clickBugReopenedBtn();
        assertTrue(issuePage.isBugClosedBtnPresent());
        assertTrue(issuePage.isBugStatusReopened());

        issuePage.clickBugClosedBtn();
        assertTrue(issuePage.isBugStatusDone());
    }

    @Test(groups = "UI")
    public void checkStoryWorkflow() {
        String issueType = "Story";
        String storySummary = "MMazur Story";
        String resolution = "Done";
        createIssuePage
                .enterIssueType(issueType)
                .fillSummary(storySummary)
                .submitNewTicketAndOpenIt();

        assertTrue(issuePage.isStoryStatusOpen());
        assertTrue(issuePage.isStartProgressBtnPresent());

        issuePage.clickStartProgressBtn();
        assertTrue(issuePage.isStoryStatusInProgress());
        assertTrue(issuePage.isStopProgressBtnPresent());


        issuePage.clickStopProgressBtn();
        assertTrue(issuePage.isStoryStatusOpen());
        assertTrue(issuePage.isResolveIssuePresent());

        issuePage.clickResolveIssueBtn()
                 .selectResolution(resolution)
                 .clickWorkflowSubmitOnPopUp();
        assertTrue(issuePage.isStoryStatusResolved());
        assertTrue(issuePage.isReopenIssueBtnPresent());

        issuePage.clickReopenIssueBtn()
                 .clickWorkflowSubmitOnPopUp();
        assertTrue(issuePage.isStoryStatusReopened());
        assertTrue(issuePage.isCloseIssueBtnPresent());

        issuePage.clickCloseIssueBtn()
                 .selectResolution(resolution)
                 .clickWorkflowSubmitOnPopUp();
        assertTrue(issuePage.isStoryStatusClosed());
    }
}

