package ui;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pages.CreateIssuePage;
import ui.pages.IssuePage;
import ui.pages.LoginPage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WorkflowTest {

    LoginPage loginPage;
    IssuePage issuePage;
    CreateIssuePage createIssuePage;

    @BeforeGroups(groups = "UI")
    public void setUp() {
        loginPage = new LoginPage();
        issuePage = new IssuePage();

        loginPage.open();
        assertEquals(loginPage.isOnThePage(), true);
        loginPage.enterUsername().enterPassword().clickLogin();
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

        issuePage.isBugOpenedBtnPresent();
        issuePage.clickBugOpenedBtn();
        assertTrue(issuePage.isInProgressBtnPresent());

        issuePage.clickBugInProgressBtn();
        assertTrue(issuePage.isBugFixedBtnPresent());

        issuePage.clickBugFixedBtn();
        assertTrue(issuePage.isBugInTestingBtnPresent());

        issuePage.clickBugInTestingBtn();
        assertTrue(issuePage.isBugReopenedBtnPresent());

        issuePage.clickBugReopenedBtn();
        assertTrue(issuePage.isBugClosedBtnPresent());

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

        issuePage.isStartProgressBtnPresent();
        issuePage.clickStartProgressBtn();
        assertTrue(issuePage.isStopProgressBtnPresent());

        issuePage.clickStopProgressBtn();
        assertTrue(issuePage.isResolveIssuePresent());

        issuePage.clickResolveIssueBtn()
                 .selectResolution(resolution)
                 .clickWorkflowSubmitOnPopUp();
        assertTrue(issuePage.isReopenIssueBtnPresent());

        issuePage.clickReopenIssueBtn()
                 .clickWorkflowSubmitOnPopUp();
        assertTrue(issuePage.isCloseIssueBtnPresent());

        issuePage.clickCloseIssueBtn()
                 .selectResolution(resolution)
                 .clickWorkflowSubmitOnPopUp();
        assertTrue(issuePage.isStoryStatusClosed());
    }
}

