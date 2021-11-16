package com.revature.project_0.models.accounts;

import com.revature.project_0.models.Transaction;
import com.revature.project_0.util.collections.DoubleLinkedList;

/**
 *      Account class is a data model which can hold information relative to an account
 *
 *      Note transactions is not populated until necessary.
 *
 *      Includes toString implementation that is custom-built for this program
 *
 *      Includes some methods that are helpful for manipulating transactions List
 */

public abstract class Account {

    //0000000000000000000000000000000000000000000000000

    protected String accountUUID;

    private DoubleLinkedList<Transaction> transactions;

    protected String name;

    protected double currentBalance;
    protected String type;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    Account(String name, double balance) {
        transactions = new DoubleLinkedList<>();
        this.name = name;
        this.currentBalance = balance;

    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public final String getAccountUUID() {
        return accountUUID;
    }

    public final void setAccountUUID(String accountUUID) {
        this.accountUUID = accountUUID;
    }

    //-------------------------------------------------

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    //-------------------------------------------------

    public final double getCurrentBalance() {
        return currentBalance;
    }

    public final void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    //-------------------------------------------------

    public final String getType() {
        return type;
    }

    public final void setType(String type) {
        this.type = type;
    }

    //-------------------------------------------------

    public final Transaction getNextTransaction() {
        return transactions.next();
    }

    public final Transaction getPrevTransaction() {
        return transactions.prev();
    }

    //-------------------------------------------------

    public final void addTransaction(Transaction transaction) {
        transactions.add(transaction);

    }

    //-------------------------------------------------

    @Override
    public String toString() {
        return String.format("%s : " + (type.equals("C") ? "Checking" : "Savings") + " : $%.2f", name, currentBalance);
    }

    //-------------------------------------------------

    public final int getNumOfTransactions() {
        return transactions.size();
    }

    //-------------------------------------------------

    public final void moveToTopOfTransactions() {
        transactions.moveToTop();
    }

    public final void moveToBottomOfTransactions() {
        transactions.moveToBottom();
    }

    //-------------------------------------------------

    public void clearTransactions() {
        transactions.clear();
    }

    //-------------------------------------------------

}
