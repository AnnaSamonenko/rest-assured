package hooks;

import cucumber.api.java.After;

import utils.FileUtils;
import utils.PropertyReader;

public class Hooks {

    @After("@clean")
    public void deleteDirectories() {
        FileUtils.removeDirectory(PropertyReader.getProperty("photos.earth.date.dir"));
        FileUtils.removeDirectory(PropertyReader.getProperty("photos.sol.dir"));
    }
}