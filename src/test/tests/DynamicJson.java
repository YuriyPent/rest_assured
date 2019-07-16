package tests;

import data.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static data.PostDataJson.AddBook;
import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook() throws IOException {

        RestAssured.baseURI = "http://216.10.245.166";
        Response response = given()
                .header("Content-Type", "application/json")
                .body(AddBook("forg", "097"))
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

    @DataProvider(name = "BooksData")
    public Object[][] getData() {

        return new Object[][] {
            {
                "iop", "9873"
            },{
                "frf", "8984"
            },{
                "iuyp", "9033"
            }
        };
    }
}
