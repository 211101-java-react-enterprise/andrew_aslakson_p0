package com.revature.project_0.models;

import com.revature.project_0.util.DoubleLinkedList;
/**
 *      Possibly should be abstract with checking account and savings account classes
 */
public abstract class Account {
    private String uuid;
    private DoubleLinkedList<Transaction> transactions;
        //TODO
    String name;

    private double currentBalance;

    Account() {
        transactions = new DoubleLinkedList<>();

    }

    public final Transaction getNextTransaction() {
        return transactions.next();
    }
    public final Transaction getPrevTransaction() {
        return transactions.prev();
    }

    public final void addTransaction(Transaction transaction) {
        transactions.add(transaction);

    }



}
