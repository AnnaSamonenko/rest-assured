package dto;

import java.util.List;

public class Rover {
    String id;
    String name;
    String landing_date;
    String launch_date;
    String status;
    Integer max_sol;
    String max_date;
    Integer total_photos;
    List<Cameras> cameras;

    @Override
    public String toString() {
        return "Rover{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", landing_date='" + landing_date + '\'' +
                ", launch_date='" + launch_date + '\'' +
                ", status='" + status + '\'' +
                ", max_sol=" + max_sol +
                ", max_date='" + max_date + '\'' +
                ", total_photos=" + total_photos +
                ", cameras=" + cameras +
                '}';
    }
}
