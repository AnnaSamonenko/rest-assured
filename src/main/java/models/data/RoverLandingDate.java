package models.data;

public enum RoverLandingDate {

    CURIOSITY("2012-08-6");

    private String date;

    RoverLandingDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

}
