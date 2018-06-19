package steps;

import dto.CameraDTO;
import dto.PhotoDTO;
import endpoint.MarsPhotosEndpoint;

import java.util.*;
import java.util.stream.Collectors;

public class MarsPhotosSteps {

    private MarsPhotosEndpoint endpoint = new MarsPhotosEndpoint();

    private List<PhotoDTO> getPhoto(final int sol) {
        return endpoint.marsPhotosEndpointWithSol(sol).stream().collect(Collectors.toList());
    }

    public List<PhotoDTO> getPhoto(final String date, final int amount) {
        return endpoint.marsPhotosEndpointWithEarthDate(date).stream().limit(amount).collect(Collectors.toList());
    }

    public List<PhotoDTO> getPhoto(final int sol, final int amount) {
        return endpoint.marsPhotosEndpointWithSol(sol).stream().limit(amount).collect(Collectors.toList());
    }

    public List<String> getListOfUrls(final int sol, final int amount) {
        return getPhoto(sol, amount).stream().limit(amount).map(PhotoDTO::getImg_src).collect(Collectors.toList());
    }

    public List<String> getListOfUrls(final String date, final int amount) {
        return getPhoto(date, amount).stream().limit(amount).map(PhotoDTO::getImg_src).collect(Collectors.toList());
    }

    public Map<String, Integer> getAmountOfPhotosInOrder(final int sol) {
        Map<String, Integer> result = getAmountOfPhotosByCameras(sol).entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return result;
    }

    private Map<String, Integer> getAmountOfPhotosByCameras(final int sol) {
        List<CameraDTO> cameraDTOS = getPhoto(sol)
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

}
