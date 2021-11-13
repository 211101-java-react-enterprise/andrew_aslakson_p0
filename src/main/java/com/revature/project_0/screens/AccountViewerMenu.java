package com.revature.project_0.screens;

import com.revature.project_0.models.accounts.Account;
import com.revature.project_0.services.AccountService;
import com.revature.project_0.services.UserService;
import com.revature.project_0.util.collections.DoubleLinkedList;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

public class AccountViewerMenu extends Menu {

    private UserService userService;
    private AccountService accountService;
    private DoubleLinkedList<Account> accounts;

    public AccountViewerMenu(BufferedReader consoleReader, Navigator navigator, UserService userService, AccountService accountService) {
        super("Account Viewer", "/account_viewer", consoleReader, navigator);
        this.userService = userService;
        this.accountService = accountService;

        accounts = new DoubleLinkedList<>();
    }

    @Override
    public void render() throws Exception {


        System.out.println("/-----------------------------\\");
        System.out.println("           Accounts");

        accounts = accountService.getAccounts(userService.getCurrentUser().getUserUUID());

        System.out.println("#) || Account Name : Type  : Current Balance");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(Integer.toString(i) + ")|| " + accounts.next().toString());
        }
        System.out.println("\\-----------------------------/");
        System.out.println("Which account would you like to view?");
        System.out.print(">> ");

        String input = consoleReader.readLine();

        accountService.setCurrentAccount(accounts.get(Integer.parseInt(input)));

        navigator.navigateTo("/account_action");
    }
}
