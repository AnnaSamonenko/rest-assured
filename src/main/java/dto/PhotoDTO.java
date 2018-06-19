package dto;

import lombok.Getter;

public class PhotoDTO extends BaseDTO {
    private long id;
    private int sol;
    @Getter
    private String img_src;
    private String earth_date;
    @Getter
    private CameraDTO cameraDTO;
    private RoverDTO rover;
}
