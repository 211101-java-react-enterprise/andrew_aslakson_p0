package com.revature.project_0.models.accounts;

public class SavingsAccount extends Account {

    public SavingsAccount(String name, double balance, String type) {
        super(name, balance);
        this.type = type;
    }
}
