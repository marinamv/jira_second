package api;

import api.api_utils.APIPathes;
import api.api_utils.HTTPTestListener;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class Authorization {
    public static String JSESSIONID;
    public static String BASE_URI = HTTPTestListener.propertiesJira.get("JiraUrl");
    public static String username = HTTPTestListener.propertiesJira.get("username");
    public static String  password = HTTPTestListener.propertiesJira.get("password");
    static final Logger logger = Logger.getLogger(Authorization.class);

    public static void loginToJIRA() {
        RestAssured.baseURI = BASE_URI;

        String loginJson = JiraJsonObjectHelper.generateJSONForLogin();
        JSESSIONID =
                given().
                        header("Content-Type", ContentType.JSON).
                        body(loginJson).
                        when().
                        post(APIPathes.login).
                        then().
                        statusCode(200).contentType(ContentType.JSON).
                        log().all().
                        extract().
                        path("session.value");

        logger.info("\nAUTHORIZATION TOKEN: " + Authorization.JSESSIONID);
    }
}
