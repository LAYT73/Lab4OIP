package utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Utility class for logging.
 */
public class MyLogger {
    // Logger instance
    private static final Logger logger = Logger.getLogger(MyLogger.class.getName());

    // Static initializer block to configure logger
    static {
        try {
            // Create file handler for logging to a file
            FileHandler fh = new FileHandler("./logs.log");

            // Add file handler to logger
            logger.addHandler(fh);

            // Set simple formatter for file handler
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            // Print stack trace if configuration fails
            e.printStackTrace();
        }
    }

    /**
     * Returns the logger instance.
     *
     * @return The logger instance
     */
    public static Logger getLogger() {
        return logger;
    }
}
