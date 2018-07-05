package resources;

import io.restassured.response.Response;
import io.restassured.http.ContentType;


import static io.restassured.RestAssured.*;

public class MarsPhotosEndpoint {

    private String endpoint = "https://api.nasa.gov/mars-photos/api/v1/rovers/{rover}/photos";
    private String apiKey = "rGltefJ0QxYGVJr9Tx7vfbC2sGSh86qCJjqRGbpe";

    public Response get(final String roverName, final int sol) {
        return given()
                .pathParam("rover", roverName)
                .queryParam("sol", sol)
                .queryParam("api_key", apiKey)
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response get(final String roverName, final String earthDate) {
        return given()
                .pathParam("rover", roverName)
                .queryParam("earth_date", earthDate)
                .queryParam("api_key", apiKey)
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

}