package com.revature.project_0.services;

import com.revature.project_0.daos.TransactionDAO;
import com.revature.project_0.exceptions.InvalidCredentialException;
import com.revature.project_0.exceptions.ResourcePersistenceException;
import com.revature.project_0.models.Transaction;
import com.revature.project_0.models.accounts.Account;

/**
 *      Transaction service is a class that ensures user entered data integrity
 *          - This class holds a reference to an instance of the TransactionDAO
 *              class and uses that class's methods to persist data to a database
 */

public class TransactionService {

    //0000000000000000000000000000000000000000000000000

    private TransactionDAO transactionDAO;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;

    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    // called to fill List of transactions in account class
    public void populateTransactions(Account currentAccount) {
        currentAccount.clearTransactions();
        transactionDAO.populateTransactions(currentAccount);
    }

    //-------------------------------------------------

    // verifies and persists transaction related data to database
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

    //-------------------------------------------------

    // Private method, ensures integrity of user entered data
    private boolean isTransactionValid(Transaction transaction) {
        return (transaction.getNewBalance() >= 0 && transaction.getAmount() > 0 );
    }

    //-------------------------------------------------

}
