package com.revature.project_0;

import com.revature.project_0.util.AppCondition;

/**
 * Project_0
 *      -Banking app
 *          + Can create new user accounts
 *          + User can create accounts
 *          + User can make deposits and withdrawals to accounts
 *          + All data is persisted to database
 *          + Users can have access to as many accounts as they would like
 *          + Accounts can be accessed by multiple users
 */

public class P0Driver {
    //MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
    public static void main(String[] args) {
        AppCondition app = new AppCondition();
        app.startApp();
    }
    //MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
}
