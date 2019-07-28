package tests;

import data.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static data.TimeStamp.getTimeStamp;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TweeterTests extends TestBase {

    private String id;

    @Test
    public void getLatestTweet() {

        baseURI = properties.getProperty("TWEETER_URL");
        logger.info("Start test");
        logger.info("Host information" + baseURI);
        Response res = given()
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

        String response = res.asString();
        logger.info(response);
        JsonPath js = new JsonPath(response);

        logger.info(js.get("text"));
        logger.info(js.get("id"));
    }

    @Test
    public void createTweet() {

        baseURI = properties.getProperty("TWEETER_URL");
        Response res = given().
                auth().
                oauth(
                        properties.getProperty("CONSUMER_KEY"),
                        properties.getProperty("CONSUMER_SECRET"),
                        properties.getProperty("TOKEN"),
                        properties.getProperty("TOKEN_SECRET"))
                .queryParam("status", "I am creating this tweet with Automation " + getTimeStamp())
                .when()
                .post(properties.getProperty("TWEETER_POST_PATH"))
                .then()
                .extract()
                .response();

        String response = res.asString();
        logger.info(response);
        JsonPath js = new JsonPath(response);
        logger.info("Below is the tweet added");
        logger.info(js.get("id"));
        id = js.get("id").toString();
    }

    @Test
    public void deleteTweet() {

        createTweet();
        baseURI = properties.getProperty("TWEETER_URL");
        Response res = given().
                auth().
                oauth(
                        properties.getProperty("CONSUMER_KEY"),
                        properties.getProperty("CONSUMER_SECRET"),
                        properties.getProperty("TOKEN"),
                        properties.getProperty("TOKEN_SECRET"))
                .when()
                .post("/destroy/" + id + ".json")
                .then()
                .extract()
                .response();

        String response = res.asString();
        logger.info(response);
        JsonPath js = new JsonPath(response);
        //System.out.println(js.get("text"));
        logger.info("Tweet which got deleted with automation is below");
        logger.info(js.get("text"));
        logger.info(js.get("truncated"));
    }
}
