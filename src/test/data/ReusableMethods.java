package data;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReusableMethods extends TestBase{

    public static XmlPath rawToXML(Response response) {

        String responseXML = response.asString();
        System.out.println("RESPONSE: \n" + responseXML);
        return new XmlPath(responseXML);
    }

    public static JsonPath rawToJSON(Response response) {

        String responseJSON = response.asString();
        System.out.println("RESPONSE: \n" + responseJSON);
        return new JsonPath(responseJSON);
    }

    public static String getSessionKey(){
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
}
