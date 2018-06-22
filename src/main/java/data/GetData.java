package data;

import model.PhotoDTO;
import io.restassured.http.ContentType;

import java.util.List;

import static io.restassured.RestAssured.*;

public class GetData {

    private String url = "https://api.nasa.gov/mars-photos/api/v1/rovers/{rover}/photos";
    private String apiKey = "rGltefJ0QxYGVJr9Tx7vfbC2sGSh86qCJjqRGbpe";

    protected List<PhotoDTO> get(final String roverName, final int sol) {
        return given()
                .pathParam("rover", roverName)
                .queryParam("sol", sol)
                .queryParam("api_key", apiKey)
                .contentType(ContentType.JSON)
                .when()
                .get(url).jsonPath().getList("photos", PhotoDTO.class);
    }

    protected List<PhotoDTO> get(final String roverName, final String earthDate) {
        return given()
                .pathParam("rover", roverName)
                .queryParam("earth_date", earthDate)
                .queryParam("api_key", apiKey)
                .contentType(ContentType.JSON)
                .when()
                .get(url).jsonPath().getList("photos", PhotoDTO.class);
    }

}
