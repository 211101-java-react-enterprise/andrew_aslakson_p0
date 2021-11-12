package com.revature.project_0.services;

import com.revature.project_0.daos.AccountDAO;
import com.revature.project_0.models.Account;
import com.revature.project_0.util.DoubleLinkedList;

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
}
