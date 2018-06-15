package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    // TODO: use singleton pattern
    // TODO: IO commons

    private static Properties properties = new Properties();

    public static String getProperty(final String property) {
        try (InputStream inputStream = new FileInputStream("src\\test\\resources\\config.properties")) {
            properties.load(inputStream);
            return properties.getProperty(property);
        }
        catch (IOException ex) {
            return ex.getMessage();
        }
    }
}
