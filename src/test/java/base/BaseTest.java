package base;

import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    protected final Logger logger =
            LogManager.getLogger(getClass());

    @BeforeClass
    public void setUp() {

        RestAssured.baseURI =
                "https://api.zippopotam.us";

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        logger.info("Base URI configured: {}", RestAssured.baseURI);
    }
}