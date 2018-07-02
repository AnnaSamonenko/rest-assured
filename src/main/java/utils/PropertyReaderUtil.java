package utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <h1>PropertyReaderUtil is for reading properties file.</>
 * The PropertyReaderUtil provide function for getting value by specified key from the property file.
 */
public class PropertyReaderUtil {

    private static Properties properties = new Properties();
    private static final Logger logger = Logger.getLogger(PropertyReaderUtil.class);

    private PropertyReaderUtil() {
    }

    /**
     * This method is used for searching for the property with the specified key.
     *
     * @param key the property key
     * @return the value
     */
    public static String getProperty(final String key) {
        try (InputStream inputStream = new FileInputStream("src\\main\\resources\\static.properties")) {
            properties.load(inputStream);
            return properties.getProperty(key);
        } catch (IOException ex) {
            logger.error(ex);
            return ex.getMessage();
        }
    }
}