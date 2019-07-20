package data;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.io.IOException;

import static data.TimeStamp.getTimeStamp;
import static io.restassured.RestAssured.given;

public class ReusableMethods extends TestBase {

    public static XmlPath rawToXML(Response response) {

        String responseXML = response.asString();
        System.out.println("RESPONSE: \n" + responseXML);
        return new XmlPath(responseXML);
    }

    public static JsonPath rawToJSON(Response response) {

        String responseJSON = response.asString();
//        System.out.println("RESPONSE: \n" + responseJSON);
        return new JsonPath(responseJSON);
    }

    private static String getSessionKey() {
        RestAssured.baseURI = properties.getProperty("LOCALHOST");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(properties.getProperty("JIRA_AUTH_BODY"))
                .when()
                .post(properties.getProperty("JIRA_AUTH_PATH"))
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON).extract().response();
        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        return jsonPath.get("session.value");
    }

    public static String jiraCreateIssue() throws IOException {
        RestAssured.baseURI = properties.getProperty("LOCALHOST");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKey())
                .body(GenerateStringFromResource(
                        System.getProperty("user.dir")
                                + properties.getProperty("JIRA_CREATE_BUG_PATH")))
                .when()
                .post(properties.getProperty("JIRA_CREATE_ISSUE_PATH"))
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .contentType(ContentType.JSON).extract().response();
        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        String id = jsonPath.get("id");
        System.out.println("ISSUE CREATE SUCCESSFULLY \nISSUE_ID: \n" + id);
        return id;
    }

    public static void jiraCreateComment() throws IOException {
        RestAssured.baseURI = properties.getProperty("LOCALHOST");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKey())
                .body(GenerateStringFromResource(
                        System.getProperty("user.dir")
                                + properties.getProperty("JIRA_CREATE_COMMENT_PATH")))
                .when()
                .post(properties.getProperty("JIRA_CREATE_ISSUE_PATH")
                        + ReusableMethods.jiraSearchIssue() + "/" + "comment")
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .contentType(ContentType.JSON).extract().response();
        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        String body = jsonPath.get("body");
        System.out.println("COMMENT CREATE SUCCESSFULLY \nCOMMENT: \n" + body);
    }

    private static String jiraSearchIssue() throws IOException {
        RestAssured.baseURI = properties.getProperty("LOCALHOST");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKey())
                .body(GenerateStringFromResource(
                        System.getProperty("user.dir")
                                + properties.getProperty("JIRA_SEARCH_ISSUE_ID")))
                .when()
                .post(properties.getProperty("JIRA_SEARCH_ISSUE_PATH"))
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON).extract().response();
        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        String id = jsonPath.get("issues[0].id");
        String key = jsonPath.get("issues[0].key");
//        System.out.println("ISSUE SEARCH SUCCESSFULLY \nISSUE_ID: \n" + id + "\nKEY: \n" + key);
        return id;
    }

    private static String jiraSearchCommentId() throws IOException {
        RestAssured.baseURI = properties.getProperty("LOCALHOST");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKey())
                .when()
                .get(properties.getProperty("JIRA_CREATE_ISSUE_PATH")
                        + ReusableMethods.jiraSearchIssue() + "/" + "comment")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON).extract().response();
        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        String id = jsonPath.get("comments[0].id");
        System.out.println("COMMENT SEARCH SUCCESSFULLY \nCOMMENT_ID: \n" + id);
        return id;
    }

    public static void jiraUpdateComment() throws IOException {
        RestAssured.baseURI = properties.getProperty("LOCALHOST");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKey())
                .body("{\n" +
                        "  \"body\": \"Updating comment from the automation code " + getTimeStamp() + "\",\n" +
                        "  \"visibility\": {\n" +
                        "    \"type\": \"role\",\n" +
                        "    \"value\": \"Administrators\"\n" +
                        "  }\n" +
                        "}")
                .when()
                .put(properties.getProperty("JIRA_CREATE_ISSUE_PATH")
                        + ReusableMethods.jiraSearchIssue() + "/" + "comment" + "/" + jiraSearchCommentId())
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON).extract().response();
        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        String body = jsonPath.get("body");
        System.out.println("COMMENT UPDATE SUCCESSFULLY \nCOMMENT: \n" + body);
    }
}
