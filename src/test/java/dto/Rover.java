package dto;

import java.util.List;

public class Rover {
    private String id;
    private String name;
    private String landing_date;
    private String launch_date;
    private String status;
    private Integer max_sol;
    private String max_date;
    private Integer total_photos;
    private List<Cameras> cameras;

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
