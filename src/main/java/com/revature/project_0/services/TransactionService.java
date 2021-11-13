package com.revature.project_0.services;

import com.revature.project_0.daos.TransactionDAO;
import com.revature.project_0.models.Transaction;
import com.revature.project_0.models.accounts.Account;

public class TransactionService {

    private TransactionDAO transactionDAO;

    private Transaction transaction;

    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;

    }

    public void populateTransactions(Account currentAccount) {
        transactionDAO.populateTransactions(currentAccount);
    }
}
