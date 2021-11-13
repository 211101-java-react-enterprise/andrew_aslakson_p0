package com.revature.project_0.models.accounts;

public class SavingsAccount extends Account {

    private final double INTEREST_RATE = 0.1;

    public SavingsAccount(String name, double balance) {
        super(name, balance);
        this.type = "S";
    }
}
