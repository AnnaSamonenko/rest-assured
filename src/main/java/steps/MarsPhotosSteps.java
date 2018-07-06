package steps;

import models.dto.PhotoDTO;
import net.thucydides.core.annotations.Step;
import resources.MarsPhotosEndpoint;
import utils.JsonParseUtil;

import java.util.*;
import java.util.stream.Collectors;

public class MarsPhotosSteps {

    private MarsPhotosEndpoint endpoint = new MarsPhotosEndpoint();

    @Step
    public List<PhotoDTO> getMetadataByEarthDate(final String roverName, final String earthDate, final int quantity) {
        return JsonParseUtil.parseJson(endpoint.get(roverName, earthDate), quantity);
    }

    @Step
    public List<PhotoDTO> getMetadataBySolDate(final String roverName, final int sol, final int quantity) {
        return JsonParseUtil.parseJson(endpoint.get(roverName, sol), quantity);
    }

    @Step
    public List<String> getListOfUrls(final String roverName, final int sol, final int quantity) {
        return getMetadataBySolDate(roverName, sol, quantity)
                .stream()
                .map(PhotoDTO::getImg_src)
                .collect(Collectors.toList());
    }

    @Step
    public List<String> getListOfUrls(final String roverName, final String date, final int quantity) {
        return getMetadataByEarthDate(roverName, date, quantity)
                .stream()
                .map(PhotoDTO::getImg_src)
                .collect(Collectors.toList());
    }

}