package endpoint;

import dto.Photo;
import io.restassured.http.ContentType;

import java.util.List;

import static io.restassured.RestAssured.*;

public class MarsPhotosEndpoint {

    // TODO: add path param for a rover
    // TODO: user api key

    private String endpoint = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos";

    public List<Photo> marsPhotosEndpointWithSol(int sol) {
        return given()
                .queryParam("sol", sol)
                .queryParam("api_key", "DEMO_KEY")
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint).jsonPath().getList("photos", Photo.class);
    }

    public List<Photo> marsPhotosEndpointWithEarthDate(String earthDate) {
        return given()
                .queryParam("earth_date", earthDate)
                .queryParam("api_key", "DEMO_KEY")
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint).jsonPath().getList("photos", Photo.class);
    }

}
