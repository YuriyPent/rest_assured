package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class getMethod {

    @Test
    public void TestAssured() {
        RestAssured.baseURI = "https://maps.googleapis.com";
        given()
                .param("location", "-33.8670522,151.1957362")
                .param("radius", "500")
                .param("types", "food")
                .param("name", "harbour")
                .param("key", "API_KEY")
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
                .header("Server", "scaffolding on HTTPServer2");
//                .and()
//                .body("results[0].place_id", equalTo("ChIJK4NnVUCuEmsRFyTLcJ7ZvBU"))
//                .and()
//                .body("results[0].name", equalTo("Harbour Bar & Kitchen"));
    }
}