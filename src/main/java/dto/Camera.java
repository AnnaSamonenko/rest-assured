package dto;

import lombok.Getter;

public class Camera extends BaseDTO {
    private int id;
    @Getter
    private String name;
    private String rover_id;
    private String full_name;

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == this) return true;
//        if (obj == null) return false;
//        if (getClass() != obj.getClass()) return false;
//        Camera camera = (Camera) obj;
//        return (id == camera.id && name.equals(camera.name) && rover_id.equals(camera.rover_id) &&
//                full_name.equals(camera.full_name));
//    }
}
