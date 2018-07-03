package helpers;

import steps.MarsPhotosSteps;
import utils.ConvertDateUtil;
import utils.DownloadFileUtil;
import utils.FileUtil;
import utils.PropertyReaderUtil;

import java.io.File;

public class MarsPhotoHelper {

    private MarsPhotosSteps marsPhotosSteps = new MarsPhotosSteps();

    private String earthDateDirectoryName = PropertyReaderUtil.getProperty("photos.earth.date.dir");
    private String solDateDirectoryName = PropertyReaderUtil.getProperty("photos.sol.dir");
    private static final String PATH = "src\\test\\resources\\";

    public void downloadPhotos(String roverName, int sol, int amount) {
        DownloadFileUtil.downloadFile(earthDateDirectoryName,
                marsPhotosSteps.getListOfUrls(roverName, ConvertDateUtil.countEarthDate(sol, roverName), amount));
        DownloadFileUtil.downloadFile(solDateDirectoryName,
                marsPhotosSteps.getListOfUrls(roverName, sol, amount));
    }

    public File getDirectoryWithSolDatePhotos() {
        return new File(PATH + solDateDirectoryName);
    }

    public File getDirectoryWithEarthDatePhotos() {
        return new File(PATH + earthDateDirectoryName);
    }

    public void removeDirectoriesWithPhotos() {
        FileUtil.removeDirectory(PATH + earthDateDirectoryName);
        FileUtil.removeDirectory(PATH + solDateDirectoryName);
    }


}
