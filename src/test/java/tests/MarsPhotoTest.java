package tests;

import helpers.MarsPhotoHelper;
import org.junit.*;
import utils.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class MarsPhotoTest {

    private MarsPhotoHelper helper = new MarsPhotoHelper();
    private int quantity = Integer.parseInt(PropertyReaderUtil.getProperty("photos.quantity"));
    private int sol = Integer.parseInt(PropertyReaderUtil.getProperty("sol"));
    private String rover = PropertyReaderUtil.getProperty("rover.name");

    @Before
    public void downloadImages() {
        helper.downloadImagesOfMars(quantity, sol);
    }

    @Test
    public void testMetadataFromMarsPhotosService() {
        String earthDate = ConvertDateUtil.countEarthDate(sol, rover);
        assertEquals("Metadata from the resources is different but should be similar",
                helper.takeFirstNPhotosDtoByEarthDate(quantity, earthDate),
                helper.takeFirstNPhotosDtosBySolDate(quantity, sol));
    }

    @Test
    public void testImagesFromMarsPhotosService() {
        assertTrue("Images is different",
                CompareImageUtil.areImagesInDirectoriesEqual(
                        helper.getDirectoryToImagesWithSolDate(),
                        helper.getDirectoryToImagesWithEarthDate())
        );
    }

    @After
    public void clean() {
        helper.removeDirectoriesWithPhotosOfMars();
    }

}
