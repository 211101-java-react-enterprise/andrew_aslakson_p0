package com.revature.project_0.services;

import com.revature.project_0.daos.TransactionDAO;
import com.revature.project_0.exceptions.InvalidCredentialException;
import com.revature.project_0.exceptions.ResourcePersistenceException;
import com.revature.project_0.models.Transaction;
import com.revature.project_0.models.accounts.Account;
import com.revature.project_0.util.logger.Logger;

/**
 *      Transaction service is a class that ensures user entered data integrity
 *          - This class holds a reference to an instance of the TransactionDAO
 *              class and uses that class's methods to persist data to a database
 */

public class TransactionService {

    //0000000000000000000000000000000000000000000000000

    private TransactionDAO transactionDAO;

    private Logger logger;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
        logger = Logger.getLogger();
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
            logger.log("Transaction is not valid\nEither the withdrawal is too large, or user input 0 or negative number");
            throw new InvalidCredentialException("Invalid user input.");
        }

        logger.log("Transaction Validated.");

        Transaction transactionVerified = transactionDAO.save(transaction);

        if (transactionVerified == null) {
            logger.log("Transaction failed to be persisted to database");
            throw new ResourcePersistenceException("The transaction could not be persisted to the database!");
        }

        logger.log("Transaction persisted to database successfully");

        return transactionVerified;
    }

    //-------------------------------------------------

    // Private method, ensures integrity of user entered data
    private boolean isTransactionValid(Transaction transaction) {
        return (transaction.getNewBalance() >= 0 && transaction.getAmount() > 0 );
    }

    //-------------------------------------------------

}
