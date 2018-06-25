package data;

import io.restassured.builder.RequestSpecBuilder;
import model.PhotoDTO;

import java.util.List;

import static io.restassured.RestAssured.*;

public class GetData {

    protected List<PhotoDTO> get(final String roverName, final int sol) {
        return get(roverName, "sol", Integer.toString(sol));
    }

    protected List<PhotoDTO> get(final String roverName, final String earthDate) {
        return get(roverName, "earth_date", earthDate);
    }

    private List<PhotoDTO> get(final String roverName, final String paramName, final String paramValue) {
        return given()
                .spec(new RequestSpecBuilder()
                        .setBaseUri("https://api.nasa.gov")
                        .addPathParam("rover", roverName)
                        .addQueryParam(paramName, paramValue)
                        .addQueryParam("api_key", "rGltefJ0QxYGVJr9Tx7vfbC2sGSh86qCJjqRGbpe")
                        .build())
                .when()
                .get("mars-photos/api/v1/rovers/{rover}/photos")
                .jsonPath()
                .getList("photos", PhotoDTO.class);
    }

}
