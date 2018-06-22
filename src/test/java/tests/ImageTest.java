package tests;

import model.PhotoDTO;
import org.junit.*;
import utils.ProcessData;
import utils.ComparatorOfImages;
import utils.Converter;
import utils.Downloader;
import utils.PropertyReader;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImageTest {
    private ProcessData processData = new ProcessData();

    private static int sol = Integer.valueOf(PropertyReader.getProperty("sol"));
    private static int quantityOfPhotos = Integer.valueOf(PropertyReader.getProperty("photos.quantity"));
    private static String dirWithEarthDateImages = PropertyReader.getProperty("photos.earth.date.dir");
    private static String dirWithSolDateImages = PropertyReader.getProperty("photos.sol.dir");
    private static String roverName = PropertyReader.getProperty("rover.name");

    @Before
    public void downloadImages() throws Exception {
        Downloader.downloadPictures(PropertyReader.getProperty("photos.earth.date.dir"),
                processData.getListOfUrls(roverName, Converter.countEarthDate(sol, roverName), quantityOfPhotos));

        Downloader.downloadPictures(PropertyReader.getProperty("photos.sol.dir"),
                processData.getListOfUrls(roverName, sol, quantityOfPhotos));
    }

    @Test
    public void testMetadataFromMarsPhotosService() throws Exception {
        List<PhotoDTO> expectedPhotosWithSolDate = processData.getPhotos(roverName, sol, quantityOfPhotos);
        List<PhotoDTO> expectedPhotosWithEarthDate = processData.getPhotos(roverName, Converter.countEarthDate(sol, roverName),
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

    }

}
