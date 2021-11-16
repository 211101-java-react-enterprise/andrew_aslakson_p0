package com.revature.project_0.services;

import com.revature.project_0.daos.AccountDAO;
import com.revature.project_0.models.accounts.Account;
import com.revature.project_0.util.collections.DoubleLinkedList;

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

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    // Verifies that user entered information is valid
    // and then persists user to database
    public Account register(Account account, String userUUID) {
        if (!isAccountValid(account)) {
            return null;
        }

        Account newAccount;
        if (!accountDAO.doesUserIDHaveAccountName(userUUID, account.getName())) {
            newAccount = accountDAO.save(account);
            if (!accountDAO.linkAccountToUser(userUUID, newAccount.getAccountUUID())) {
                return null;
            }
            return newAccount;

        }
        return null;
    }

    //-------------------------------------------------

    // gets a list of accounts that user is associated with
    public DoubleLinkedList<Account> getAccounts(String userUUID) {
        if (!userUUID.trim().equals("") && userUUID != null) {
            return accountDAO.findAccounts(userUUID);
        }

        return null;
    }

    //-------------------------------------------------

    // Used when linking account that already exists to user that already exists
    public boolean linkAccountToUser(String accountUUID, String userUUID) {
        if (accountDAO.linkAccountToUser(userUUID, accountUUID)) {
            return true;
        }
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
    private boolean isAccountValid(Account account) {
        if (account == null) return false;
        if (account.getName().trim().equals("") || account.getName() == null) return false;
        return account.getType() != null;
    }

    //-------------------------------------------------

}
