package com.revature.project_0.services;

import com.revature.project_0.daos.TransactionDAO;
import com.revature.project_0.exceptions.InvalidCredentialException;
import com.revature.project_0.exceptions.ResourcePersistenceException;
import com.revature.project_0.models.Transaction;
import com.revature.project_0.models.User;
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

    public Transaction register(Transaction transaction) {

        if (!isTransactionValid(transaction)) {
            throw new InvalidCredentialException("Invalid user input.");
        }

        Transaction transactionVerified = transactionDAO.save(transaction);

        if (transactionVerified == null) {
            throw new ResourcePersistenceException("The user could not be persisted to the database!");
        }

        return transactionVerified;
    }

    private boolean isTransactionValid(Transaction transaction) {
        return (transaction.getNewBalance() >= 0 && transaction.getAmount() > 0 );
    }


}
