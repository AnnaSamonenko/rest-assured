package steps.business;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dto.Photo;
import steps.flow.MarsPhotosSteps;
import utils.Converter;
import utils.Helper;
import utils.PropertyReader;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ImageDefinitionSteps {
    private MarsPhotosSteps marsPhotosSteps = new MarsPhotosSteps();
    private List<Photo> expectedPhotosWithEarthDate;
    private List<Photo> expectedPhotosWithSolDate;

    @When("^call Mars photos service with (\\d+) sol for sol date for (\\d+) photos$")
    public void callMarsPhotosForEarthDateService(final int sol, final int amount) {
        Helper.downloadPictures(PropertyReader.getProperty("dirForPhotosWithEarthDate"),
                marsPhotosSteps.getListOfUrls(Converter.getEarthDate(sol), amount));
        expectedPhotosWithSolDate = marsPhotosSteps.getPhoto(sol, amount);
    }

    @When("^call Mars photos service with (\\d+) sol of earth date for (\\d+) photos$")
    public void callMarsPhotosForSolDateService(final int sol, final int amount) {
        Helper.downloadPictures(PropertyReader.getProperty("photosSol"),
                marsPhotosSteps.getListOfUrls(sol, amount));
        expectedPhotosWithEarthDate = marsPhotosSteps.getPhoto(Converter.getEarthDate(sol), amount);
    }

    @Then("metadata is the same")
    public void verifyMatadata() {
        assertEquals("Metadata from the endpoint is different but should be similar",
                expectedPhotosWithEarthDate.toString(),
                expectedPhotosWithSolDate.toString());
    }

    @Then("images are the same")
    public void verifyImages() {

    }

}
