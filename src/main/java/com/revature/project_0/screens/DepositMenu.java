package com.revature.project_0.screens;

import com.revature.project_0.models.accounts.Account;
import com.revature.project_0.services.AccountService;
import com.revature.project_0.services.TransactionService;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

public class DepositMenu extends Menu {

    private AccountService accountService;
    private TransactionService transactionService;

    public DepositMenu(BufferedReader consoleReader, Navigator navigator, AccountService accountService, TransactionService transactionService) {
        super("Deposit", "/deposit", consoleReader, navigator);
        this.accountService = accountService;
        this.transactionService = transactionService;

    }

    @Override
    public void render() throws Exception {

    }
}
