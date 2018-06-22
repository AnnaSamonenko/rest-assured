package steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.PhotoDTO;
import utils.process.ProcessData;
import utils.ComparatorOfImages;
import utils.Converter;
import utils.Downloader;
import utils.PropertyReader;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ImageDefinitionSteps {
    private ProcessData processData = new ProcessData();
    private List<PhotoDTO> expectedPhotosWithEarthDate;
    private List<PhotoDTO> expectedPhotosWithSolDate;

    @When("^call Mars photos service with (\\d+) sol for (\\w+) rover for sol date for (\\d+) photos$")
    public void callMarsPhotosForEarthDateService(final int sol, final String roverName, final int amount) throws Exception {
        Downloader.downloadPictures(PropertyReader.getProperty("photos.earth.date.dir"),
                processData.getListOfUrls(roverName, Converter.countEarthDate(sol, roverName), amount));
        expectedPhotosWithSolDate = processData.getPhotos(roverName, sol, amount);
    }

    @When("^call Mars photos service with (\\d+) sol for (\\w+) rover for earth date for (\\d+) photos$")
    public void callMarsPhotosForSolDateService(final int sol, final String roverName, final int amount) throws Exception {
        Downloader.downloadPictures(PropertyReader.getProperty("photos.sol.dir"),
                processData.getListOfUrls(roverName, sol, amount));
        expectedPhotosWithEarthDate = processData.getPhotos(roverName, Converter.countEarthDate(sol, roverName), amount);
    }

    @Then("metadata is the same")
    public void verifyMatadata() {
        assertEquals("Metadata from the resources is different but should be similar",
                expectedPhotosWithEarthDate,
                expectedPhotosWithSolDate);
    }

    @Then("images are the same")
    public void verifyImages() {
        File file1 = new File(PropertyReader.getProperty("photos.earth.date.dir"));
        File file2 = new File(PropertyReader.getProperty("photos.sol.dir"));
        ComparatorOfImages.imagesAreEqual(file1, file2);
    }

}
