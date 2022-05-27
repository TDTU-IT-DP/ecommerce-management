package com.tdtu.ecommerce.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Logger {
    private static Logger logger = null;
    private final String logFile = "log/log.txt";

    private PrintWriter writer;

    private Logger() {
        try {
            FileWriter fw = new FileWriter(logFile);
            writer = new PrintWriter(fw, true);
        } catch (IOException e) {}
    }

    public static synchronized Logger getInstance(){
        if(logger == null)
            logger = new Logger();
        return logger;
    }

    public void info (String clazz, String message) {
        writer.println(String.format("%s INFO: %s --- %s", new Date(), clazz, message));
    }

    public void error (String error, String message) {
        final String format = String.format("%s ERROR: %s --- %s", new Date(), error, message);
        writer.println(format);
    }

}
