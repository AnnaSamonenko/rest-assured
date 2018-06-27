package helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import model.dto.PhotoDTO;
import utils.*;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class MarsPhotoHelper {

    private String dirWithEarthDateImages = PropertyReaderUtil.getProperty("photos.earth.date.dir");
    private String dirWithSolDateImages = PropertyReaderUtil.getProperty("photos.sol.dir");
    private String roverName = PropertyReaderUtil.getProperty("rover.name");
    private String path = "src\\test\\resources\\";

    public void downloadImagesOfMars(int N, int sol) {
        String earthDate = ConvertDateUtil.countEarthDate(sol, roverName);
        DownloadFileUtil.downloadFile(dirWithEarthDateImages, getUrlsFromPhotoDtosByEarthDate(N, earthDate));
        DownloadFileUtil.downloadFile(dirWithSolDateImages, getUrlsFromPhotoDtosBySolDate(N, sol));
    }

    public void removeDirectoriesWithPhotosOfMars() {
        FileUtil.removeDirectory(path + dirWithSolDateImages);
        FileUtil.removeDirectory(path + dirWithEarthDateImages);
    }

    public File getDirectoryToImagesWithSolDate() {
        return new File(path + dirWithEarthDateImages);
    }

    public File getDirectoryToImagesWithEarthDate() {
        return new File(path + dirWithEarthDateImages);
    }

    /**
     * Method for get url to images
     *
     * @return list with images
     */
    private List<String> getUrlsFromPhotoDtosBySolDate(int N, int sol) {
        return takeFirstNPhotosDtosBySolDate(N, sol)
                .stream()
                .map(PhotoDTO::getImg_src)
                .collect(Collectors.toList());
    }

    /**
     * Method for get url to images
     *
     * @return list with images
     */
    private List<String> getUrlsFromPhotoDtosByEarthDate(int N, String earthDate) {
        return takeFirstNPhotosDtoByEarthDate(N, earthDate)
                .stream()
                .map(PhotoDTO::getImg_src)
                .collect(Collectors.toList());
    }

    /**
     * Method for get defined quantity of photos from
     * the mars photo endpoint with earth query param
     *
     * @return list with images
     */
    public List<PhotoDTO> takeFirstNPhotosDtoByEarthDate(int N, String earthDate) {
        return parseResponseToGetPhotosMetadataByEarthDate(earthDate)
                .stream()
                .limit(N)
                .collect(Collectors.toList());
    }

    /**
     * Method for get defined quantity of photos from
     * the mars photo endpoint with sol query param
     *
     * @return list with images
     */
    public List<PhotoDTO> takeFirstNPhotosDtosBySolDate(int N, int sol) {
        return parseResponseToGetPhotosMetadataBySolDate(sol)
                .stream()
                .limit(N)
                .collect(Collectors.toList());
    }

    /**
     * Method for parsing response from mars-photo service with sol param
     *
     * @return list with images
     */
    private List<PhotoDTO> parseResponseToGetPhotosMetadataBySolDate(int sol) {
        return RestUtil
                .getResponse(buildUrl("sol", Integer.toString(sol)))
                .jsonPath()
                .getList("photos", PhotoDTO.class);
    }

    /**
     * Method for parsing response from mars-photo service with earth_date param
     *
     * @return list with images
     */
    private List<PhotoDTO> parseResponseToGetPhotosMetadataByEarthDate(String earthDate) {
        return RestUtil
                .getResponse(buildUrl("earth_date", earthDate))
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
        String baseUrl = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos";
        String apiKey = "rGltefJ0QxYGVJr9Tx7vfbC2sGSh86qCJjqRGbpe";

        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addQueryParam(paramName, paramValue)
                .addQueryParam("api_key", apiKey)
                .addParam("")
                .build();
    }

}
