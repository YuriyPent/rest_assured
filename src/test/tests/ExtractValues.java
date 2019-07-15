package tests;

import data.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ExtractValues {

    @Test
    public void TestAssured() {
        RestAssured.baseURI = "https://maps.googleapis.com";
        Response response = given()
                .param("location", "-33.8670522,151.1957362")
                .param("radius", "500")
                .param("types", "food")
                .param("name", "harbour")
                .param("key", "AIzaSyDW6QVDCAdrRXHivlxKWpTNHV1LfPsVpCQ")
//                .header("", "")
//                .cookie("", "")
//                .body("")
                .when()
                .get("/maps/api/place/nearbysearch/json")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and().statusLine("HTTP/1.1 200 OK")
                .and()
                .header("Server", "scaffolding on HTTPServer2").extract().response();
        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        int count = jsonPath.get("results.size()");
        for (int i = 0; i < count; i++) {

        }
        System.out.println(count);
//                .and()
//                .body("results[0].place_id", equalTo("ChIJK4NnVUCuEmsRFyTLcJ7ZvBU"))
//                .and()
//                .body("results[0].name", equalTo("Harbour Bar & Kitchen"));
    }
}