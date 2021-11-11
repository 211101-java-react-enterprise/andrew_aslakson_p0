package com.revature.project_0.screens;

import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

public class LoginMenu extends Menu {
    public LoginMenu(BufferedReader consoleReader, Navigator navigator) {
        super("Login Menu", "/login", consoleReader, navigator);

    }

    @Override
    public void render() throws Exception {
        System.out.println("/----------------------------------\\");
        System.out.println("              Login");

        System.out.print("Username: ");
        String username = consoleReader.readLine();

        System.out.print("Password: ");
        String password = consoleReader.readLine();

        System.out.println("\\----------------------------------/");
        System.out.println("Provided user credentials:");
        System.out.printf("Username: %s\n" +
                          "Password: %s\n",
                          username, password);

        //TODO: Use user service to validate credentials

        //change to access different outputs
        boolean isValid = true;

        if (isValid) {
            navigator.navigateTo("/user_menu");
        } else {
            navigator.navigateTo("/main_menu");
        }
    }
}
