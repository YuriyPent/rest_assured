package tests;

import data.ReusableMethods;
import data.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class StaticJson extends TestBase{

    @Test
    public void addBook() throws IOException {

        RestAssured.baseURI = properties.getProperty("HOST");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(GenerateStringFromResource(
                        System.getProperty("user.dir")
                                + properties.getProperty("ADD_BOOK_JSON_PATH")))
                .when()
                .post(properties.getProperty("ADD_BOOK_PATH"))
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON).extract().response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        System.out.println("ID: \n" + jsonPath.get("ID"));
    }

    @SuppressWarnings("Since15")
    private static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
