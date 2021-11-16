package com.revature.project_0.models.accounts;

/**
 *      Savings account is differentiated from
 *      the account superclass by having an
 *      interest rate (unimplemented)
 *
 *      type will always be "S"
 *
 *      all functionality falls back to superclass
 */

public class SavingsAccount extends Account {

    //0000000000000000000000000000000000000000000000000

    private final double INTEREST_RATE = 0.1;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public SavingsAccount(String name, double balance) {
        super(name, balance);
        this.type = "S";
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

}
