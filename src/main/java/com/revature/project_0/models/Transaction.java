package com.revature.project_0.models;

import java.sql.Timestamp;

/**
 *      Transaction is a data model that stores information
 *      pertaining to a specific transaction
 *
 *      Includes overridden toString() method that formats
 *      object information to be suitable for this program
 */

public class Transaction {

    //0000000000000000000000000000000000000000000000000

    private String transactionUUID;

    private String accountUUID;

    private boolean type; // true is deposit, false is withdrawl
    private Timestamp dateTime;

    private double amount;
    private double newBalance;
    private double oldBalance;

    private final String RED_TEXT = "\u001B[31m";
    private final String GREEN_TEXT = "\u001B[32m";
    private final String RESET_TEXT = "\u001B[0m";

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public Transaction(boolean type, Timestamp date, double amount, double oldBalance) {
        this.type = type;
        this.dateTime = date;

        this.amount = amount;
        this.oldBalance = oldBalance;
        this.newBalance = (type) ? (amount + oldBalance) : (oldBalance - amount);

    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    //-------------------------------------------------

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    //-------------------------------------------------

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    //-------------------------------------------------

    public double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(double newBalance) {
        this.newBalance = newBalance;
    }

    //-------------------------------------------------

    public double getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(double oldBalance) {
        this.oldBalance = oldBalance;
    }

    //-------------------------------------------------

    public String getAccountUUID() {
        return accountUUID;
    }

    public void setAccountUUID(String accountUUID) {
        this.accountUUID = accountUUID;
    }

    //-------------------------------------------------

    public String getTransactionUUID() {
        return transactionUUID;
    }

    public void setTransactionUUID(String transactionUUID) {
        this.transactionUUID = transactionUUID;
    }

    //-------------------------------------------------

    @Override
    public String toString() {
        //       #) || Date & Time of transaction : Type  : Amount : Current Balance
        return String.format(" %.19s : %s $%.2f%s : $%.2f",
                dateTime.toString(),
                type ? "Deposit :" + GREEN_TEXT : "Withdrawl :" + RED_TEXT,
                amount,
                RESET_TEXT,
                newBalance);
    }

    //-------------------------------------------------

}
