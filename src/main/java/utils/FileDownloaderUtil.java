package utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * <h1>FileDownloaderUtil for download files.</>
 * The FileDownloaderUtil provide function for download files by URL.
 */
public class FileDownloaderUtil {

    private static final Logger logger = Logger.getLogger(PropertyReaderUtil.class);

    private FileDownloaderUtil() {
    }

    /**
     * This method is used for downloading images.
     *
     * @param folder destination folder for downloaded files
     * @param urls   is a list of URLs to files
     */
    public static void downloadFile(final String folder, final List<String> urls) {
        File dir = new File("src\\test\\resources\\" + folder);
        FileUtil.createDir(dir);
        if (dir.exists()) {
            for (String s : urls)
                downloadFile(dir.getPath(), s);
        } else {
            logger.error("Folder is not created");
        }
    }

    /**
     * This method is used for downloading single image.
     *
     * @param folder   destination folder for downloaded image
     * @param imageUrl is a URL to a single image
     */
    private static void downloadFile(final String folder, final String imageUrl) {
        try (InputStream is = new URL(imageUrl).openStream();
             OutputStream os = new FileOutputStream(folder + "/" + getFileTitle(imageUrl))) {

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
    private static String getFileTitle(final String imageUrl) throws MalformedURLException {
        URL url = new URL(imageUrl);
        String urlFile = url.getFile();
        String[] urlFileList = urlFile.split("/");
        return urlFileList[urlFileList.length - 1];
    }

}
