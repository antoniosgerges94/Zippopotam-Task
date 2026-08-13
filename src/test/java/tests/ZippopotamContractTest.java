package tests;

import base.BaseTest;
import clients.ZippopotamClient;
import models.Place;
import models.ZippopotamResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.*;

public class ZippopotamContractTest extends BaseTest {

    private ZippopotamClient client;

    @BeforeClass
    public void initializeClient() {
        client = new ZippopotamClient();
    }

    @Test
    public void shouldDeserializeResponseIntoPojo() {

        ZippopotamResponse response =
                client.getLocation("us", "90210")
                      .then()
                      .statusCode(200)
                      .extract()
                      .as(ZippopotamResponse.class);

        assertNotNull(response);
        assertEquals(response.getPostCode(), "90210");
        assertEquals(response.getCountry(), "United States");
        assertEquals(response.getCountryAbbreviation(), "US");

        assertNotNull(response.getPlaces());
        assertFalse(response.getPlaces().isEmpty());

        Place place = response.getPlaces().get(0);

        assertNotNull(place.getPlaceName());
        assertNotNull(place.getLongitude());
        assertNotNull(place.getLatitude());
        assertNotNull(place.getState());
        assertNotNull(place.getStateAbbreviation());
    }

    @Test
    public void shouldReturnJsonContentType() {

        client.getLocation("us", "90210")
              .then()
              .statusCode(200)
              .contentType("application/json");
    }

    @Test
    public void shouldRespondWithinAcceptableTime() {

        client.getLocation("us", "90210")
              .then()
              .statusCode(200)
              .time(org.hamcrest.Matchers.lessThan(3000L));
    }

    // ADD THIS TEST
    @Test
    public void shouldMatchResponseSchema() {

        client.getLocation("us", "90210")
              .then()
              .statusCode(200)
              .body(
                      matchesJsonSchemaInClasspath(
                              "schemas/zippopotam-response-schema.json"
                      )
              );
    }
}