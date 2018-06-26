package helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import model.dto.PhotoDTO;
import utils.*;

import java.util.*;
import java.util.stream.Collectors;

public class MarsPhotoHelper {

    private int sol = Integer.valueOf(PropertyReaderUtil.getProperty("sol"));
    private String dirWithEarthDateImages = PropertyReaderUtil.getProperty("photos.earth.date.dir");
    private String dirWithSolDateImages = PropertyReaderUtil.getProperty("photos.sol.dir");
    private String roverName = PropertyReaderUtil.getProperty("rover.name");
    private int quantity = Integer.valueOf(PropertyReaderUtil.getProperty("photos.quantity"));
    private String baseUrl = "https://api.nasa.gov/mars-photos/api/v1/rovers/{rover}/photos";
    private String apiKey = "rGltefJ0QxYGVJr9Tx7vfbC2sGSh86qCJjqRGbpe";

    public void downloadImagesFromMars() {
        FileDownloaderUtil.downloadFile(dirWithEarthDateImages, getUrlsToEarthDatePictures());
        FileDownloaderUtil.downloadFile(dirWithSolDateImages, getUrlsToSolDatePictures());
    }

    public void removeDirectoriesWithPhotosFromMars() {
        FileUtil.removeDirectory(dirWithSolDateImages);
        FileUtil.removeDirectory(dirWithEarthDateImages);
    }

    public List<PhotoDTO> getPhotosFromMarsByEarthDate() {
        return findListOfPhotosByEarthDate()
                .stream()
                .limit(quantity)
                .collect(Collectors.toList());
    }

    public List<PhotoDTO> getPhotosFromMarsBySolDate() {
        return findListOfPhotosBySolDate()
                .stream()
                .limit(quantity)
                .collect(Collectors.toList());
    }

    public List<String> getUrlsToSolDatePictures() {
        return findPhotosBySol()
                .stream()
                .limit(quantity)
                .map(PhotoDTO::getImg_src)
                .collect(Collectors.toList());
    }

    public List<String> getUrlsToEarthDatePictures() {
        return getPhotosFromMarsByEarthDate()
                .stream()
                .limit(quantity)
                .map(PhotoDTO::getImg_src)
                .collect(Collectors.toList());
    }

    private List<PhotoDTO> findListOfPhotosBySolDate() {
        return RestUtil
                .receiveResponseByGet(buildUrl("sol", Integer.toString(sol)))
                .jsonPath()
                .getList("photos", PhotoDTO.class);
    }

    private List<PhotoDTO> findListOfPhotosByEarthDate() {
        return RestUtil
                .receiveResponseByGet(buildUrl("earth_date",
                        DateConverterUtil.countEarthDate(sol, roverName)))
                .jsonPath()
                .getList("photos", PhotoDTO.class);
    }

    private RequestSpecification buildUrl(String paramName, String paramValue) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addPathParam("rover", roverName)
                .addQueryParam(paramName, paramValue)
                .addQueryParam("api_key", apiKey)
                .build();
    }

}
