package com.revature.project_0.screens;

import com.revature.project_0.services.UserService;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

/**
 *      This menu is where a user logs into the application
 *
 *      An instance of userService is included to perform
 *      the logic of authenticating a user
 *
 *      userService.currentUser is also set when we call
 *      userService.authenticate()
 */

public class LoginMenu extends Menu {

    //0000000000000000000000000000000000000000000000000

    private UserService userService;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public LoginMenu(BufferedReader consoleReader, Navigator navigator, UserService userService) {
        super("Login Menu", "/login", consoleReader, navigator);
        this.userService = userService;

    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    @Override
    public void render() throws Exception {
        System.out.println("/----------------------------------\\");
        System.out.println("              Login");

        System.out.print("Username: ");
        String username = consoleReader.readLine();

        System.out.print("Password: ");
        String password = consoleReader.readLine();

        System.out.println("\\----------------------------------/");

        try {
            userService.authenticate(username, password);

            if (userService.getCurrentUser() != null) {
                navigator.navigateTo("/user_menu");
            } else {
                System.out.println("Could not verify user credentials!");
            }
        } catch (Exception e) {
            System.out.println("User verification failed!");
        }
    }

    //-------------------------------------------------

}
