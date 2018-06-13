package dto;

public class Camera {
    String id;
    String name;
    String rover_id;
    String full_name;

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
