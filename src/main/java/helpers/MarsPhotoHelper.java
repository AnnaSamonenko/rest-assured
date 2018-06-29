package helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import model.dto.PhotoDTO;
import utils.*;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class MarsPhotoHelper {

    /**
     * Title of directory which will contain photos from mars-photo service with earth_date parameter.
     */
    private String dirWithEarthDatePhotos = PropertyReaderUtil.getProperty("photos.earth.date.dir");

    /**
     * Title of directory which will contain photos from mars-photo service with sol parameter.
     */
    private String dirWithSolDatePhotos = PropertyReaderUtil.getProperty("photos.sol.dir");

    /**
     * Path to resource folder which will contain directories with photos from mars-photo service.
     */
    private static final String PATH = "src\\test\\resources\\";

    /**
     * Method which download defined quantity of photos from mars-photos service.
     *
     * @param quantity  is amount of photos.
     * @param sol       is a duration of a sonar day on Mars.
     * @param earthDate is a earth date in yyyy-MM-dd format.
     */
    public void downloadFirstNImagesOfMars(int quantity, int sol, String earthDate) {
        DownloadFileUtil.downloadFile(dirWithEarthDatePhotos, getUrlsFromPhotoDtosByEarthDate(quantity, earthDate));
        DownloadFileUtil.downloadFile(dirWithSolDatePhotos, getUrlsFromPhotoDtosBySolDate(quantity, sol));
    }

    /**
     * Method for removing directories with photos.
     */
    public void removeDirectoriesWithPhotosOfMars() {
        FileUtil.removeDirectory(PATH + dirWithSolDatePhotos);
        FileUtil.removeDirectory(PATH + dirWithEarthDatePhotos);
    }

    /**
     * @return new File instance of directory with sol date photos.
     */
    public File getDirectoryWithImagesBySolDate() {
        return new File(PATH + dirWithEarthDatePhotos);
    }

    /**
     * @return new File instance of directory with earth date photos.
     */
    public File getDirectoryWithImagesByEarthDate() {
        return new File(PATH + dirWithEarthDatePhotos);
    }

    /**
     * Method for get url to images
     *
     * @param quantity is amount of photos.
     * @param sol      is a duration of a sonar day on Mars.
     * @return list with images
     */
    private List<String> getUrlsFromPhotoDtosBySolDate(int quantity, int sol) {
        return takeFirstNPhotoDtosBySolDate(quantity, sol)
                .stream()
                .map(PhotoDTO::getImg_src)
                .collect(Collectors.toList());
    }

    /**
     * Method for get url to images
     *
     * @param quantity  is amount of photos.
     * @param earthDate is a earth date in yyyy-MM-dd format.
     * @return list with images
     */
    private List<String> getUrlsFromPhotoDtosByEarthDate(int quantity, String earthDate) {
        return takeFirstNPhotoDtosByEarthDate(quantity, earthDate)
                .stream()
                .map(PhotoDTO::getImg_src)
                .collect(Collectors.toList());
    }

    /**
     * Method for get defined quantity of photos from
     * the mars photo endpoint with earth query param.
     *
     * @param quantity  is amount of photos.
     * @param earthDate is a earth date in yyyy-MM-dd format.
     * @return list of photo DTOs with defined size.
     */
    public List<PhotoDTO> takeFirstNPhotoDtosByEarthDate(int quantity, String earthDate) {
        return parseResponseToGetPhotosMetadataByEarthDate(earthDate)
                .stream()
                .limit(quantity)
                .collect(Collectors.toList());
    }

    /**
     * Method for get defined quantity of photos from
     * the mars-photo service with sol query parameter.
     *
     * @param quantity is amount of photos.
     * @param sol      is a duration of a sonar day on Mars.
     * @return list of photo DTOs with defined size.
     */
    public List<PhotoDTO> takeFirstNPhotoDtosBySolDate(int quantity, int sol) {
        return parseResponseToGetPhotosMetadataBySolDate(sol)
                .stream()
                .limit(quantity)
                .collect(Collectors.toList());
    }

    /**
     * Method for parsing response from mars-photo service with sol parameter.
     *
     * @param sol is a duration of a sonar day on Mars.
     * @return list of photo DTOs.
     */
    private List<PhotoDTO> parseResponseToGetPhotosMetadataBySolDate(int sol) {
        return RestUtil
                .getResponse(buildUrl("sol", Integer.toString(sol)))
                .jsonPath()
                .getList("photos", PhotoDTO.class);
    }

    /**
     * Method for parsing response from mars-photo service with earth_date parameter.
     *
     * @param earthDate is a earth date in yyyy-MM-dd format.
     * @return list of photo DTOs.
     */
    private List<PhotoDTO> parseResponseToGetPhotosMetadataByEarthDate(String earthDate) {
        return RestUtil
                .getResponse(buildUrl("earth_date", earthDate))
                .jsonPath()
                .getList("photos", PhotoDTO.class);
    }

    /**
     * Method for build url.
     *
     * @param paramName  is name of parameter of query string.
     * @param paramValue is value of parameter of query string.
     * @return RequestSpecification object which will be used for build url.
     */
    private RequestSpecification buildUrl(String paramName, String paramValue) {
        String baseUrl = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos";
        String apiKey = "rGltefJ0QxYGVJr9Tx7vfbC2sGSh86qCJjqRGbpe";

        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addQueryParam(paramName, paramValue)
                .addQueryParam("api_key", apiKey)
                .build();
    }

}
