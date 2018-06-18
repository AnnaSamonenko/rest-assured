package dto;

import java.util.List;

public class Rover extends BaseDTO {
    private int id;
    private String name;
    private String landing_date;
    private String launch_date;
    private String status;
    private Integer max_sol;
    private String max_date;
    private Integer total_photos;
    private List<Cameras> cameras;

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == this) return true;
//        if (obj == null) return false;
//        if (getClass() != obj.getClass()) return false;
//        Rover rover = (Rover) obj;
//        return (id == rover.id && name.equals(rover.name) && landing_date.equals(rover.landing_date) &&
//                launch_date.equals(rover.launch_date) && status.equals(rover.status) && max_sol.equals(rover.max_sol) &&
//                max_date.equals(rover.max_date) && total_photos.equals(rover.total_photos) &&
//                cameras.equals(rover.cameras));
//    }
}
