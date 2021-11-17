package com.revature.project_0.util.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Follows Singleton Design pattern so we can only have a single
 * instance of this class
 *
 * Basic logger, writes to resource/app.log
 *
 * takes in boolean parameter that is used to determine whether logs are printed to console or not
 * This boolean is hard-coded in the constructor and should be turned off for final builds
 */

public class Logger {

    //0000000000000000000000000000000000000000000000000

    private static final String ANSI_RESET = "\u001b[0m";
    private static final String ANSI_YELLOW = "\u001b[33m";

    //Hard-coded printToConsole variable
    private static final Logger logger = new Logger(false);
    private final boolean printToConsole;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    private Logger(boolean printToConsole) {
        this.printToConsole = printToConsole;
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

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

    public boolean isPrintToConsole() {
        return printToConsole;
    }

    //-------------------------------------------------

}
