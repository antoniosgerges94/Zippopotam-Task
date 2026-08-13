package tests;

import base.BaseTest;
import clients.ZippopotamClient;
import data.ZippopotamTestData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

public class ZippopotamNegativeTest extends BaseTest {

    private ZippopotamClient client;

    @BeforeClass
    public void initializeClient() {
        client = new ZippopotamClient();
    }

    @Test(
            dataProvider = "invalidLocations",
            dataProviderClass = ZippopotamTestData.class
    )
    public void shouldReturn404ForInvalidLocation(
            String country,
            String postalCode) {

        logger.info(
                "Testing invalid location {}/{}",
                country,
                postalCode
        );

        client.getLocation(country, postalCode)
              .then()
              .statusCode(404);
    }

    @Test
    public void shouldReturn404ForNonExistentPostalCode() {

        client.getLocation("us", "99999")
              .then()
              .statusCode(404);
    }

    @Test
    public void shouldReturnMeaningful404Response() {

        String response =
                client.getLocation("xx", "90210")
                      .then()
                      .statusCode(404)
                      .extract()
                      .asString();

        assertFalse(
                response.trim().isEmpty(),
                "404 response body should not be empty"
        );
    }
}