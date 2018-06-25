package data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import model.PhotoDTO;
import io.restassured.http.ContentType;

import java.util.List;

import static io.restassured.RestAssured.*;

public class GetData {

    private String baseURL = "https://api.nasa.gov";
    private String service = "mars-photos/api/v1/rovers/{rover}/photos";
    private String apiKey = "rGltefJ0QxYGVJr9Tx7vfbC2sGSh86qCJjqRGbpe";

    protected List<PhotoDTO> get(final String roverName, final int sol) {
        return get(roverName, "sol", Integer.toString(sol));
    }

    protected List<PhotoDTO> get(final String roverName, final String earthDate) {
        return get(roverName, "earth_date", earthDate);
    }

    private List<PhotoDTO> get(final String roverName, final String paramName, final String paramValue) {

        return given()
                .spec(new RequestSpecBuilder()
                        .setBaseUri(baseURL)
                        .addPathParam("rover", roverName)
                        .addQueryParam(paramName, paramValue)
                        .addQueryParam("api_key", apiKey)
                        .build())
                .when()
                .get(service)
                .jsonPath()
                .getList("photos", PhotoDTO.class);
    }

}
