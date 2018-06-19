package utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * <h1>Downloader for download images.</>
 * The Downloader provide function for download images by URL.
 */
public class Downloader {

    private static final Logger logger = Logger.getLogger(PropertyReader.class);

    private Downloader() {
    }

    /**
     * This method is used for downloading images.
     *
     * @param folder destination folder for downloaded images
     * @param URLs   is a list of URLs to images
     */
    public static void downloadPictures(final String folder, final List<String> URLs) {
        if (createDir(folder)) {
            for (String s : URLs)
                downloadPicture(folder, s);
        } else {
            logger.error("Folder for images is not created");
            System.exit(1);
        }
    }

    /**
     * This method is used for downloading single image.
     *
     * @param folder   destination folder for downloaded image
     * @param imageUrl is a URL to a single image
     */
    private static void downloadPicture(final String folder, final String imageUrl) {
        try (InputStream is = new URL(imageUrl).openStream();
             OutputStream os = new FileOutputStream(folder + "/" + getImageTitle(imageUrl))) {

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    /**
     * This method is used for parsing URL for getting image title.
     *
     * @param imageUrl is a URL to a single image
     * @return the list of image titles
     */
    private static String getImageTitle(final String imageUrl) throws Exception {
        URL url = new URL(imageUrl);
        String urlFile = url.getFile();
        String[] urlFileList = urlFile.split("/");
        return urlFileList[urlFileList.length - 1];
    }

    /**
     * This method is used for creating directory.
     *
     * @param dirName is the name of the directory
     * @return true in case folder is created, otherwise - false
     */
    private static boolean createDir(final String dirName) {
        return new File(dirName).mkdir();
    }

}
