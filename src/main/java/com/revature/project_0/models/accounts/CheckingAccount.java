package com.revature.project_0.models.accounts;

/**
 *      Checking account is differentiated from
 *      the account superclass by having an
 *      interest rate (unimplemented)
 *
 *      type will always be "C"
 *
 *      all functionality falls back to superclass
 */

public class CheckingAccount extends Account {

    //0000000000000000000000000000000000000000000000000

    private final double INTEREST_RATE = 0.06;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public CheckingAccount(String name, double balance) {
        super(name, balance);
        this.type = "C";
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

}
