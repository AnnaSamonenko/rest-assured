package tests;

import org.junit.Test;
import steps.MarsPhotosSteps;
import utils.Converter;
import utils.Helper;
import utils.PropertyReader;

import static org.junit.Assert.assertEquals;

public class CuriosityTest {
    private MarsPhotosSteps marsPhotosSteps = new MarsPhotosSteps();

    // TODO: add fail message

    @Test
    public void verifyImagesMadeByCuriosity() {
        String dirForPhotosWithEarthDate = PropertyReader.getProperty("dirForPhotosWithEarthDate");
        int numberOfPhotos = Integer.valueOf(PropertyReader.getProperty("amount"));
        String dirForPhotosWithSol = PropertyReader.getProperty("photosSol");
        int sol = Integer.valueOf(PropertyReader.getProperty("sol"));

        Helper.downloadPictures(dirForPhotosWithEarthDate,
                marsPhotosSteps.getListOfUrls(Converter.getEarthDate(sol), numberOfPhotos));
        Helper.downloadPictures(dirForPhotosWithSol,
                marsPhotosSteps.getListOfUrls(sol, numberOfPhotos));

        assertEquals(marsPhotosSteps.getPhoto(Converter.getEarthDate(sol), numberOfPhotos).toString(),
                marsPhotosSteps.getPhoto(sol, numberOfPhotos).toString());
    }

}
