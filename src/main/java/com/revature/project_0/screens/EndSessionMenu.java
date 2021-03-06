package com.revature.project_0.screens;

import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

/**
 *  Very simple message informing user that they have been logged out of the application
 */

public class EndSessionMenu extends Menu {

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public EndSessionMenu(BufferedReader consoleReader, Navigator navigator) {
        super("End Session", "/end_session", consoleReader, navigator);
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    @Override
    public void render() throws Exception {
        System.out.println("/-----------------------------\\");
        System.out.println("   Thank you for using the");
        System.out.println("     AA Banking system");
        System.out.println("   Press enter to return to ");
        System.out.println("     the welcome screen:");
        System.out.println("\\-----------------------------/");
        consoleReader.readLine();

    }

    //-------------------------------------------------

}
