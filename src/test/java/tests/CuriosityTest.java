package tests;

import org.junit.Test;
import steps.MarsPhotosSteps;
import utils.Converter;
import utils.Helper;

import static org.junit.Assert.assertEquals;

public class CuriosityTest {

    private MarsPhotosSteps marsPhotosSteps = new MarsPhotosSteps();

    private final int numberOfPhotos = 10;
    private final static String dirForPhotosWithEarthDate = "earthDate";
    private final static String dirForPhotosWithSol = "solDate";
    private final int sol = 1000;

    @Test
    public void verifyImagesMadeByCuriosity() throws Exception {
        Helper.downloadPictures(dirForPhotosWithEarthDate,
                marsPhotosSteps.getListOfUrls(Converter.getEarthDate(sol), numberOfPhotos));
        Helper.downloadPictures(dirForPhotosWithSol,
                marsPhotosSteps.getListOfUrls(sol, numberOfPhotos));

        assertEquals(marsPhotosSteps.getPhoto(Converter.getEarthDate(sol), numberOfPhotos).toString(),
                marsPhotosSteps.getPhoto(sol, numberOfPhotos).toString());
    }

}
