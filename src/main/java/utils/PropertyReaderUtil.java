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

    private static Properties properties;
    private static final Logger LOGGER = Logger.getLogger(PropertyReaderUtil.class);

    private PropertyReaderUtil() {
    }

    /**
     * This method is used for searching for the property with the specified key.
     *
     * @param key the property key
     * @return the value
     */
    public static String getProperty(final String key) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(key);
    }


    private static void loadProperties() {
        String path = "src\\test\\resources\\static.properties";
        properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(path);
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new IllegalStateException("Failed to read the properties from path" + path);
        }
    }

}

