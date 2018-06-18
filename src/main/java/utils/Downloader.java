package utils;

import java.io.*;
import java.net.URL;
import java.util.List;

public class Downloader {

    public static void downloadPictures(final String folder, final List<String> URLs) {
        createDir(folder);
        for (String s : URLs)
            downloadPicture(folder, s);
    }

    private static void downloadPicture(final String folder, final String imageUrl) {
        try (InputStream is = new URL(imageUrl).openStream();
             OutputStream os = new FileOutputStream(folder + "/" + parseUrl(imageUrl))) {

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    private static String parseUrl(final String imageUrl) throws Exception {
        URL url = new URL(imageUrl);
        String urlFile = url.getFile();
        String[] urlFileList = urlFile.split("/");
        return urlFileList[urlFileList.length - 1];
    }

    private static void createDir(final String dirName) {
        new File(dirName).mkdir();
    }

}
