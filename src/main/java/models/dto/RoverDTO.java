package models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoverDTO extends BaseDTO {
    private int id;
    private String name;
    private String landing_date;
    private String launch_date;
    private String status;
    private Integer max_sol;
    private String max_date;
    private Integer total_photos;
    private CamerasDTO[] cameras;
}
