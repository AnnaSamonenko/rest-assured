package dto;

import lombok.Getter;

public class Photo {
    private long id;
    private int sol;
    @Getter
    private String img_src;
    private String earth_date;
    @Getter
    private Camera camera;
    private Rover rover;

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", sol=" + sol +
                ", img_src='" + img_src + '\'' +
                ", earth_date='" + earth_date + '\'' +
                ", camera=" + camera +
                ", rover=" + rover +
                '}';
    }
}
