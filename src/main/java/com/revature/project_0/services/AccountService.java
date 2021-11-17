package com.revature.project_0.services;

import com.revature.project_0.daos.AccountDAO;
import com.revature.project_0.exceptions.InvalidCredentialException;
import com.revature.project_0.exceptions.InvalidRequestException;
import com.revature.project_0.exceptions.ResourcePersistenceException;
import com.revature.project_0.models.accounts.Account;
import com.revature.project_0.util.collections.DoubleLinkedList;
import com.revature.project_0.util.logger.Logger;

/**
 *      AccountService Holds relevant information and logic to verify user
 *      entered information is acceptable.
 *
 *      This class holds a reference to an instance of AccountDao which
 *      is used in persisting data to the database
 */

public class AccountService {

    //0000000000000000000000000000000000000000000000000

    private AccountDAO accountDAO;
    private Account currentAccount;

    private Logger logger;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
        logger = Logger.getLogger();
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    // Verifies that user entered information is valid
    // and then persists user to database
    public Account register(Account account, String userUUID) {
        if (!isAccountValid(account)) {
            logger.log("Invalid credentials provided when creating account");
            throw new InvalidRequestException("Invalid credentials given");
        }

        logger.log("Account credentials verified");

        Account newAccount;
        if (!accountDAO.doesUserIDHaveAccountName(userUUID, account.getName())) {
            logger.log("user does not already have account with name: " + account.getName());
            logger.log("Account is valid to save");

            newAccount = accountDAO.save(account);
            if (!accountDAO.linkAccountToUser(userUUID, newAccount.getAccountUUID())) {
                logger.log("Issue occurred when attempting to link account to user");

                throw new ResourcePersistenceException("Issue occurred when linking user to account");
            }

            logger.log("account persisted and link to user was made successfully");

            return newAccount;

        }
        logger.log("User already has account with given name, failed to persist new information");

        throw new InvalidRequestException("User already has an account with that name");
    }

    //-------------------------------------------------

    // gets a list of accounts that user is associated with
    public DoubleLinkedList<Account> getAccounts(String userUUID) {
        if (!userUUID.trim().equals("") && userUUID != null) {
            logger.log("UserUUID validated, ready to locate accounts");
            return accountDAO.findAccounts(userUUID);
        }

        return null;
    }

    //-------------------------------------------------

    // Used when linking account that already exists to user that already exists
    public boolean linkAccountToUser(String accountUUID, String userUUID) {
        if (accountDAO.linkAccountToUser(userUUID, accountUUID)) {
            logger.log("New User has been linked to account successfully");
            return true;
        }
        logger.log("Could not create new link from new user to account data persistence failed");
        return false;
    }

    //-------------------------------------------------

    public Account getCurrentAccount() {
        return currentAccount;
    }

    //-------------------------------------------------

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    //-------------------------------------------------

    public boolean updateBalance(double newBalance) {
        return accountDAO.updateBalance(newBalance, currentAccount.getAccountUUID());
    }

    //-------------------------------------------------

    // Private method holds logic to validate user entered values before accesing DAO
    boolean isAccountValid(Account account) {
        if (account == null) return false;
        if (account.getName().trim().equals("")) return false;
        return account.getType() != null;
    }

    //-------------------------------------------------

}
