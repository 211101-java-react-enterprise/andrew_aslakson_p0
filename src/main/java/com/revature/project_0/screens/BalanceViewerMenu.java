package com.revature.project_0.screens;

import com.revature.project_0.services.AccountService;
import com.revature.project_0.services.TransactionService;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

public class BalanceViewerMenu extends Menu {

    private AccountService accountService;
    private TransactionService transactionService;

    public BalanceViewerMenu(BufferedReader consoleReader, Navigator navigator, AccountService accountService, TransactionService transactionService) {
        super("Balance Viewer", "/balance_viewer", consoleReader, navigator);
        this.accountService = accountService;
        this.transactionService = transactionService;

    }

    @Override
    public void render() throws Exception {

        //Populate currentAccount with Transactions from DB
        transactionService.populateTransactions(accountService.getCurrentAccount());
        accountService.getCurrentAccount().moveToTopOfTransactions();

        System.out.println("/-----------------------------\\");
        System.out.println("       Transaction History");

        System.out.println("#) || Date & Time of transaction : Type  : Amount : Current Balance");
        for (int i = 0; i < accountService.getCurrentAccount().getNumOfTransactions(); i++) {
            System.out.println(Integer.toString(i) + ")|| " + accountService.getCurrentAccount().getPrevTransaction().toString());
        }
        System.out.println("\\-----------------------------/");
        System.out.println("Press enter to return to User menu");
        System.out.print(">> ");

    }
}
