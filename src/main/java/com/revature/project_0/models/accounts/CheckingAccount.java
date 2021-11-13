package com.revature.project_0.models.accounts;

public class CheckingAccount extends Account {

    private final double INTEREST_RATE = 0.06;

    public CheckingAccount(String name, double balance) {
        super(name, balance);
        this.type = "C";
    }
}
