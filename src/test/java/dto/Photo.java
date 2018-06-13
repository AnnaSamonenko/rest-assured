package dto;


public class Photo {
    long id;
    int sol;
    String img_src;
    String earth_date;
    Camera camera;
    Rover rover;

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

    public String getImg_src() {
        return img_src;
    }

}
