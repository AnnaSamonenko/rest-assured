package models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoDTO extends BaseDTO {
    private long id;
    private int sol;
    private String img_src;
    private String earth_date;
    private CameraDTO camera;
    private RoverDTO rover;
}
