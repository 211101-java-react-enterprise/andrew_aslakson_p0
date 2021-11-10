package com.revature.project_0.screens;

import com.revature.project_0.models.User;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

public class UserRegisterMenu extends Menu {
    public UserRegisterMenu(BufferedReader consoleReader, Navigator navigator) {
        super("Registration Menu", "/register", consoleReader, navigator);
    }

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

        System.out.printf("Provided user credentials:\n" +
                          "First Name: %s\n" +
                          "Last Name: %s\n" +
                          "E-Mail: %s\n" +
                          "Username: %s\n" +
                          "password: %s\n",
                          firstName, lastName, email, username, password);

        User newUser = new User(firstName, lastName, email, username, password);

        //TODO: use services to register user
    }
}
