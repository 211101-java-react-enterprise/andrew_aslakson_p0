package com.revature.project_0.screens;

import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

public class WelcomeMenu extends Menu {

    public WelcomeMenu(BufferedReader consoleReader, Navigator navigator) {
        super("Welcome Menu", "/welcome", consoleReader, navigator);
    }

    @Override
    public void render() throws Exception {
        String temp;

        System.out.println("/-----------------------------\\");
        System.out.println("        Welcome to the");
        System.out.println("           AA Bank");
        System.out.println("    Press enter to continue:");
        System.out.println("\\-----------------------------/");

        consoleReader.readLine();

        navigator.navigateTo("/main_menu");

    }
}
