package dto;

import lombok.Getter;

public class Photo extends BaseDTO {
    private long id;
    private int sol;
    @Getter
    private String img_src;
    private String earth_date;
    @Getter
    private Camera camera;
    private Rover rover;

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == this) return true;
//        if (obj == null) return false;
//        if (getClass() != obj.getClass()) return false;
//        Photo photo = (Photo) obj;
//        return (id == photo.id && sol == photo.sol && img_src.equals(photo.img_src) &&
//                earth_date.equals(photo.earth_date) && camera.equals(photo.camera) && rover.equals(photo.rover));
//    }
}
