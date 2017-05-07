package com.aksesi.infrastructure.logger;


import org.apache.log4j.Logger;
/**
 * Created by Mateusz Brycki on 29/04/2017.
 */
public class AksesiLogger {

    public static AksesiLogger getLogger(String name) {
        Logger logger = Logger.getLogger(name);

        return new AksesiLogger(logger);
    }

    private static final String AKSESI_PREFIX = "[AKSESI] ";

    private Logger logger;
    private StringBuilder builder = new StringBuilder();

    private AksesiLogger(Logger logger) {
        this.logger = logger;
    }

    public void info(String message) {
        logger.info(prepareMessage(message));
    }

    public void debug(String message) {
        logger.debug(prepareMessage(message));
    }

    public void warn(String message) {
        logger.warn(prepareMessage(message));
    }

    public void error(String message) {
        logger.error(prepareMessage(message));
    }

    public void fatal(String message) {
        logger.fatal(prepareMessage(message));
    }

    private String prepareMessage(String message) {

        builder.append(AKSESI_PREFIX);
        builder.append(message);

        message = builder.toString();

        builder.setLength(0);

        return message;

    }
}
