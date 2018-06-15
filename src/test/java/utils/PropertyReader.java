package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static Properties properties = new Properties();
    private static InputStream inputStream;

    public static String getProperty(final String property) throws IOException {
        try {
            inputStream = new FileInputStream("src\\test\\resources\\config.properties");
            properties.load(inputStream);
            return properties.getProperty(property);
        } catch (Exception ex) {
            return "Exception happened";
        }
    }

}
