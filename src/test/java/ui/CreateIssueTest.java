package ui;

import org.testng.annotations.*;
import ui.pages.*;


import java.awt.*;
import java.io.File;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateIssueTest {
    CreateIssuePage createIssuePage;
    IssuePage issuePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeGroups(groups = "UI")
    public void setUp(){
        createIssuePage = new CreateIssuePage();
        issuePage = new IssuePage();
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();

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
    public void createNewStory(){

        String projectId = "Simple Project";
        String issueType = "Story";
        String storySummary = "MMazur Story summary";
        String storyDescription = "MMazur Story description";
        String issuePriority = "Medium";

        createIssuePage
                .enterProject(projectId)
                .enterIssueType(issueType)
                .fillSummary(storySummary)
                .switchDescriptionToTextMode() // this step is for Chrome - it can't focus on description if it's in visual mode
                .fillDescription(storyDescription)
                .selectPriority(issuePriority)
                .clickAssignToMeButton()
                .submitNewTicketAndOpenIt();


        assertTrue(issuePage.isProjectIdCorrect(projectId));
        assertTrue(issuePage.isIssueSummaryCorrect(storySummary));
        assertTrue(issuePage.isIssueTypeCorrect(issueType));
        assertTrue(issuePage.isIssuePriorityCorrect(issuePriority));
        assertTrue(issuePage.isIssueDescriptionCorrect(storyDescription));
        assertTrue(issuePage.isIssueAssigneeCorrect());
    }

    @Test(groups = "UI")
    public void createNewBug(){

        String projectId = "Simple Project";
        String issueType = "Bug";
        String storySummary = "MMazur Bug";
        String storyDescription = "MMazur Bug description";
        String issuePriority = "Medium";


        createIssuePage
                .enterProject(projectId)
                .enterIssueType(issueType)
                .fillSummary(storySummary)
                .switchDescriptionToTextMode() // this step is for Chrome - it can't focus on description if it's in visual mode
                .fillDescription(storyDescription)
                .selectPriority(issuePriority)
                .clickAssignToMeButton()
                .submitNewTicketAndOpenIt();

        assertTrue(issuePage.isProjectIdCorrect(projectId));
        assertTrue(issuePage.isIssueSummaryCorrect(storySummary));
        assertTrue(issuePage.isIssueTypeCorrect(issueType));
        assertTrue(issuePage.isIssuePriorityCorrect(issuePriority));
        assertTrue(issuePage.isIssueDescriptionCorrect(storyDescription));
    }
    @Test(groups = "UI")
    public void addAttachmentToIssue() throws AWTException{
        String IssueId = "SIM-1360";
        String pathToFile = "./src/main/resources/Selection_886.png";
        String fileName = "Selection_886.png";
        File file = new File(pathToFile);
        dashboardPage.search(IssueId);
        assertEquals(issuePage.isOnThePage(IssueId), true);
        issuePage
                .clickBrowseButton()
                .setClipboardData(file.getAbsolutePath())
                .robot();
        assertEquals(issuePage.isAttachmentPresent(fileName),true);

    }

    @Test(groups = "UI")
    public void issuesReportedByMe() {
        String issueType = "Story";
        String storySummary = "MMazur Story";
        createIssuePage
                .enterIssueType(issueType)
                .fillSummary(storySummary)
                .submitNewTicketAndOpenIt();
        dashboardPage.issuesFilterReportedByMe();
    }
}
