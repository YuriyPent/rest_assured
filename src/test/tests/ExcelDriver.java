package tests;

import data.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ExcelDriver {

    @Test
    public void addBook() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";
        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"name\": \"Learn Appium Automation with Java\",\n" +
                        "    \"isbn\": \"dfdf\",\n" +
                        "    \"aisle\": \"544\",\n" +
                        "    \"author\": \"John foe\"\n" +
                        "}")
                .when()
                .post("/Library/Addbook.php")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON).extract().response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        System.out.println("ID: \n" + jsonPath.get("ID"));
    }
}
