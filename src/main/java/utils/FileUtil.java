package utils;

import org.apache.log4j.Logger;

import java.io.File;


/**
 * <h1>FileUtil provide functions for work with files.</>
 * For removing directory.
 */
public class FileUtil {

    private static final Logger logger = Logger.getLogger(ComparePhotoUtil.class);

    private FileUtil() {
    }

    /**
     * This method is used for removing directory
     *
     * @param dirName name of directory
     */
    public static void removeDirectory(String dirName) {
        File directory = new File(dirName);
        String[] entries = directory.list();
        if (entries.length != 0) {
            for (String s : entries) {
                File currentFile = new File(directory.getPath(), s);
                if (!currentFile.delete()) {
                    logger.error("Can't remove file in the directory");
                }
            }
        }
        if (!directory.delete()) {
            logger.error("Can't remove directories");
        }
    }

}