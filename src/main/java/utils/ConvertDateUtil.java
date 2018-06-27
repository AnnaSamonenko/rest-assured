package utils;

import model.data.LandingDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <h1>ConvertDateUtil of sol to earth getDate</>
 * The ConvertDateUtil provide function for converting sol to earth getDate.
 */
public class ConvertDateUtil {

    private ConvertDateUtil() {
    }

    /**
     * This method is used for counting earth getDate.
     * Earth getDate is counted by adding landing getDate of rover and time of staying of rover on Mars.
     * Note: Time of staying of rover needed to be counted in the earth getDate.
     *
     * @param sol       is duration of a sonar day on Mars
     * @param roverName is enum with landing getDate of rovers
     * @return String with formatted earth getDate: yyyy-MM-dd
     */
    public static String countEarthDate(final int sol, final String roverName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar earthDate = Calendar.getInstance();
        try {
            Date landingDate = sdf.parse(LandingDate.valueOf(roverName.toUpperCase()).getDate());
            earthDate.setTime(landingDate);
            earthDate.add(Calendar.DATE, convertSolToEarthDay(sol));
        } catch (ParseException ex) {
            //TODO: logger need to be here
        }
        return sdf.format(earthDate.getTime());
    }

    /**
     * This method is used for converting sol to earth getDate.
     *
     * @param sol is duration of a sonar day on Mars
     * @return int of the amount of earth getDate
     */
    private static int convertSolToEarthDay(final int sol) {
        return (int) (Math.round(sol * 1027.49 / 1000));
    }

}