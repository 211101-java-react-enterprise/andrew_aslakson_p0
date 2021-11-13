package com.revature.project_0.services;

import com.revature.project_0.daos.AccountDAO;
import com.revature.project_0.models.accounts.Account;
import com.revature.project_0.util.collections.DoubleLinkedList;

public class AccountService {

    private AccountDAO accountDAO;

    private Account currentAccount;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public DoubleLinkedList<Account> getAccounts(String userUUID) {
        if (!userUUID.trim().equals("") && userUUID != null) {
            return accountDAO.findAccounts(userUUID);
        }

        return null;
    }

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

    private boolean isAccountValid(Account account) {
        if (account == null) return false;
        if (account.getName().trim().equals("") || account.getName() == null) return false;
        return account.getType() != null;
    }
}
