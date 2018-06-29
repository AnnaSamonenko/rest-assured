package model.dto;

import lombok.Getter;

class CameraDTO extends BaseDTO {
    private int id;
    @Getter
    private String name;
    private String rover_id;
    private String full_name;
}
