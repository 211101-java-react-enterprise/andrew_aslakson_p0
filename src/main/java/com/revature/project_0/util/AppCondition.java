package com.revature.project_0.util;

import com.revature.project_0.daos.AccountDAO;
import com.revature.project_0.daos.TransactionDAO;
import com.revature.project_0.daos.UserDAO;
import com.revature.project_0.screens.*;
import com.revature.project_0.services.AccountService;
import com.revature.project_0.services.TransactionService;
import com.revature.project_0.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppCondition {

    private static boolean running;
    private final Navigator navigator;
    private final BufferedReader reader;

    private final UserService userService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    public AppCondition() {
        running = true;
        navigator = new Navigator();
        reader = new BufferedReader(new InputStreamReader(System.in));

        userService = new UserService(new UserDAO());
        accountService = new AccountService(new AccountDAO());
        transactionService = new TransactionService(new TransactionDAO());

        // TODO add MORE Menus here!
        navigator.addMenu(new WelcomeMenu(reader, navigator));
        navigator.addMenu(new MainMenu(reader, navigator));
        navigator.addMenu(new UserRegisterMenu(reader, navigator, userService));
        navigator.addMenu(new LoginMenu(reader, navigator, userService));
        navigator.addMenu(new UserMenu(reader, navigator, userService));
        navigator.addMenu(new EndSessionMenu(reader, navigator));
        navigator.addMenu(new AccountViewerMenu(reader, navigator, userService, accountService));
        navigator.addMenu(new AccountCreationMenu(reader, navigator, userService, accountService));
        navigator.addMenu(new BalanceViewerMenu(reader, navigator, accountService, transactionService));
        navigator.addMenu(new AccountActionMenu(reader, navigator, accountService));
        navigator.addMenu(new WithdrawalMenu(reader, navigator, accountService, transactionService));
        navigator.addMenu(new DepositMenu(reader, navigator, accountService, transactionService));

    }

    public void startApp() {
        try {
            while (running) {
                navigator.navigateTo("/welcome");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown() {
        running = false;
    }
}
