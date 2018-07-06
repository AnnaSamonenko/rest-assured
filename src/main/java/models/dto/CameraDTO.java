package models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CameraDTO extends BaseDTO {
    private int id;
    private String name;
    private String rover_id;
    private String full_name;
}
