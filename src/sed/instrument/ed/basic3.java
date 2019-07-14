package instrument.ed;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class basic3 {
    private Properties properties = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(
                System.getProperty("user.dir") + "/src/sed/files/env.properties");
        properties.load(fileInputStream);
    }

    @Test
    public void AddAndDeletePlace() {

        String body = "{\n" +
                "    \"location\":{\n" +
                "        \"lat\" : -38.383494,\n" +
                "        \"lng\" : 33.427362\n" +
                "    },\n" +
                "    \"accuracy\":50,\n" +
                "    \"name\":\"Frontline house\",\n" +
                "    \"phone_number\":\"(+91) 983 893 3937\",\n" +
                "    \"address\" : \"29, side layout, cohen 09\",\n" +
                "    \"types\": [\"shoe park\",\"shop\"],\n" +
                "    \"website\" : \"http://google.com\",\n" +
                "    \"language\" : \"French-IN\"\n" +
                "}\n";

//        Add place
        RestAssured.baseURI = properties.getProperty("HOST");
        Response response = given()
                .queryParam("key", "qaclick123")
                .body(body)
                .when()
                .post("maps/api/place/add/json")
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
                .queryParam("key", "qaclick123")
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
