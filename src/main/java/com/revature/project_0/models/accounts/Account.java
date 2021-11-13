package com.revature.project_0.models.accounts;

import com.revature.project_0.models.Transaction;
import com.revature.project_0.util.collections.DoubleLinkedList;
/**
 *      Possibly should be abstract with checking account and savings account classes
 */
public abstract class Account {
    protected String accountUUID;

    private DoubleLinkedList<Transaction> transactions;

    protected String name;

    protected double currentBalance;
    protected String type;

    Account(String name, double balance) {
        transactions = new DoubleLinkedList<>();
        this.name = name;
        this.currentBalance = balance;

    }

    public final String getAccountUUID() {
        return accountUUID;
    }

    public final void setAccountUUID(String accountUUID) {
        this.accountUUID = accountUUID;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final double getCurrentBalance() {
        return currentBalance;
    }

    public final void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public final String getType() {
        return type;
    }

    public final void setType(String type) {
        this.type = type;
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


    @Override
    public String toString() {
        return String.format("%s : " + (type.equals("C") ? "Checking" : "Savings") + " : $%.2f", name, currentBalance);
    }
}
