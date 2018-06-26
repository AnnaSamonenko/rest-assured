package tests;

import helpers.MarsPhotoHelper;
import org.junit.*;
import utils.*;

import java.io.File;

import static org.junit.Assert.assertTrue;

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
        File file1 = new File(dirWithEarthDateImages);
        File file2 = new File(dirWithSolDateImages);

        assertTrue("",
                ComparatorOfImagesUtil.imagesAreEqual(file1, file2));
    }

    @After
    public void clean() {
        helper.removeDirectoriesWithPhotosFromMars();
    }

}
