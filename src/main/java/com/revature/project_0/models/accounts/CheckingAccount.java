package com.revature.project_0.models.accounts;

public class CheckingAccount extends Account {

    public CheckingAccount(String name, double balance, String type) {
        super(name, balance);
        this.type = type;
    }
}
