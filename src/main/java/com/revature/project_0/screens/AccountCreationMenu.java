package com.revature.project_0.screens;

import com.revature.project_0.models.accounts.Account;
import com.revature.project_0.models.accounts.CheckingAccount;
import com.revature.project_0.models.accounts.SavingsAccount;
import com.revature.project_0.services.AccountService;
import com.revature.project_0.services.UserService;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

/**
 *      AccountCreationMenu allows a user to create a new account, it then leverages
 *      accountService and therefore accountDAO to verify validity of user entered
 *      information and persist that info to database
 *
 *      currentUser should be set before a user views this screen
 */

public class AccountCreationMenu extends Menu{

    //0000000000000000000000000000000000000000000000000

    private UserService userService;
    private AccountService accountService;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public AccountCreationMenu(BufferedReader consoleReader, Navigator navigator, UserService userService, AccountService accountService) {
        super("Account Creation", "/account_creator", consoleReader, navigator);
        this.userService = userService;
        this.accountService = accountService;
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

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

        try {
            accountService.register(newAccount, userService.getCurrentUser().getUserUUID());
            System.out.println("Account Persisted Successfully!");

        } catch (Exception e) {
            System.out.println("Account creation failed:");
            System.out.println("   Please note a user cannot");
            System.out.println("   have two accounts with the");
            System.out.println("   same name.");

        }


    }

    //-------------------------------------------------

}
