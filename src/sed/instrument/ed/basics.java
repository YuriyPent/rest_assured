package instrument.ed;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class basics {

    public static void main(String[] args){
        RestAssured.baseURI = "https://maps.googleapis.com";
        given()
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
                .assertThat().statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    }
}