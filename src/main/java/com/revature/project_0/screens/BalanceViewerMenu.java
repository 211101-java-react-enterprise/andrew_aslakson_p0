package com.revature.project_0.screens;

import com.revature.project_0.services.AccountService;
import com.revature.project_0.services.TransactionService;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

/**
 *      BalanceViewerMenu displays transactions relevant to
 *      the accountService.getCurrentAccount() account
 *
 *      An AccountService and a TransactionService is leveraged to do this
 *      Accounts don't know there transaction history until this point
 *
 *      accountService.currentAccount should be set when viewing this menu
 */

public class BalanceViewerMenu extends Menu {

    //0000000000000000000000000000000000000000000000000

    private AccountService accountService;
    private TransactionService transactionService;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public BalanceViewerMenu(BufferedReader consoleReader, Navigator navigator, AccountService accountService, TransactionService transactionService) {
        super("Balance Viewer", "/balance_viewer", consoleReader, navigator);
        this.accountService = accountService;
        this.transactionService = transactionService;

    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

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

    //-------------------------------------------------

}
