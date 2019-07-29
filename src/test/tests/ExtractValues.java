package tests;

import data.ReusableMethods;
import data.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static data.Path.placePostTestAssured;
import static io.restassured.RestAssured.given;

public class ExtractValues extends TestBase{

    @Test
    public void TestAssured() {
        logger.debug("Start test TestAssured");
        RestAssured.baseURI = properties.getProperty("GOOGLE_API_URL");
        Response response = given()
                .param("location", "-33.8670522,151.1957362")
                .param("radius", "500")
                .param("types", "food")
                .param("name", "harbour")
                .param("key", "API-KEY").log().all()
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
                .header("Server", "scaffolding on HTTPServer2").extract().response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        int count = jsonPath.get("results.size()");

        for (int i = 0; i < count; i++) {
            logger.info(jsonPath.get("results[" + i + "].name"));
        }
        logger.info(count);
    }
}