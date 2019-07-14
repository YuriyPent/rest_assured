package tests;

import data.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static data.Path.placePostDataXml;
import static io.restassured.RestAssured.given;

public class PostMethodXML {

    @SuppressWarnings("Since15")
    private static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    @Test
    public void postData() throws IOException {
        String postData = GenerateStringFromResource(System.getProperty("user.dir")
                + "/src/test/utilities/postdata.xml");
        RestAssured.baseURI = "http://216.10.245.166";
        Response response = given()
                .queryParam("key", "qaclick123")
                .body(postData)
                .when()
                .post(placePostDataXml())
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.XML).extract().response();

        XmlPath xmlPath = ReusableMethods.rawToXML(response);
        System.out.println("PLACE_ID: \n" + xmlPath.get("PlaceAddResponse.place_id"));
    }
}
