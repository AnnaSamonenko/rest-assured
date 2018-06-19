package steps;

import dto.CameraDTO;
import dto.PhotoDTO;
import resources.MarsPhotosEndpoint;

import java.util.*;
import java.util.stream.Collectors;

public class MarsPhotosSteps {

    private MarsPhotosEndpoint endpoint = new MarsPhotosEndpoint();

    public List<PhotoDTO> getPhotos(final String roverName, final String earthDate, final int quantity) {
        return endpoint
                .get(roverName, earthDate)
                .stream()
                .limit(quantity)
                .collect(Collectors.toList());
    }

    public List<PhotoDTO> getPhotos(final String roverName, final int sol, final int quantity) {
        return endpoint
                .get(roverName, sol)
                .stream()
                .limit(quantity)
                .collect(Collectors.toList());
    }

    public List<String> getListOfUrls(final String roverName, final int sol, final int quantity) {
        return getPhotos(roverName, sol, quantity)
                .stream()
                .limit(quantity)
                .map(PhotoDTO::getImg_src)
                .collect(Collectors.toList());
    }

    public List<String> getListOfUrls(final String roverName, final String date, final int quantity) {
        return getPhotos(roverName, date, quantity)
                .stream()
                .limit(quantity)
                .map(PhotoDTO::getImg_src)
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getQuantityOfPhotosMadeByCameraInOrder(final String roverName, final int sol) {
        Map<String, Integer> result =
                getQuantityOfPhotosByCamera(roverName, sol)
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return result;
    }

    private Map<String, Integer> getQuantityOfPhotosByCamera(final String roverName, final int sol) {
        List<CameraDTO> cameraDTOS = getAllPhotos(roverName, sol)
                .stream()
                .map(PhotoDTO::getCamera)
                .collect(Collectors.toList());

        Set<String> nameOfCameras = cameraDTOS.stream().map(CameraDTO::getName).collect(Collectors.toSet());

        Map<String, Integer> amountOfPhotos = new LinkedHashMap<>();

        for (String key : nameOfCameras) {
            amountOfPhotos.put(key, 0);
        }

        for (CameraDTO cameraDTO : cameraDTOS) {
            for (String name : nameOfCameras)
                if (cameraDTO.getName().equals(name)) {
                    Integer amount = amountOfPhotos.get(name);
                    amount++;
                    amountOfPhotos.put(name, amount);
                }
        }

        return amountOfPhotos;
    }

    private List<PhotoDTO> getAllPhotos(final String roverName, final int sol) {
        return endpoint
                .get(roverName, sol)
                .stream()
                .collect(Collectors.toList());
    }

}
