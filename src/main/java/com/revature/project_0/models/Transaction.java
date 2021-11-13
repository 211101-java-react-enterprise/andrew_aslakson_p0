package com.revature.project_0.models;

import java.util.Date;

public class Transaction {
    String transactionUUID;
    String accountUUID;

    private boolean type; // true is deposit, false is withdrawl
    private Date dateTime;

    private double amount;
    private double newBalance;
    private double oldBalance;

    private final String RED_TEXT = "\u001B[31m";
    private final String GREEN_TEXT = "\u001B[32m";
    private final String RESET_TEXT = "\u001B[0m";

    public Transaction(boolean type, Date date, double amount, double oldBalance) {
        this.type = type;
        this.dateTime = date;

        this.amount = amount;
        this.oldBalance = oldBalance;
        this.newBalance = (type) ? (amount + oldBalance) : (oldBalance - amount);

    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(double newBalance) {
        this.newBalance = newBalance;
    }

    public double getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(double oldBalance) {
        this.oldBalance = oldBalance;
    }

    @Override
    public String toString() {
        //       #) || Date & Time of transaction : Type  : Amount : Current Balance
        return String.format(" %s : %s $%.2f%s : $%.2f",
                dateTime.toString(),
                type ? "Deposit :" + GREEN_TEXT : "Withdrawl :" + RED_TEXT,
                amount,
                RESET_TEXT,
                newBalance);
    }
}
