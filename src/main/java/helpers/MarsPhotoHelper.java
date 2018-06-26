package helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import model.dto.PhotoDTO;
import utils.*;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class MarsPhotoHelper {

    private int sol = Integer.valueOf(PropertyReaderUtil.getProperty("sol"));
    private String dirWithEarthDateImages = PropertyReaderUtil.getProperty("photos.earth.date.dir");
    private String dirWithSolDateImages = PropertyReaderUtil.getProperty("photos.sol.dir");
    private String roverName = PropertyReaderUtil.getProperty("rover.name");
    private int quantity = Integer.valueOf(PropertyReaderUtil.getProperty("photos.quantity"));
    private String baseUrl = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos";
    private String apiKey = "rGltefJ0QxYGVJr9Tx7vfbC2sGSh86qCJjqRGbpe";

    public void downloadImagesFromMars() {
        FileDownloaderUtil.downloadFile(dirWithEarthDateImages, getUrlsToEarthDatePictures());
        FileDownloaderUtil.downloadFile(dirWithSolDateImages, getUrlsToSolDatePictures());
    }

    public void removeDirectoriesWithPhotosFromMars() {
        FileUtil.removeDirectory(dirWithSolDateImages);
        FileUtil.removeDirectory(dirWithEarthDateImages);
    }

    public File getDirectoryToImagesWithSolDate(){
        return new File(dirWithEarthDateImages);
    }

    public File getDirectoryToImagesWithEarthDate(){
        return new File(dirWithEarthDateImages);
    }

    /**
     * Method for get defined quantity of photos from
     * the mars photo endpoint with earth query param
     *
     * @return list with images
     */
    public List<PhotoDTO> getPhotosFromMarsByEarthDate() {
        return parseResponseToListOfPhotosWithEarthDate()
                .stream()
                .limit(quantity)
                .collect(Collectors.toList());
    }

    /**
     * Method for get defined quantity of photos from
     * the mars photo endpoint with sol query param
     *
     * @return list with images
     */
    public List<PhotoDTO> getPhotosFromMarsBySolDate() {
        return parseResponseToListOfPhotosWithSolDate()
                .stream()
                .limit(quantity)
                .collect(Collectors.toList());
    }

    /**
     * Method for get url to images
     *
     * @return list with images
     */
    private List<String> getUrlsToSolDatePictures() {
        return getPhotosFromMarsBySolDate()
                .stream()
                .map(PhotoDTO::getImg_src)
                .collect(Collectors.toList());
    }

    /**
     * Method for get url to images
     *
     * @return list with images
     */
    private List<String> getUrlsToEarthDatePictures() {
        return getPhotosFromMarsByEarthDate()
                .stream()
                .map(PhotoDTO::getImg_src)
                .collect(Collectors.toList());
    }

    /**
     * Method for parsing response from mars-photo service with sol param
     *
     * @return list with images
     */
    private List<PhotoDTO> parseResponseToListOfPhotosWithSolDate() {
        return RestUtil
                .receiveResponseByGet(buildUrl("sol", Integer.toString(sol)))
                .jsonPath()
                .getList("photos", PhotoDTO.class);
    }

    /**
     * Method for parsing response from mars-photo service with earth_date param
     *
     * @return list with images
     */
    private List<PhotoDTO> parseResponseToListOfPhotosWithEarthDate() {
        return RestUtil
                .receiveResponseByGet(buildUrl("earth_date",
                        DateConverterUtil.countEarthDate(sol, roverName)))
                .jsonPath()
                .getList("photos", PhotoDTO.class);
    }

    /**
     * Method for build url
     *
     * @param paramName
     * @param paramValue
     * @return RequestSpecification object which will be used for build url
     */
    private RequestSpecification buildUrl(String paramName, String paramValue) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addQueryParam(paramName, paramValue)
                .addQueryParam("api_key", apiKey)
                .addParam("")
                .build();
    }

}
