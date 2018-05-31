package ui;

import org.testng.annotations.*;
import ui.pages.*;
import utils.ListenerTest;
import java.awt.*;
import java.io.File;
import java.util.Date;
import static com.sun.org.apache.xalan.internal.lib.ExsltMath.random;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateIssueTest {
    CreateIssuePage createIssuePage;
    IssuePage issuePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    protected String project = ListenerTest.properties.get("project");
    protected String bugSummary = ListenerTest.properties.get("bugSummary");
    protected String storySummary = ListenerTest.properties.get("storySummary");
    protected String description = ListenerTest.properties.get("description");
    protected String issuePriority = ListenerTest.properties.get("issuePriority");
    protected String issueTypeBug = ListenerTest.properties.get("issueTypeBug");
    protected String issueTypeStory = ListenerTest.properties.get("issueTypeStory");
    protected String issueId = ListenerTest.properties.get("issueId");
    protected String fileName = ListenerTest.properties.get("fileName");
    protected String pathToFile = ListenerTest.properties.get("pathToFile");



    @BeforeGroups(groups = "UI")
    public void setUp(){
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        createIssuePage = new CreateIssuePage();
        issuePage = new IssuePage();
        loginPage.open();

        assertEquals(loginPage.isOnThePage(), true);
        loginPage.enterUsername().enterPassword().clickLogin();
        assertEquals(dashboardPage.isOnThePage(), true);
    }

    @Test(priority = 1, groups = "UI")
    public void createNewStory(){
        createIssuePage.clickCreateAndWaitForDialog()
                .enterProject(project)
                .enterIssueTypeStory(issueTypeStory)
                .fillSummary(storySummary +"  "+ new Date().toString())
                .switchDescriptionToTextMode()
                .fillDescription(description)
                .selectPriority(issuePriority)
                .clickAssignToMeButton()
                .submitNewTicketAndOpenIt();


        assertTrue(issuePage.isProjectIdCorrect(project));
        assertTrue(issuePage.isIssueTypeCorrect(issueTypeStory));
        assertTrue(issuePage.isIssuePriorityCorrect(issuePriority));
        assertTrue(issuePage.isIssueDescriptionCorrect(description));
        assertTrue(issuePage.isIssueAssigneeCorrect());
    }

    @Test(priority = 4, groups = "UI")
    public void createNewBug(){
        createIssuePage.clickCreateAndWaitForDialog()
                //.enterProject(projectId)
                .enterIssueTypeBug(issueTypeBug)
                .fillSummary(bugSummary+ random())
                .switchDescriptionToTextMode()
                .fillDescription(description)
                .selectPriority(issuePriority)
                .clickAssignToMeButton()
                .submitNewTicketAndOpenIt();

        assertTrue(issuePage.isProjectIdCorrect(project));
        assertTrue(issuePage.isIssueTypeCorrect(issueTypeBug));
        assertTrue(issuePage.isIssuePriorityCorrect(issuePriority));
        assertTrue(issuePage.isIssueDescriptionCorrect(description));
    }
    @Test(priority = 2, groups = {"UI"}, dependsOnMethods = {"createNewStory"})
    public void addAttachmentToIssue() throws AWTException{
        File file = new File(String.valueOf(pathToFile));
        //dashboardPage.search(issueId);
        //assertEquals(issuePage.isOnThePage(issueId), true);
        issuePage
                .clickBrowseButton()
                .setClipboardData(file.getAbsolutePath())
                .selectItem();

        //assertEquals(issuePage.isAttachmentPresent(fileName),true);

    }

    @Test(priority = 3, groups = "UI")
    public void issuesReportedByMe() {
        dashboardPage.issuesFilterReportedByMe();
        assertTrue(dashboardPage.isReportedByMeFilter());
    }
}
