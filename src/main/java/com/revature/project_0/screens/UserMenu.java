package com.revature.project_0.screens;

import com.revature.project_0.models.User;
import com.revature.project_0.services.UserService;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

/**
 *      This menu is the main menu for once a user is logged in
 *
 *      it implements a looping structure based on whether or not
 *      userService.currentUser is occupied by a valid User instance
 */

public class UserMenu extends Menu {

    //0000000000000000000000000000000000000000000000000

    private UserService userService;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public UserMenu(BufferedReader consoleReader, Navigator navigator, UserService userService) {
        super("User Menu", "/user_menu", consoleReader, navigator);
        this.userService = userService;

    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    @Override
    public void render() throws Exception {

        System.out.printf("\nWelcome %s %s! \nHow can we help you today?\n",
                userService.getCurrentUser().getFirstName(),
                userService.getCurrentUser().getLastName());

        while (userService.getCurrentUser() != null) {
            System.out.println("/----------------------------------\\");
            System.out.println("           User Actions");
            System.out.println("1) View Accounts");
            System.out.println("2) Create New Account");
            System.out.println("3) Exit to Main Menu");
            System.out.println("\\----------------------------------/");
            System.out.println("Please select an option:");
            System.out.print(">> ");

            String input = consoleReader.readLine();

            switch (input) {
                case "1":
                    navigator.navigateTo("/account_viewer");
                    break;
                case "2":
                    navigator.navigateTo("/account_creator");
                    break;
                case "3":
                    userService.endSession();
                    navigator.navigateTo("/end_session");
                    break;
                default:
                    System.out.println("Invalid input, please try again");

            }
        }
    }

    //-------------------------------------------------

}
