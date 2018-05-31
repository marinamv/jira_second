package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyReader {

    public Map<String, String> readProperties(String propertyFile) {
        Properties propertyFileValues = new Properties();
        FileInputStream input = null;
        HashMap<String, String> result;

        try {

            try {
                input = new FileInputStream(propertyFile);
                try {
                    propertyFileValues.load(input);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            result = new HashMap();
            result.put("JiraUrl", propertyFileValues.getProperty("JiraUrl"));
            result.put("username", propertyFileValues.getProperty("username"));
            result.put("password", propertyFileValues.getProperty("password"));
            result.put("project", propertyFileValues.getProperty ("project"));
            result.put("bugSummary", propertyFileValues.getProperty ("bugSummary"));
            result.put("storySummary", propertyFileValues.getProperty ("storySummary"));
            result.put("description", propertyFileValues.getProperty ("description"));
            result.put("issueTypeBug", propertyFileValues.getProperty ("issueTypeBug"));
            result.put("issueTypeStory", propertyFileValues.getProperty ("issueTypeStory"));
            result.put("issuePriority", propertyFileValues.getProperty ("issuePriority"));
            result.put("issueId", propertyFileValues.getProperty ("issueId"));
            result.put("fileName", propertyFileValues.getProperty ("fileName"));
            result.put("pathToFile", propertyFileValues.getProperty ("pathToFile"));
            result.put("GridURL", propertyFileValues.getProperty("GridURL"));

        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return result;
    }


}
