package endpoint;

import dto.Photo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.*;

public class MarsPhotosEndpoint {

    private String endpoint = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos";

    public List<Photo> MarsPhotosEndpointWithSol(int sol) {
        return given()
                .queryParam("sol", sol)
                .queryParam("api_key", "DEMO_KEY")
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint).jsonPath().getList("photos", Photo.class);
    }

    public List<Photo> MarsPhotosEndpointWithEarthDate(String earthDate) {
        return given()
                .queryParam("earth_date", earthDate)
                .queryParam("api_key", "DEMO_KEY")
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint).jsonPath().getList("photos", Photo.class);
    }

}
