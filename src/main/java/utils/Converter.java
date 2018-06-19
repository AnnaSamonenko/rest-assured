package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <h1>Converter of sol to earth date</>
 * The Converter provide function for converting sol to earth date.
 */
public class Converter {

    private Converter() {
    }

    /**
     * This method is used for counting earth date.
     * Earth date is counted by adding landing date of rover and time of staying of rover on Mars.
     * Note: Time of staying of rover needed to be counted in the earth date.
     *
     * @param sol is duration of a sonar day on Mars
     * @return String with formatted earth date: yyyy-MM-dd
     */
    public static String countEarthDate(final int sol) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar earthDate = Calendar.getInstance();
        earthDate.set(2012, Calendar.AUGUST, 6);
        earthDate.add(Calendar.DATE, convertSolToEarthDay(sol));
        return sdf.format(earthDate.getTime());
    }

    /**
     * This method is used for converting sol to earth date.
     *
     * @param sol is duration of a sonar day on Mars
     * @return int of the amount of earth date
     */
    private static int convertSolToEarthDay(final int sol) {
        return (int) (Math.round(sol * 1027.49 / 1000));
    }

}
