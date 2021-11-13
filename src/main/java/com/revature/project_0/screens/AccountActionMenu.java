package com.revature.project_0.screens;

import com.revature.project_0.services.AccountService;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

public class AccountActionMenu extends Menu {

    private AccountService accountService;
    public AccountActionMenu(BufferedReader consoleReader, Navigator navigator, AccountService accountService) {
        super("Account Action", "/account_action", consoleReader, navigator);
        this.accountService = accountService;

    }

    @Override
    public void render() throws Exception {
        System.out.println("/----------------------------------\\");
        System.out.println("           Account Actions");
        System.out.println("1) View Previous Transactions");
        System.out.println("2) Make a Deposit");
        System.out.println("3) Make a Withdrawal");
        System.out.println("4) Exit to User Menu");
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
                accountService.setCurrentAccount(null);
                break;
            default:
                //TODO: Implement Me
        }
    }
}

