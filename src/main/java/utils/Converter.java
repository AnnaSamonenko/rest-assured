package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Converter {

    public static String getEarthDate(final int sol) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar earthDate = Calendar.getInstance();
        earthDate.set(2012, Calendar.AUGUST, 6);
        earthDate.add(Calendar.DATE, (int) convertSolToEarthDay(sol));
        return sdf.format(earthDate.getTime());
    }

    private static long convertSolToEarthDay(final int sol) {
        return (Math.round(sol * 1027.49 / 1000));
    }

}
