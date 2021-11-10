package com.revature.project_0.models;

import java.sql.Date;

public class Transaction {
    private boolean type; // true is deposit, false is withdrawl
    private Date dateTime;

    private double amount;
    private double newBalance;
    private double oldBalance;

    private User user;

    Transaction(boolean type, Date date, double amount, double oldBalance, User user) {
        this.type = type;
        this.dateTime = date;

        this.amount = amount;
        this.oldBalance = oldBalance;
        this.newBalance = (type) ? (amount + oldBalance) : (oldBalance - amount);

        this.user = user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}