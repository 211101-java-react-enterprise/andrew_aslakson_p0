package com.revature.project_0.models;

import com.revature.project_0.util.DoubleLinkedList;

public class Account {
    private String uuid;
    private DoubleLinkedList<Transaction> transactions;

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
