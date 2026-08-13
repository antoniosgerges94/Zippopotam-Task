package clients;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ZippopotamClient {

    private static final String LOCATION_ENDPOINT =
            "/{country}/{postalCode}";

    public Response getLocation(
            String country,
            String postalCode) {

        return given()
                .pathParam("country", country)
                .pathParam("postalCode", postalCode)
                .when()
                .get(LOCATION_ENDPOINT);
    }
}