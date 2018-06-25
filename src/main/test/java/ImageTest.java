import model.PhotoDTO;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.MarsPhotosSteps;
import utils.*;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SerenityRunner.class)
public class ImageTest {
    private MarsPhotosSteps marsPhotosSteps = new MarsPhotosSteps();
    private String earthDateDirectoryName = PropertyReader.getProperty("photos.earth.date.dir");
    private String solDateDirectoryName = PropertyReader.getProperty("photos.sol.dir");

    @Before
    public void downloadImages() throws ParseException {
        Downloader.downloadPictures(earthDateDirectoryName,
                marsPhotosSteps.getListOfUrls(roverName, Converter.countEarthDate(sol, roverName), amount));
        Downloader.downloadPictures(solDateDirectoryName,
                marsPhotosSteps.getListOfUrls(roverName, sol, amount));
    }

    @Test
    public void verifyMetadata(final int sol, final String roverName, final int amount)
            throws ParseException {
        List<PhotoDTO> expectedPhotosWithSolDate = marsPhotosSteps.getPhotos(roverName, sol, amount);
        List<PhotoDTO> expectedPhotosWithEarthDate = marsPhotosSteps.getPhotos(roverName, Converter.countEarthDate(sol, roverName), amount);

        assertEquals("Metadata from the resources is different but should be similar",
                expectedPhotosWithEarthDate,
                expectedPhotosWithSolDate);
    }

    @Test
    public void verifyImages() {
        File file1 = new File(earthDateDirectoryName);
        File file2 = new File(solDateDirectoryName);
        ComparatorOfImages.imagesAreEqual(file1, file2);
    }

    @After
    public void deleteDirectories() {
        FileUtils.removeDirectory(earthDateDirectoryName);
        FileUtils.removeDirectory(solDateDirectoryName);
    }
}

