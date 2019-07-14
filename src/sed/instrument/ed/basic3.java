package instrument.ed;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class basic3 {

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
        RestAssured.baseURI = "http://216.10.245.166";
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
        String responseString = response.asString();
        System.out.println("RESPONSE: \n" + responseString);
        JsonPath jsonPath = new JsonPath(responseString);
        String placeId = jsonPath.get("place_id");
        System.out.println("PLACE_ID: \n" + placeId);
    }
}
