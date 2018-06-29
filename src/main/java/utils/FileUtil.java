package utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * <h1>FileUtil provide functions for work with files.</>
 * For removing directory.
 */
public class FileUtil {

    private static final Logger LOGGER = Logger.getLogger(CompareImageUtil.class);

    private FileUtil() {
    }

    /**
     * This method is used for removing directory
     *
     * @param dirName name of directory
     */
    public static void removeDirectory(String dirName) {
        Path filePath = Paths.get(dirName);
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            LOGGER.error("Can't find directory by with name" + dirName);
        }
    }

    /**
     * This method is used for creating directory.
     *
     * @param file is the directory
     * @return true in case folder is created, otherwise - false
     */
    static boolean createDir(final File file) {
        return file.mkdir();
    }

}