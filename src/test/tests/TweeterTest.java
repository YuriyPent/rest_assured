package tests;

import data.ReusableMethods;
import data.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TweeterTest extends TestBase {

    private CloseableHttpClient httpclient;

    @Test
    public void getLatestTweet() {

        baseURI = properties.getProperty("TWEETER_URL");
        Response response = given()
                .auth()
                .oauth(
                        properties.getProperty("CONSUMER_KEY"),
                        properties.getProperty("CONSUMER_SECRET"),
                        properties.getProperty("TOKEN"),
                        properties.getProperty("TOKEN_SECRET"))
                .queryParam("count", "1")
                .when()
                .get(properties.getProperty("TWEETER_PATH"))
                .then()
                .extract()
                .response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        System.out.println(jsonPath.get("text"));
        System.out.println(jsonPath.get("id"));
    }
}
