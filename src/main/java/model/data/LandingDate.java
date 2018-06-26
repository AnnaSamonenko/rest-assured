package model.data;

public enum LandingDate {

    CURIOSITY("2012-08-6");

    private String date;

    LandingDate(String date) {
        this.date = date;
    }

    public String date() {
        return date;
    }

}