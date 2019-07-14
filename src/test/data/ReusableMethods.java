package data;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {

    public static XmlPath rawToXML(Response response) {

        String responseXML = response.asString();
        System.out.println("RESPONSE: \n" + responseXML);
        return new XmlPath(responseXML);
    }

    public static JsonPath rawToJSON(Response response) {

        String responseJSON = response.asString();
        System.out.println("RESPONSE: \n" + responseJSON);
        return new JsonPath(responseJSON);
    }
}
