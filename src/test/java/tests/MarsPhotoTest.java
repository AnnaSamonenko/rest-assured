package tests;

import helpers.MarsPhotoHelper;
import org.junit.*;
import utils.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class MarsPhotoTest {

    private MarsPhotoHelper helper = new MarsPhotoHelper();

    @Before
    public void downloadImages() {
        helper.downloadImagesFromMars();
    }

    @Test
    public void testMetadataFromMarsPhotosService() {
        assertEquals("Metadata from the resources is different but should be similar",
                helper.getPhotosFromMarsBySolDate(),
                helper.getPhotosFromMarsByEarthDate());
    }

    @Test
    public void testImagesFromMarsPhotosService() {
        assertTrue("Images is different",
                ComparatorOfImagesUtil.imagesAreEqual(
                        helper.getDirectoryToImagesWithSolDate(),
                        helper.getDirectoryToImagesWithEarthDate())
        );
    }

    @After
    public void clean() {
        helper.removeDirectoriesWithPhotosFromMars();
    }

}
