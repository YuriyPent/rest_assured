package tests;

import data.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static data.Path.placePostTestAssured;
import static io.restassured.RestAssured.given;

public class GetMethod extends TestBase{

    @Test
    public void TestAssured() {
        logger.debug("Start test TestAssured");
        RestAssured.baseURI = properties.getProperty("GOOGLE_API_URL");
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
                .get(placePostTestAssured())
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