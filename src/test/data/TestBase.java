package data;

import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    protected Properties properties = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/env.properties");
        properties.load(fileInputStream);
    }
}
