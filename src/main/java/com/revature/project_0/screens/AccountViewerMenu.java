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

        //TODO: Pull accounts related to this user from database
    }
}
