package tests;

import dto.PhotoDTO;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import steps.MarsPhotosSteps;
import utils.ComparatorOfImages;
import utils.Converter;
import utils.Downloader;
import utils.PropertyReader;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImageTest {
    private MarsPhotosSteps marsPhotosSteps = new MarsPhotosSteps();

    private static int sol = Integer.valueOf(PropertyReader.getProperty("sol"));
    private static int quantityOfPhotos = Integer.valueOf(PropertyReader.getProperty("photos.quantity"));
    private static String dirWithEarthDateImages = PropertyReader.getProperty("photos.earth.date.dir");
    private static String dirWithSolDateImages = PropertyReader.getProperty("photos.sol.dir");
    private static String roverName = PropertyReader.getProperty("rover.name");

    @Before
    public void downloadImages() {
        Downloader.downloadPictures(PropertyReader.getProperty("photos.earth.date.dir"),
                marsPhotosSteps.getListOfUrls(roverName, Converter.countEarthDate(sol), quantityOfPhotos));

        Downloader.downloadPictures(PropertyReader.getProperty("photos.sol.dir"),
                marsPhotosSteps.getListOfUrls(roverName, sol, quantityOfPhotos));
    }

    @Test
    public void testMetadataFromMarsPhotosService() {
        List<PhotoDTO> expectedPhotosWithSolDate = marsPhotosSteps.getPhotos(roverName, sol, quantityOfPhotos);
        List<PhotoDTO> expectedPhotosWithEarthDate = marsPhotosSteps.getPhotos(roverName, Converter.countEarthDate(sol),
                quantityOfPhotos);

        assertEquals("Metadata from the resources is different but should be similar",
                expectedPhotosWithEarthDate,
                expectedPhotosWithSolDate);
    }

    @Test
    public void testImagesFromMarsPhotosService() {
        File file1 = new File(dirWithEarthDateImages);
        File file2 = new File(dirWithSolDateImages);
        assertTrue(ComparatorOfImages.imagesAreEqual(file1, file2));
    }

    @After
    public void clean() throws Exception {
        FileUtils.deleteDirectory(new File(PropertyReader.getProperty("photos.earth.date.dir")));
        FileUtils.deleteDirectory(new File(PropertyReader.getProperty("photos.sol.dir")));
    }

}
