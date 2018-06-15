package tests;

import org.junit.Test;
import steps.MarsPhotosSteps;
import utils.PropertyReader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CameraTest {

    private MarsPhotosSteps marsPhotosSteps = new MarsPhotosSteps();

    //TODO: add before method for removing useless folders

    @Test
    public void testAmountOfPhotos() {
        int sol = Integer.valueOf(PropertyReader.getProperty("sol"));
        List<Integer> amount = new ArrayList<>(marsPhotosSteps.getAmountOfPhotosInOrder(sol).values());
        assertTrue("One of camera do in 10 more photos than one of other", 10 * amount.get(0) > amount.get(amount.size() - 1));
    }

}
