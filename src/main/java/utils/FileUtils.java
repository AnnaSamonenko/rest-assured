package utils;

import java.io.File;


/**
 * <h1>FileUtils provide functions for work with files.</>
 * For removing directory.
 */
public class FileUtils {

    private FileUtils() {
    }

    /**
     * This method is used for removing directory
     *
     * @param dirName name of directory
     */
    public static void removeDirectory(String dirName) {
        File directory = new File(dirName);
        String[] entries = directory.list();
        for (String s : entries) {
            File currentFile = new File(directory.getPath(), s);
            currentFile.delete();
        }
        directory.delete();
    }

}