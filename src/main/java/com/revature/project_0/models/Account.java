package com.revature.project_0.models;

import com.revature.project_0.util.DoubleLinkedList;
/**
 *      Possibly should be abstract with checking account and savings account classes
 */
public class Account {
    private String uuid;
    private DoubleLinkedList<Transaction> transactions;
        //TODO
    String name;

    User user;

    double currentBalance;

    Account() {
        transactions = new DoubleLinkedList<>();

    }

    User getUser() {
        return user;
    }


}
