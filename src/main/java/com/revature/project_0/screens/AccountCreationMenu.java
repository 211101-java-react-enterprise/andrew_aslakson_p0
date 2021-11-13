package com.revature.project_0.screens;

import com.revature.project_0.models.accounts.Account;
import com.revature.project_0.models.accounts.CheckingAccount;
import com.revature.project_0.models.accounts.SavingsAccount;
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
        System.out.println("/----------------------------------\\");
        System.out.printf(" Account Creation for %s:", userService.getCurrentUser().getUsername());
        System.out.print("Account Name: ");
        String name = consoleReader.readLine();

        System.out.println("What type of account will this be?");
        System.out.println("1) Checking Account");
        System.out.println("2) Savings Account");
        System.out.print(">> ");
        String input = consoleReader.readLine();

        System.out.println("\\----------------------------------/");

        Account newAccount = null;
        switch (input) {
            case "1":
                newAccount = new CheckingAccount(name, 0.0);

                break;
            case "2":
                newAccount = new SavingsAccount(name, 0.0);
                break;
            default:

        }

        accountService.register(newAccount, userService.getCurrentUser().getUserUUID());

        System.out.println("Account Persisted Successfully!");

    }
}
