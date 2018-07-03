import helpers.MarsPhotoHelper;
import models.dto.PhotoDTO;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import steps.MarsPhotosSteps;
import utils.*;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SerenityRunner.class)
public class MarsPhotoServiceTest {
    private MarsPhotosSteps marsPhotosSteps = new MarsPhotosSteps();
    private MarsPhotoHelper marsPhotoHelper = new MarsPhotoHelper();

    private int sol = Integer.parseInt(PropertyReaderUtil.getProperty("sol"));
    private String roverName = PropertyReaderUtil.getProperty("rover.name");
    private int amount = Integer.parseInt(PropertyReaderUtil.getProperty("amount"));

    @Before
    public void setUp() {
        marsPhotoHelper.downloadPhotos(roverName, sol, amount);
    }

    @Test
    public void verifyMetadataOfMarsPhotoService() {
        List<PhotoDTO> actualPhotosWithSolDate = marsPhotosSteps.getMetadataBySolDate(roverName, sol, amount);
        List<PhotoDTO> actualPhotosWithEarthDate = marsPhotosSteps.getMetadataByEarthDate(roverName,
                ConvertDateUtil.countEarthDate(sol, roverName), amount);

        assertEquals("Metadata from the resources is different but should be similar",
                actualPhotosWithEarthDate,
                actualPhotosWithSolDate);
    }

    @Test
    public void verifyPhotosOfMarsPhotoService() {
        File directoryWithEarthDatePhotos = marsPhotoHelper.getDirectoryWithEarthDatePhotos();
        File directoryWithSolDatePhotos = marsPhotoHelper.getDirectoryWithSolDatePhotos();
        Assert.assertTrue(ComparePhotoUtil.areImagesInDirectoriesEqual(directoryWithEarthDatePhotos,
                directoryWithSolDatePhotos));
    }

    @After
    public void tearDown() {
        marsPhotoHelper.removeDirectoriesWithPhotos();
    }
}

