package tests;

import org.junit.Test;
import steps.MarsPhotosSteps;
import utils.Converter;
import utils.Helper;
import utils.PropertyReader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CuriosityTest {
    private MarsPhotosSteps marsPhotosSteps = new MarsPhotosSteps();

    @Test
    public void verifyImagesMadeByCuriosity() throws Exception {
        String dirForPhotosWithEarthDate = PropertyReader.getProperty("dirForPhotosWithEarthDate");
        int numberOfPhotos = 10;
        String dirForPhotosWithSol = PropertyReader.getProperty("photosSol");
        int sol = Integer.valueOf(PropertyReader.getProperty("sol"));

        Helper.downloadPictures(dirForPhotosWithEarthDate,
                marsPhotosSteps.getListOfUrls(Converter.getEarthDate(sol), numberOfPhotos));
        Helper.downloadPictures(dirForPhotosWithSol,
                marsPhotosSteps.getListOfUrls(sol, numberOfPhotos));

        assertEquals(marsPhotosSteps.getPhoto(Converter.getEarthDate(sol), numberOfPhotos).toString(),
                marsPhotosSteps.getPhoto(sol, numberOfPhotos).toString());
    }

    @Test
    public void testAmountOfPhotos() throws Exception {
        int sol = Integer.valueOf(PropertyReader.getProperty("sol"));

        List<Integer> amount = new ArrayList<>(marsPhotosSteps.getAmountOfPhotosInOrder(sol).values());
        assertTrue(10 * amount.get(0) > amount.get(amount.size() - 1));
    }

}
