package com.revature.project_0.screens;

import com.revature.project_0.models.Transaction;
import com.revature.project_0.services.AccountService;
import com.revature.project_0.services.TransactionService;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;
import java.sql.Timestamp;

/**
 *      The Withdrawal Menu allows a user to make a withdrawl from
 *      an existing account
 *
 *      Note: when a user is on this screen both currentUser from
 *      userService and currentAccount from accountService should
 *      have valid instances inside them.
 */

public class WithdrawalMenu extends Menu {

    //0000000000000000000000000000000000000000000000000

    private AccountService accountService;
    private TransactionService transactionService;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public WithdrawalMenu(BufferedReader consoleReader, Navigator navigator, AccountService accountService, TransactionService transactionService) {
        super("Withdrawal", "/withdrawal", consoleReader, navigator);
        this.accountService = accountService;
        this.transactionService = transactionService;

    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    @Override
    public void render() throws Exception {
        System.out.println("/----------------------------------\\");
        System.out.println("      Creating a New Withdrawal");
        System.out.println("       For Account: " + accountService.getCurrentAccount().getName());
        System.out.println();
        System.out.println(" How much would you like to withdraw?");
        System.out.print(">> $");

        String amount = consoleReader.readLine();

        System.out.println("\\----------------------------------/");

        Transaction transaction = new Transaction(false, new Timestamp(System.currentTimeMillis()), Double.parseDouble(amount),accountService.getCurrentAccount().getCurrentBalance());
        transaction.setAccountUUID(accountService.getCurrentAccount().getAccountUUID());

        try {
            Transaction verifiedTransaction = transactionService.register(transaction);
            accountService.getCurrentAccount().setCurrentBalance(verifiedTransaction.getNewBalance());

            if (verifiedTransaction != null) {
                System.out.println("Transaction Saved Successfully!");

                try {
                    if (accountService.updateBalance(verifiedTransaction.getNewBalance())) {
                        System.out.println("Balance on account updated successfully!");
                    }
                } catch (Exception e) {
                    System.out.println("Big Error, user doesn't enter this information");
                    System.out.println("something went horribly wrong when attempting");
                    System.out.println("to update balance on account");
                }
            }
        } catch (Exception e) {
            System.out.println("Transaction failed!");
            System.out.println("Please note:");
            System.out.println("Transaction values must not be negative");
            System.out.println("and you may not overdraw your account");
        }

    }

    //-------------------------------------------------

}
