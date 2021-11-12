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

    public DoubleLinkedList<Account> getAccounts(String userUUID) {
        if (!userUUID.trim().equals("") && userUUID != null) {
            accountDAO.findAccounts(userUUID);
        }

        return null;
    }

    public Account register(Account account, String userUUID) {
        if (!isAccountValid(account)) {
            return null;
        }

        if (accountDAO.findAccountByUserIDAndName(account.getName(), userUUID) == null) {

        }
        return null;
    }

    private boolean isAccountValid(Account account) {
        if (account == null) return false;
        if (account.getName().trim().equals("") || account.getName() == null) return false;
        return account.getType() != null;
    }
}
