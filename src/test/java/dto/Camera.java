package dto;

import lombok.Getter;

public class Camera {
    private String id;
    @Getter
    private String name;
    private String rover_id;
    private String full_name;

    @Override
    public String toString() {
        return "Camera{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", rover_id='" + rover_id + '\'' +
                ", full_name='" + full_name + '\'' +
                '}';
    }
}
