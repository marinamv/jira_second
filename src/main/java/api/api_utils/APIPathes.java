package api.api_utils;

public interface APIPathes {
    String login = "/rest/auth/1/session/";
    String issue = "/rest/api/2/issue/";
    String existingIssue = "/rest/api/2/issue/%s";
    String projectAndIssuesTypeId = "/rest/api/2/issue/createmeta";
}
