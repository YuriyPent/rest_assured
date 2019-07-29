package data;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.getProperty;

public class TestBase {

    protected static Logger logger = LogManager.getLogger(TestBase.class.getName());

    protected static Properties properties = new Properties();

    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(
                getProperty("user.dir") + "/src/test/resources/env.properties");
        properties.load(fileInputStream);
    }

    @DataProvider(name = "BooksData")
    public Object[][] getDataP() {

        return new Object[][]{
                {RandomStringUtils.randomAlphabetic(3),
                        ThreadLocalRandom.current().nextInt(1, 9999) + ""},
                {RandomStringUtils.randomAlphabetic(3),
                        ThreadLocalRandom.current().nextInt(1, 9999) + ""},
                {RandomStringUtils.randomAlphabetic(3),
                        ThreadLocalRandom.current().nextInt(1, 9999) + ""}
        };
    }

    @SuppressWarnings("Since15")
    protected static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
