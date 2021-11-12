package com.revature.project_0.screens;

import com.revature.project_0.services.AccountService;
import com.revature.project_0.services.UserService;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

public class AccountCreationMenu extends Menu{
    private UserService userService;
    private AccountService accountService;

    public AccountCreationMenu(BufferedReader consoleReader, Navigator navigator, UserService userService, AccountService accountService) {
        super("Account Creation", "/account_creator", consoleReader, navigator);
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void render() throws Exception {
        String input;

        System.out.println("/----------------------------------\\");
        System.out.printf(" Account Creation for %s:", userService.getCurrentUser().getUsername());
        System.out.print("Account Name: ");
        input = consoleReader.readLine();

        System.out.println("2) Create New Account");
        System.out.println("3) Add User to existing account");
        System.out.println("4) Exit to Main Menu");
        System.out.println("\\----------------------------------/");
        System.out.println("Please select an option:");
        System.out.print(">> ");





        }
}
