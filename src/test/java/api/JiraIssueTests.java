package api;

import api.issue.Issue;
import org.testng.annotations.Test;
import java.util.Date;
import static org.testng.Assert.assertNotNull;

public class JiraIssueTests {

    String projectId = "10000";
    String issueIdBug = "10002";
    String issueIdStory = "10003";
    String assignee = "Marina";

    String summaryBug = "API Bug test by MMazur";
    String summaryStory = "API Story test by MMazur";


    @Test(groups = "API")
    public void authentication(){
        assertNotNull(Authorization.JSESSIONID);
    }

    @Test(groups = {"API"}, dependsOnMethods ={"authentication"})
    public void createBug() {
        Issue issueJson = JiraJsonObjectHelper.generateJSONForIssue(projectId, summaryBug  +"  "+ new Date().toString(), issueIdBug, assignee);
        /* create issue */
        String issueKey = JiraApiActions.createIssue(issueJson);
        /* get issue to confirm that it exists*/
        JiraApiActions.getIssue(issueKey);
        /* delete issue - no permissions */
        //JiraApiActions.deleteIssue(issueKey);

    }

    @Test(groups = {"API"}, dependsOnMethods ={"authentication"})
    public void createStory() {
        Issue issueJson = JiraJsonObjectHelper.generateJSONForIssue(projectId, summaryStory  +"  "+ new Date().toString(), issueIdStory, assignee);
        /* create issue */
        String issueKey = JiraApiActions.createIssue(issueJson);
        /* get issue to confirm that it exists*/
        JiraApiActions.getIssue(issueKey);
    }
}
