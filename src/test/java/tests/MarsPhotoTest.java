package tests;

import helpers.MarsPhotoHelper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.*;


public class MarsPhotoTest {

    private MarsPhotoHelper helper = new MarsPhotoHelper();
    private int quantity = Integer.parseInt(PropertyReaderUtil.getProperty("photos.quantity"));
    private int sol = Integer.parseInt(PropertyReaderUtil.getProperty("sol"));
    private String rover = PropertyReaderUtil.getProperty("rover.name");

    @BeforeClass
    public void downloadImages() {
        String earthDate = ConvertDateUtil.countEarthDate(sol, rover);
        helper.downloadFirstNImagesOfMars(quantity, sol, earthDate);
    }

    @Test
    public void metadataFromMarsPhotosServiceTest() {
        String earthDate = ConvertDateUtil.countEarthDate(sol, rover);
        Assert.assertEquals(
                helper.takeFirstNPhotoDtosByEarthDate(quantity, earthDate),
                helper.takeFirstNPhotoDtosBySolDate(quantity, sol),
                "Metadata from the resources is different but should be similar");
    }

    @Test
    public void imagesFromMarsPhotosServiceTest() {
        Assert.assertTrue(
                CompareImageUtil.areImagesInDirectoriesEqual(
                        helper.getDirectoryWithImagesBySolDate(),
                        helper.getDirectoryWithImagesByEarthDate()),
                "Images are different"
        );
    }

    @AfterClass
    public void clean() {
        helper.removeDirectoriesWithPhotosOfMars();
    }

}
