package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class postMethodXML {

    @Test
    public void postData() throws IOException {
        String postData = GenerateStringFromResource(System.getProperty("user.dir")
                + "/src/test/utilities/postdata.xml");
        RestAssured.baseURI = "http://216.10.245.166";
        Response response = given()
                .queryParam("key", "qaclick123")
                .body(postData)
                .when()
                .post("maps/api/place/add/xml")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.XML).extract().response();
        System.out.println(response.asString());
    }

    @SuppressWarnings("Since15")
    private static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
