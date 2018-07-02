package utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.awt.image.*;
import javax.imageio.*;

/**
 * <h1>Comparator of images.</>
 * The CompareImageUtil provide function for comparing images.
 */
public class ComparePhotoUtil {

    private static final Logger LOGGER = Logger.getLogger(ComparePhotoUtil.class);


    private ComparePhotoUtil() {
    }

    /**
     * This method is used to compare images placed in the directories.
     *
     * @param folder1 first directory with photos
     * @param folder2 second directory with photos
     * @return true in case images in first directory is relevant
     * to images from the second directory, otherwise return false
     */
    public static boolean areImagesInDirectoriesEqual(final File folder1, final File folder2) {
        if (verifyFolders(folder1, folder2)) {
            File[] listOfImages1 = folder1.listFiles();
            File[] listOfImages2 = folder2.listFiles();

            for (int i = 0; i < listOfImages1.length; i++) {
                if (!areImagesEqual(listOfImages1[i].getAbsoluteFile(), listOfImages2[i].getAbsoluteFile())) {
                    return false;
                }
            }
            return true;
        } else return false;
    }

    /**
     * Method which verify folders: that folders exist,
     * files are present and
     * amount of files are equals.
     *
     * @param folder1 first directory with photos
     * @param folder2 second directory with photos
     * @return true in case folders satisfied requirements, otherwise false.
     */

    private static boolean verifyFolders(final File folder1, final File folder2) {
        if (!folder1.isDirectory() || !folder2.isDirectory()) {
            LOGGER.error("There no such folder");
            throw new IllegalStateException();
        }

        if (folder1.listFiles() == null || folder2.listFiles() == null) {
            LOGGER.error("One of folders is empty");
            throw new IllegalStateException();
        } else {
            return folder1.listFiles().length == folder2.listFiles().length;
        }
    }

    /**
     * This method is used to compare two files of images.
     *
     * @param file1 first file of images
     * @param file2 second file of images
     * @return true in case first image is relevant to image from the second directory, otherwise return false
     */
    private static boolean areImagesEqual(final File file1, final File file2) {
        BufferedImage image1;
        BufferedImage image2;

        try {
            image1 = ImageIO.read(file1);
            image2 = ImageIO.read(file2);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }

        if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight())
            return false;

        for (int x = 0; x < image1.getWidth(); x++)
            for (int y = 0; y < image1.getHeight(); y++)
                if (image1.getRGB(x, y) != image2.getRGB(x, y))
                    return false;

        return true;
    }
}