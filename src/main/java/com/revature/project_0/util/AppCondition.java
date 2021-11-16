package com.revature.project_0.util;

import com.revature.project_0.daos.AccountDAO;
import com.revature.project_0.daos.TransactionDAO;
import com.revature.project_0.daos.UserDAO;
import com.revature.project_0.screens.*;
import com.revature.project_0.services.AccountService;
import com.revature.project_0.services.TransactionService;
import com.revature.project_0.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * App Condition stores the runtime information of the entire program
 *      - Holds navigator object so any screen can navigate to any screen
 *      - This class holds references to service classes that are necessary for program functions
 *          these service classes are generally passed into screens so only 1 of any of these
 *          objects need ever exist
 *
 *
 *      This class also holds the looping operation of the first menu screen
 *      it is based on whether running is true or false
 */
public class AppCondition {

    //0000000000000000000000000000000000000000000000000

    private static boolean running;
    private final Navigator navigator;
    private final BufferedReader reader;

    private final UserService userService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public AppCondition() {
        //initialize various services
        running = true;
        navigator = new Navigator();
        reader = new BufferedReader(new InputStreamReader(System.in));

        userService = new UserService(UserDAO.getInstance());
        accountService = new AccountService(AccountDAO.getInstance());
        transactionService = new TransactionService(TransactionDAO.getInstance());

        //Adding menus to navigator
        navigator.addMenu(new WelcomeMenu(reader, navigator));
        navigator.addMenu(new MainMenu(reader, navigator));
        navigator.addMenu(new UserRegisterMenu(reader, navigator, userService));
        navigator.addMenu(new LoginMenu(reader, navigator, userService));
        navigator.addMenu(new UserMenu(reader, navigator, userService));
        navigator.addMenu(new EndSessionMenu(reader, navigator));
        navigator.addMenu(new AccountViewerMenu(reader, navigator, userService, accountService));
        navigator.addMenu(new AccountCreationMenu(reader, navigator, userService, accountService));
        navigator.addMenu(new BalanceViewerMenu(reader, navigator, accountService, transactionService));
        navigator.addMenu(new AccountActionMenu(reader, navigator, accountService));
        navigator.addMenu(new WithdrawalMenu(reader, navigator, accountService, transactionService));
        navigator.addMenu(new DepositMenu(reader, navigator, accountService, transactionService));
        navigator.addMenu(new AddUserToExistingAccountMenu(reader, navigator, userService, accountService));

    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    //called at program start, sends user to welcome screen
    public void startApp() {
        try {
            while (running) {
                navigator.navigateTo("/welcome");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-------------------------------------------------

    // called to shut down program
    public static void shutdown() {
        running = false;
    }

    //-------------------------------------------------

}
