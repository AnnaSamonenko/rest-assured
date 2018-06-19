package tests;

import org.junit.Test;
import steps.MarsPhotosSteps;
import utils.PropertyReader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CameraTest {

    private MarsPhotosSteps marsPhotosSteps = new MarsPhotosSteps();
    private int sol = Integer.valueOf(PropertyReader.getProperty("sol"));
    private static String roverName = PropertyReader.getProperty("rover.name");

    @Test
    public void testQuantityOfPhotos() {
        List<Integer> quantity =
                new ArrayList<>(marsPhotosSteps.getQuantityOfPhotosMadeByCameraInOrder(roverName, sol).values());
        assertTrue("One of camera do in 10 more photos than one of other",
                10 * quantity.get(0) > quantity.get(quantity.size() - 1));
    }

}
