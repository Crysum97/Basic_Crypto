import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ShutdownHook;
import view.ApplicationFrame;

import javax.swing.*;

/**
 * This class is used as the starting point of the appliction
 */
public class Application {
    private static final Logger logger = LogManager.getLogger("Application");

    /**
     * Starts the application
     * @param args optional parameters
     */
    public static void main(String[] args) {
        logger.info("Starting...");
        // register shutdown hook
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
        logger.info("registered shutdown hook");
        // construct and show the frame
        JFrame frame = new ApplicationFrame();
    }
}
