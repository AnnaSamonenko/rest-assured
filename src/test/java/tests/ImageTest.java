package steps.business;

import dto.PhotoDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.flow.MarsPhotosSteps;
import utils.ComparatorOfImages;
import utils.Converter;
import utils.Downloader;
import utils.PropertyReader;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ImageDefinitionSteps {
    private MarsPhotosSteps marsPhotosSteps = new MarsPhotosSteps();

    private static int sol = Integer.valueOf(PropertyReader.getProperty("sol"));
    private static int quantityOfPhotos = Integer.valueOf(PropertyReader.getProperty("photos.quantity"));
    private static String dirWithEarthDateImages = PropertyReader.getProperty("photos.earth.date.dir");
    private static String dirWithSolDateImages = PropertyReader.getProperty("photos.sol.dir");

    private List<PhotoDTO> expectedPhotosWithEarthDate;
    private List<PhotoDTO> expectedPhotosWithSolDate;

    @Before
    public void downloadImages() {
        Downloader.downloadPictures(PropertyReader.getProperty("photos.earth.date.dir"),
                marsPhotosSteps.getListOfUrls(Converter.getEarthDate(sol), quantityOfPhotos));

        Downloader.downloadPictures(PropertyReader.getProperty("photos.sol.dir"),
                marsPhotosSteps.getListOfUrls(sol, quantityOfPhotos));
    }

    @Test
    public void testMetadataFromMarsPhotosService() {
        expectedPhotosWithSolDate = marsPhotosSteps.getPhoto(sol, quantityOfPhotos);
        expectedPhotosWithEarthDate = marsPhotosSteps.getPhoto(Converter.getEarthDate(sol), quantityOfPhotos);

        assertEquals("Metadata from the endpoint is different but should be similar",
                expectedPhotosWithEarthDate,
                expectedPhotosWithSolDate);
    }

    @Test
    public void testImagesFromMarsPhotosService() {
        File file1 = new File(dirWithEarthDateImages);
        File file2 = new File(dirWithSolDateImages);
        ComparatorOfImages.imagesIsEqual(file1, file2);
    }

    @After
    public void clean() {

    }

}
