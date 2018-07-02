import model.dto.PhotoDTO;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.MarsPhotosSteps;
import utils.*;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SerenityRunner.class)
public class MarsPhotoServiceTest {
    private MarsPhotosSteps marsPhotosSteps = new MarsPhotosSteps();
    private String earthDateDirectoryName = PropertyReaderUtil.getProperty("photos.earth.date.dir");
    private String solDateDirectoryName = PropertyReaderUtil.getProperty("photos.sol.dir");
    private int sol = Integer.parseInt(PropertyReaderUtil.getProperty("sol"));
    private String roverName = PropertyReaderUtil.getProperty("rover.name");
    private int amount = Integer.parseInt(PropertyReaderUtil.getProperty("amount"));

    @Before
    public void setUp() {
        DownloadFileUtil.downloadFile(earthDateDirectoryName,
                marsPhotosSteps.getListOfUrls(roverName, ConvertDateUtil.countEarthDate(sol, roverName), amount));
        DownloadFileUtil.downloadFile(solDateDirectoryName,
                marsPhotosSteps.getListOfUrls(roverName, sol, amount));
    }

    @Test
    public void verifyMetadataOfMarsPhotoService() {
        List<PhotoDTO> expectedPhotosWithSolDate = marsPhotosSteps.getPhotos(roverName, sol, amount);
        List<PhotoDTO> expectedPhotosWithEarthDate = marsPhotosSteps.getPhotos(roverName, ConvertDateUtil.countEarthDate(sol, roverName), amount);

        assertEquals("Metadata from the resources is different but should be similar",
                expectedPhotosWithEarthDate,
                expectedPhotosWithSolDate);
    }

    @Test
    public void verifyPhotosOfMarsPhotoService() {
        File file1 = new File(earthDateDirectoryName);
        File file2 = new File(solDateDirectoryName);
        Assert.assertTrue(ComparePhotoUtil.areImagesInDirectoriesEqual(file1, file2));
    }

    @After
    public void tearDown() {
        FileUtil.removeDirectory(earthDateDirectoryName);
        FileUtil.removeDirectory(solDateDirectoryName);
    }
}

