package com.revature.project_0.screens;

import com.revature.project_0.models.User;
import com.revature.project_0.services.UserService;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

public class UserMenu extends Menu {
    private UserService userService;

    public UserMenu(BufferedReader consoleReader, Navigator navigator, UserService userService) {
        super("User Menu", "/user_menu", consoleReader, navigator);
        this.userService = userService;

    }

    @Override
    public void render() throws Exception {

        User user = userService.getCurrentUser();

        System.out.printf("Welcome %s %s! \nHow can we help you today?", user.getFirstName(), user.getLastName());

        while (userService.getCurrentUser() != null) {
            System.out.println("/----------------------------------\\");
            System.out.println("           User Actions");
            System.out.println("1) View Accounts");
            System.out.println("2) Create New Account");
            System.out.println("3) Add User to existing account");
            System.out.println("4) Exit to Main Menu");
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
                    //TODO: Implement me!
                    break;
                case "4":
                    userService.endSession();
                    navigator.navigateTo("/end_session");
                    break;
                default:
                    //TODO: Implement Me

            }
        }
    }
}
