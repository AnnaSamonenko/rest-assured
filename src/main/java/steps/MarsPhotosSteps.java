package steps;

import model.dto.PhotoDTO;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import resources.MarsPhotosEndpoint;

import java.util.*;
import java.util.stream.Collectors;

public class MarsPhotosSteps {

    private MarsPhotosEndpoint endpoint = new MarsPhotosEndpoint();

    @Step
    public List<PhotoDTO> getPhotos(final String roverName, final String earthDate, final int quantity) {
        return endpoint
                .get(roverName, earthDate)
                .stream()
                .limit(quantity)
                .collect(Collectors.toList());
    }

    @Step
    public List<PhotoDTO> getPhotos(final String roverName, final int sol, final int quantity) {
        return endpoint
                .get(roverName, sol)
                .stream()
                .limit(quantity)
                .collect(Collectors.toList());
    }

    @Step
    public List<String> getListOfUrls(final String roverName, final int sol, final int quantity) {
        return getPhotos(roverName, sol, quantity)
                .stream()
                .limit(quantity)
                .map(PhotoDTO::getImg_src)
                .collect(Collectors.toList());
    }

    @Step
    public List<String> getListOfUrls(final String roverName, final String date, final int quantity) {
        return getPhotos(roverName, date, quantity)
                .stream()
                .limit(quantity)
                .map(PhotoDTO::getImg_src)
                .collect(Collectors.toList());
    }

}