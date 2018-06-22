package hooks;

import cucumber.api.java.After;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import utils.PropertyReader;

import java.io.File;
import java.io.IOException;

public class Hooks {

    private Logger logger = Logger.getLogger(Hooks.class);

    @After("@clean")
    public void deleteDirectories() throws IOException {
        FileUtils.deleteDirectory(new File(PropertyReader.getProperty("photos.earth.date.dir")));
        FileUtils.deleteDirectory(new File(PropertyReader.getProperty("photos.sol.dir")));
        logger.info("Directories deleted");
    }
}
