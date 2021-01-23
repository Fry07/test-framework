package common;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    private static Logger logger = LoggerFactory.getLogger(Log.class);
    private static String log4jConfPath = "src/main/resources/properties/log4j.properties";

    /**
     * Log info message
     *
     * @param logMessage
     */
    public static void log(String logMessage) {
        PropertyConfigurator.configure(log4jConfPath);
        logger.info(logMessage);
    }
}
