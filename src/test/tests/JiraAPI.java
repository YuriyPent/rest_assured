package tests;

import data.ReusableMethods;
import data.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class JiraAPI extends TestBase {

    @Test
    public void JiraApi() throws IOException {
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
        System.out.println("ISSUE_ID: \n" + jsonPath.get("id"));

    }
}
