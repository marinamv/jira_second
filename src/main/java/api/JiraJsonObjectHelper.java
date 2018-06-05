package api;

import api.issue.Fields;
import api.issue.Issue;
import api.issue.IssueInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;


public class JiraJsonObjectHelper {

    public static String generateJSONForLogin() {
        JSONObject credentials = new JSONObject();
        credentials.put("username", Authorization.username);
        credentials.put("password", Authorization.password);

        return credentials.toJSONString();
    }

    public static Issue generateJSONForIssue(String projectId, String summary, String issueType, String assignee) {

        Issue issueJson = new Issue(new Fields()
                .setProject(projectId)
                .setSummary(summary)
                .setAssignee(assignee)
                .setIssueType(issueType)
        );
        return issueJson;
    }

    public static String extractJson(IssueInterface issueInterface) {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(issueInterface);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
