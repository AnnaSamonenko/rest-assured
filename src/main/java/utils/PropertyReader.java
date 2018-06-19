package utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static Properties properties = new Properties();
    final static Logger logger = Logger.getLogger(PropertyReader.class);

    private PropertyReader() {
    }

    public static String getProperty(final String property) {
        try (InputStream inputStream = new FileInputStream("src\\test\\resources\\static.properties")) {
            properties.load(inputStream);
            return properties.getProperty(property);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            return ex.getMessage();
        }
    }
}
