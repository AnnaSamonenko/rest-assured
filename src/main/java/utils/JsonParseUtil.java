package utils;


import com.google.gson.*;
import io.restassured.response.Response;
import models.dto.CameraDTO;
import models.dto.PhotoDTO;
import models.dto.RoverDTO;

import java.util.LinkedList;
import java.util.List;

public class JsonParseUtil {

    private JsonParseUtil() {
    }

    public static List<PhotoDTO> parseJson(Response response, int quantity) {
        List<PhotoDTO> photoDTOS = new LinkedList<>();
        JsonElement jsonResponse = new JsonParser().parse(response.body().asString());
        JsonArray jsonOfPhotos = jsonResponse.getAsJsonObject().get("photos").getAsJsonArray();

        for (int i = 0; i < quantity; i++) {
            PhotoDTO photoDTO = new PhotoDTO();
            photoDTO.setId(Integer.parseInt(jsonOfPhotos.get(i).getAsJsonObject().get("id").toString()));
            photoDTO.setImg_src(jsonOfPhotos.get(i).getAsJsonObject().get("img_src").toString());
            photoDTO.setEarth_date(jsonOfPhotos.get(i).getAsJsonObject().get("earth_date").toString());
            photoDTO.setSol(Integer.parseInt(jsonOfPhotos.get(i).getAsJsonObject().get("sol").toString()));
            photoDTO.setCamera(parseJsonOfCamera(jsonOfPhotos.get(i).getAsJsonObject().get("camera").getAsJsonObject()));
            photoDTO.setRover(parseJsonOfRover(jsonOfPhotos.get(i).getAsJsonObject().get("rover").getAsJsonObject()));
            photoDTOS.add(photoDTO);
        }
        return photoDTOS;
    }

    private static CameraDTO parseJsonOfCamera(JsonObject jsonObject) {
        Gson gson = new Gson();
        return gson.fromJson(jsonObject, CameraDTO.class);
    }

    private static RoverDTO parseJsonOfRover(JsonObject jsonObject) {
        Gson gson = new Gson();
        return gson.fromJson(jsonObject, RoverDTO.class);
    }

}
