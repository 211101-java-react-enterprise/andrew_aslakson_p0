package com.revature.project_0.util.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Logger {

    //0000000000000000000000000000000000000000000000000

    private static final String ANSI_RESET = "\u001b[0m";
    private static final String ANSI_YELLOW = "\u001b[33m";

    private static Logger logger;
    private final boolean printToConsole;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    private Logger(boolean printToConsole) {
        this.printToConsole = printToConsole;
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    // Hard coded printToConsole variable
    static {
        logger = new Logger(true);
    }

    //-------------------------------------------------

    public static Logger getLogger() {
        return logger;
    }

    //-------------------------------------------------

    public void info(String msg) {

    }

    //-------------------------------------------------

    public void warn(String msg) {

    }

    //-------------------------------------------------

    public void error(String msg) {

    }

    //-------------------------------------------------

    public void fatal(String msg) {

    }

    //-------------------------------------------------

    public void log(String msg, Object... args) {
        try (Writer logWriter = new FileWriter("src/main/resources/logs/app.log", true)) {

            String formattedMsg = String.format(msg, args);
            logWriter.write(formattedMsg + "\n");

            if (printToConsole) {
                System.out.println(ANSI_YELLOW + msg + ANSI_RESET);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //-------------------------------------------------

}
