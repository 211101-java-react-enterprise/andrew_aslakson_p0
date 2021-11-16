package com.revature.project_0.screens;

import com.revature.project_0.models.User;
import com.revature.project_0.services.UserService;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

/**
 *      This menu is accessed when registering a new user
 *
 *      holds UserService instance to do this
 */

public class UserRegisterMenu extends Menu {

    //0000000000000000000000000000000000000000000000000

    private UserService userService;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public UserRegisterMenu(BufferedReader consoleReader, Navigator navigator, UserService userService) {
        super("Registration Menu", "/register", consoleReader, navigator);
        this.userService = userService;
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    @Override
    public void render() throws Exception {
        System.out.println("/--------------------------------------------\\");
        System.out.println("Please provide us with some basic information.");

        System.out.print("First Name: ");
        String firstName = consoleReader.readLine();

        System.out.print("Last Name: ");
        String lastName = consoleReader.readLine();

        System.out.print("E-Mail: ");
        String email = consoleReader.readLine();

        System.out.print("Username: ");
        String username = consoleReader.readLine();

        System.out.print("Password: ");
        String password = consoleReader.readLine();
        System.out.println("\\--------------------------------------------/");

        User newUser = new User(firstName, lastName, email, username, password);

        try {
            userService.register(newUser);
            System.out.println("New User registered successfully!");
        }
        catch(Exception e){
            System.out.println("Failed to create new User account: ");
            System.out.println("   All user data must not be empty and");
            System.out.println("   username and email must be unique.");
        }

    }

    //-------------------------------------------------

}
