package endpoint;

import dto.Photo;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import utils.PropertyReader;

import java.util.List;

import static io.restassured.RestAssured.*;

public class MarsPhotosEndpoint {

    // TODO: add path param for a rover

    final static Logger logger = Logger.getLogger(PropertyReader.class);
    private String endpoint = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos";
    private String api_key = "rGltefJ0QxYGVJr9Tx7vfbC2sGSh86qCJjqRGbpe";

    public List<Photo> marsPhotosEndpointWithSol(final int sol) {
        logger.error("Verify work of logger");
        return given()
                .queryParam("sol", sol)
                .queryParam("api_key", api_key)
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint).jsonPath().getList("photos", Photo.class);
    }

    public List<Photo> marsPhotosEndpointWithEarthDate(final String earthDate) {
        return given()
                .queryParam("earth_date", earthDate)
                .queryParam("api_key", api_key)
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint).jsonPath().getList("photos", Photo.class);
    }

}
