package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShutdownHook extends Thread {
    /** reference to the log4j instance */
    private static final Logger logger = LogManager.getLogger("Application");

    /**
     * Overrides the {@link Thread#run()} method.
     * Signals that the application is closing
     */
    @Override
    public void run() {
        logger.info("Shutdown Hook called: Exiting...");
    }
}
