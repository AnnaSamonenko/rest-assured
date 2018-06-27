package utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <h1>PropertyReaderUtil is for reading properties file.</>
 * The PropertyReaderUtil provide function for getting value by specified key from the property file.
 */
public class PropertyReaderUtil {

    private static Properties properties;
    private static String PATH = "src\\test\\resources\\static.properties";
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
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(key);
    }


    private static void loadProperties() {
        properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(PATH);
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}

