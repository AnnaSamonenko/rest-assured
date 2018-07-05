package utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import models.dto.PhotoDTO;

import java.util.List;

public class JsonParseUtil {

    private JsonParseUtil() {
    }

    public static List<PhotoDTO> parseJson(Response response) {
        JsonParser parser = new JsonParser();
        JsonElement jsonOfPhotos = parser.parse(response.body().asString()).getAsJsonObject().get("photos");
        return null;
    }

}
