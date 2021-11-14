package com.revature.project_0.screens;

import com.revature.project_0.models.Transaction;
import com.revature.project_0.models.accounts.Account;
import com.revature.project_0.services.AccountService;
import com.revature.project_0.services.TransactionService;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Calendar;

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
        System.out.println("/----------------------------------\\");
        System.out.println("        Creating a New Deposit");
        System.out.println("        For Account: " + accountService.getCurrentAccount().getName());
        System.out.println();
        System.out.println(" How much would you like to deposit?");
        System.out.print(">> $");

        String amount = consoleReader.readLine();

        System.out.println("\\----------------------------------/");

        Transaction transaction = new Transaction(true, new Timestamp(System.currentTimeMillis()), Double.parseDouble(amount),accountService.getCurrentAccount().getCurrentBalance());
        transaction.setAccountUUID(accountService.getCurrentAccount().getAccountUUID());

        Transaction verifiedTransaction = transactionService.register(transaction);

        if (verifiedTransaction != null) {
            System.out.println("Transaction Persisted to Database Successfully!");
            if (accountService.updateBalance(verifiedTransaction.getNewBalance())) {
                System.out.println("Balance on account updated successfully!");
            }
        }

    }
}
