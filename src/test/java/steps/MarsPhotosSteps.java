package steps;

import dto.Photo;
import endpoint.MarsPhotosEndpoint;

import java.util.List;
import java.util.stream.Collectors;

public class MarsPhotosSteps {

    private MarsPhotosEndpoint endpoint = new MarsPhotosEndpoint();

    public List<Photo> getPhoto(String date, int amount) {
        return endpoint.MarsPhotosEndpointWithEarthDate(date).stream().limit(amount).collect(Collectors.toList());
    }

    public List<Photo> getPhoto(int sol, int amount) {
        return endpoint.MarsPhotosEndpointWithSol(sol).stream().limit(amount).collect(Collectors.toList());
    }

    public List<String> getListOfUrls(int sol, int amount){
        return getPhoto(sol, amount).stream().map(Photo::getImg_src).collect(Collectors.toList());
    }

    public List<String> getListOfUrls(String date, int amount){
        return getPhoto(date, amount).stream().map(Photo::getImg_src).collect(Collectors.toList());
    }

}
