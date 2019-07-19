package tests;

import data.ReusableMethods;
import data.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static data.PostDataJson.AddBook;
import static io.restassured.RestAssured.given;

public class DynamicJson extends TestBase{

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle){

        RestAssured.baseURI = properties.getProperty("HOST");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(AddBook(isbn, aisle))
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
}
