package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static data.Path.placePostDataJson;
import static data.PostDataJson.getPostData;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostAndDeleteMethod {
    private Properties properties = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/env.properties");
        properties.load(fileInputStream);
    }

    @Test
    public void AddAndDeletePlace() {

//        Add place
        RestAssured.baseURI = properties.getProperty("HOST");
        Response response = given()
                .queryParam("key", properties.getProperty("KEY"))
                .body(getPostData())
                .when()
                .post(placePostDataJson())
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("status", equalTo("OK"))
                .extract().response();
        System.out.println("ADD PLACE SUCCESS");

//        Grab the place_id from response
        String responseString = response.asString();
        System.out.println("RESPONSE: \n" + responseString);
        JsonPath jsonPath = new JsonPath(responseString);
        String placeId = jsonPath.get("place_id");
        System.out.println("PLACE_ID: \n" + placeId);

//        Place this place_id in the Delete request
        given()
                .queryParam("key", properties.getProperty("KEY"))
                .body("{\"place_id\": \"" + placeId + "\"}")
                .when()
                .post("maps/api/place/delete/json")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("status", equalTo("OK"));
        System.out.println("DELETE PLACE SUCCESS");
    }
}
