package steps;

import dto.Camera;
import dto.Photo;
import endpoint.MarsPhotosEndpoint;

import java.util.*;
import java.util.stream.Collectors;

public class MarsPhotosSteps {

    private MarsPhotosEndpoint endpoint = new MarsPhotosEndpoint();

    private List<Photo> getPhoto(int sol) {
        return endpoint.marsPhotosEndpointWithSol(sol).stream().collect(Collectors.toList());
    }

    public List<Photo> getPhoto(String date, int amount) {
        return endpoint.marsPhotosEndpointWithEarthDate(date).stream().limit(amount).collect(Collectors.toList());
    }

    public List<Photo> getPhoto(int sol, int amount) {
        return endpoint.marsPhotosEndpointWithSol(sol).stream().limit(amount).collect(Collectors.toList());
    }

    public List<String> getListOfUrls(int sol, int amount){
        return getPhoto(sol, amount).stream().limit(amount).map(Photo::getImg_src).collect(Collectors.toList());
    }

    public List<String> getListOfUrls(String date, int amount){
        return getPhoto(date, amount).stream().limit(amount).map(Photo::getImg_src).collect(Collectors.toList());
    }

    public Map<String, Integer> getAmountOfPhotosInOrder(int sol){
        Map<String, Integer> result = amountOfPhotosByCameras(sol).entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return result;
    }

    private Map<String, Integer> amountOfPhotosByCameras(int sol) {
        List<Camera> cameras = getPhoto(sol)
                .stream()
                .map(Photo::getCamera)
                .collect(Collectors.toList());

        Set<String> nameOfCameras = cameras.stream().map(Camera::getName).collect(Collectors.toSet());

        Map<String, Integer> amountOfPhotos = new LinkedHashMap<>();

        for (String key : nameOfCameras) {
            amountOfPhotos.put(key, 0);
        }

        for (Camera camera : cameras) {
            for (String name : nameOfCameras)
                if (camera.getName().equals(name)) {
                    Integer amount = amountOfPhotos.get(name);
                    amount++;
                    amountOfPhotos.put(name, amount);
                }
        }

        return amountOfPhotos;
    }

}
