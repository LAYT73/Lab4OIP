package utils;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
    private static final Logger logger = Logger.getLogger(MyLogger.class.getName());

    static {
        try {
            FileHandler fh = new FileHandler("./logs.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}

