package com.revature.project_0.screens;

import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

import static com.revature.project_0.util.AppCondition.shutdown;

/**
 *      This menu is the first menu a user sees after the welcome screen
 *
 *      it allows a user to select between creating a new user, logging
 *      in as a new user or exiting the application
 *
 */

public class MainMenu extends Menu {

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public MainMenu(BufferedReader consoleReader, Navigator navigator) {
        super("Main Menu", "/main_menu", consoleReader, navigator);
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    @Override
    public void render() throws Exception {
        String input;

        System.out.println("/-----------------------\\");
        System.out.println("        Main Menu");
        System.out.println("       -----------");
        System.out.println("1) Register User");
        System.out.println("2) Login");
        System.out.println("3) Exit");
        System.out.println("\\-----------------------/");
        System.out.println("Please select an option:");
        System.out.print(">> ");

        input = consoleReader.readLine();

        switch (input) {
            case "1":
                navigator.navigateTo("/register");
                break;
            case "2":
                navigator.navigateTo("/login");
                break;
            case "3":
                shutdown();
                break;

        }
    }

    //-------------------------------------------------

}
