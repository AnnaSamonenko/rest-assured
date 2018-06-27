package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestUtil {

    private RestUtil() {
    }

    public static Response getResponse(RequestSpecification reqSpec) {
        return given()
                .spec(reqSpec)
                .when()
                .get();
    }

}
