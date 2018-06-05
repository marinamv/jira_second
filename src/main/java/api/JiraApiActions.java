package api;

import api.api_utils.APIPathes;
import api.issue.Issue;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

public class JiraApiActions {

    public static String createIssue(Issue issueJson) {
        ValidatableResponse response = HTTPMethods.post(APIPathes.issue, issueJson);
        Assert.assertEquals(response.extract().statusCode(), 201, "Status is not 201!");
        Assert.assertTrue(response.extract().contentType().contains(ContentType.JSON.toString()), "Invalid Json");
        return response.extract().path("key");
    }

    public static String getIssue(String issueKey) {
        ValidatableResponse response = HTTPMethods.get(APIPathes.issue + issueKey);
        Assert.assertEquals(response.extract().statusCode(), 200, "Status is not 200!");
        Assert.assertTrue(response.extract().contentType().contains(ContentType.JSON.toString()), "Invalid Json");
        return response.extract().asString();
    }

    public static void deleteIssue(String issueKey) {
        ValidatableResponse response = HTTPMethods.delete(APIPathes.issue + issueKey);
        Assert.assertEquals(response.extract().statusCode(), 204, "Status is not 200!");
        Assert.assertTrue(response.extract().contentType().contains(ContentType.JSON.toString()), "Invalid Json");
    }
}
