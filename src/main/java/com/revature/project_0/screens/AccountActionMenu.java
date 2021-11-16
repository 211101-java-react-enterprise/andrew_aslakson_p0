package com.revature.project_0.screens;

import com.revature.project_0.services.AccountService;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

/**
 *      AccountActionMenu is a set of options regarding what a user
 *      would like to do to a particular account
 *
 *      currentUser and currentAccount should be set when a user
 *      is accessing this menu
 */

public class AccountActionMenu extends Menu {

    //0000000000000000000000000000000000000000000000000

    private AccountService accountService;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public AccountActionMenu(BufferedReader consoleReader, Navigator navigator, AccountService accountService) {
        super("Account Action", "/account_action", consoleReader, navigator);
        this.accountService = accountService;

    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    @Override
    public void render() throws Exception {
        while (accountService.getCurrentAccount() != null) {
            System.out.println("/----------------------------------\\");
            System.out.println("           Account Actions");
            System.out.println("1) View Previous Transactions");
            System.out.println("2) Make a Deposit");
            System.out.println("3) Make a Withdrawal");
            System.out.println("4) Add another User to this account");
            System.out.println("5) Exit to User Menu");
            System.out.println("\\----------------------------------/");
            System.out.println("Please select an option:");
            System.out.print(">> ");

            String input = consoleReader.readLine();

            switch (input) {
                case "1":
                    navigator.navigateTo("/balance_viewer");
                    break;
                case "2":
                    navigator.navigateTo("/deposit");
                    break;
                case "3":
                    navigator.navigateTo("/withdrawal");
                    break;
                case "4":
                    navigator.navigateTo("/add_user_to_existing_account");
                    break;
                case "5":
                    accountService.setCurrentAccount(null);
                    break;
                default:
                    System.out.println("Invalid selection, please try again.");
            }
        }
    }

    //-------------------------------------------------

}

