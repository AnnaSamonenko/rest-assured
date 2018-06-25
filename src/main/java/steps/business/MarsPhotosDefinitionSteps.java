package steps.business;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.PhotoDTO;
import net.thucydides.core.annotations.Steps;
import steps.serenity.MarsPhotosSteps;
import utils.ComparatorOfImages;
import utils.Converter;
import utils.Downloader;
import utils.PropertyReader;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MarsPhotosDefinitionSteps {
    @Steps
    private MarsPhotosSteps marsPhotosSteps;
    private List<PhotoDTO> expectedPhotosWithEarthDate;
    private List<PhotoDTO> expectedPhotosWithSolDate;

    @When("^call Mars photos service with (\\d+) sol for (\\w+) rover for sol date for (\\d+) photos$")
    public void callMarsPhotosForEarthDateService(final int sol, final String roverName, final int amount)
            throws ParseException {
        Downloader.downloadPictures(PropertyReader.getProperty("photos.earth.date.dir"),
                marsPhotosSteps.getListOfUrls(roverName, Converter.countEarthDate(sol, roverName), amount));
        expectedPhotosWithSolDate = marsPhotosSteps.getPhotos(roverName, sol, amount);
    }

    @When("^call Mars photos service with (\\d+) sol for (\\w+) rover for earth date for (\\d+) photos$")
    public void callMarsPhotosForSolDateService(final int sol, final String roverName, final int amount)
            throws ParseException {
        Downloader.downloadPictures(PropertyReader.getProperty("photos.sol.dir"),
                marsPhotosSteps.getListOfUrls(roverName, sol, amount));
        expectedPhotosWithEarthDate = marsPhotosSteps.getPhotos(roverName, Converter.countEarthDate(sol, roverName), amount);
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
