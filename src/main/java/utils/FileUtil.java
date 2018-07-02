package utils;

import org.apache.log4j.Logger;

import java.io.File;

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
        File directory = new File(dirName);
        String[] entries = directory.list();
        if (entries.length != 0) {
            for (String s : entries) {
                File currentFile = new File(directory.getPath(), s);
                if (!currentFile.delete()) {
                    LOGGER.error("Can't remove file in the directory");
                }
            }
        }
        if (!directory.delete()) {
            LOGGER.error("Can't remove directories");
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