package com.revature.project_0.screens;

import com.revature.project_0.services.UserService;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

public class LoginMenu extends Menu {
    private UserService userService;

    public LoginMenu(BufferedReader consoleReader, Navigator navigator, UserService userService) {
        super("Login Menu", "/login", consoleReader, navigator);
        this.userService = userService;

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

        //DEBUG OUTPUT
        /*
        System.out.println("Provided user credentials:");
        System.out.printf("Username: %s\n" +
                          "Password: %s\n",
                          username, password);
        */

        userService.authenticate(username, password);

        if (userService.getCurrentUser() != null) {
            navigator.navigateTo("/user_menu");
        } else {
            navigator.navigateTo("/main_menu");
        }
    }
}
