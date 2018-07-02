package utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.awt.image.*;
import javax.imageio.*;

/**
 * <h1>Comparator of images.</>
 * The ComparatorOfImages provide function for comparing images.
 */
public class ComparatorOfImages {

    private static final Logger logger = Logger.getLogger(ComparatorOfImages.class);


    private ComparatorOfImages() {
    }

    /**
     * This method is used to compare images placed in the directories.
     *
     * @param folder1 first directory with images
     * @param folder2 second directory with images
     * @return true in case images in first directory is relevant
     * to images from the second directory, otherwise return false
     */
    public static boolean imagesAreEqual(final File folder1, final File folder2) {
        if (!folder1.isDirectory() || !folder2.isDirectory()) {
            logger.error("There no such folder");
            System.exit(1);
        }
        if (folder1.listFiles() == null || folder2.listFiles() == null) {
            logger.error("One of folders is empty");
            System.exit(1);
        }
        boolean isEquals = true;
        File[] listOfImages1 = folder1.listFiles();
        File[] listOfImages2 = folder2.listFiles();

        if (listOfImages1.length != listOfImages2.length) {
            return false;
        }

        for (int i = 0; i < listOfImages1.length; i++) {
            if (!imagesAreEqualBySingleFile(listOfImages1[i].getAbsoluteFile(), listOfImages2[i].getAbsoluteFile())) {
                return false;
            }
        }
        return isEquals;
    }

    /**
     * This method is used to compare two files of images.
     *
     * @param file1 first file of images
     * @param file2 second file of images
     * @return true in case first image is relevant to image from the second directory, otherwise return false
     */
    private static boolean imagesAreEqualBySingleFile(final File file1, final File file2) {
        BufferedImage image1;
        BufferedImage image2;

        try {
            image1 = ImageIO.read(file1);
            image2 = ImageIO.read(file2);
        } catch (Exception e) {
            logger.error(e);
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