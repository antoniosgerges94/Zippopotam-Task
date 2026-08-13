package tests;

import base.BaseTest;
import clients.ZippopotamClient;
import data.ZippopotamTestData;
import io.restassured.response.Response;
import models.Place;
import models.ZippopotamResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class ZippopotamPositiveTest extends BaseTest {

    private ZippopotamClient client;

    @BeforeClass
    public void initializeClient() {
        client = new ZippopotamClient();
    }

    @Test(
            dataProvider = "validLocations",
            dataProviderClass = ZippopotamTestData.class
    )
    public void shouldReturnLocationForValidPostalCode(
            String country,
            String postalCode,
            String expectedCountry) {

        logger.info(
                "Testing {}/{}",
                country,
                postalCode
        );

        Response response = client.getLocation(country, postalCode);

        response.then()
                .statusCode(200)
                .contentType("application/json")
                .body("country", equalTo(expectedCountry))
                .body("places", not(empty()));
    }

    @Test
    public void shouldReturnCorrectBeverlyHillsInformation() {

        Response response =
                client.getLocation("us", "90210");

        response.then()
                .statusCode(200)
                .contentType("application/json")
                .body("country", equalTo("United States"))
                .body("places", not(empty()));

        ZippopotamResponse location =
                response.as(ZippopotamResponse.class);

        assertEquals(location.getPostCode(), "90210");
        assertEquals(location.getCountry(), "United States");
        assertEquals(location.getCountryAbbreviation(), "US");

        assertFalse(location.getPlaces().isEmpty());

        Place place = location.getPlaces().get(0);

        assertEquals(place.getPlaceName(), "Beverly Hills");
        assertEquals(place.getState(), "California");
        assertEquals(place.getStateAbbreviation(), "CA");

        assertNotNull(place.getLatitude());
        assertNotNull(place.getLongitude());
    }

    @Test
    public void shouldReturnCompletePlaceInformation() {

        Response response =
                client.getLocation("us", "90210");

        response.then()
                .statusCode(200)
                .contentType("application/json");

        ZippopotamResponse location =
                response.as(ZippopotamResponse.class);

        assertNotNull(location);
        assertNotNull(location.getPostCode());
        assertNotNull(location.getCountry());
        assertNotNull(location.getCountryAbbreviation());
        assertNotNull(location.getPlaces());

        assertFalse(location.getPlaces().isEmpty());

        for (Place place : location.getPlaces()) {

            assertNotNull(place.getPlaceName());
            assertFalse(place.getPlaceName().isBlank());

            assertNotNull(place.getLongitude());
            assertFalse(place.getLongitude().isBlank());

            assertNotNull(place.getLatitude());
            assertFalse(place.getLatitude().isBlank());

            assertNotNull(place.getState());
            assertFalse(place.getState().isBlank());

            assertNotNull(place.getStateAbbreviation());
            assertFalse(place.getStateAbbreviation().isBlank());
        }
    }
}