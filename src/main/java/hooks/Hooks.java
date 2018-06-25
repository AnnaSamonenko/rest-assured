package hooks;

import cucumber.api.java.After;
import org.apache.log4j.Logger;
import utils.FileUtils;
import utils.PropertyReader;

public class Hooks {

    private Logger logger = Logger.getLogger(Hooks.class);

    @After("@clean")
    public void deleteDirectories() {
        FileUtils.removeDirectory(PropertyReader.getProperty("photos.earth.date.dir"));
        FileUtils.removeDirectory(PropertyReader.getProperty("photos.sol.dir"));
        logger.info("Directories deleted");
    }
}
