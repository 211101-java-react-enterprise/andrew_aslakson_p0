package com.revature.project_0.screens;

import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

public class UserMenu extends Menu {
    public UserMenu(BufferedReader consoleReader, Navigator navigator) {
        super("User Menu", "/user_menu", consoleReader, navigator);
    }

    @Override
    public void render() throws Exception {
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
                //TODO: Implement me!
                break;
            case "2":
                //TODO: Implement me!
                break;
            case "3":
                //TODO: Implement me!
                break;
            case "4":
                navigator.navigateTo("/end_session");
                break;
            default:
                //TODO: Implement Me
        }
    }
}
