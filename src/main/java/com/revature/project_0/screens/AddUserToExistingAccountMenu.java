package com.revature.project_0.screens;

import com.revature.project_0.models.User;
import com.revature.project_0.services.AccountService;
import com.revature.project_0.services.UserService;
import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

/**
 *      Allows a logged in user to allow another user to access an account
 *
 *      This is my implementation of a joint account feature
 *
 *      currentUser is actually stored in a temporary variable while
 *      authenticating a second user to link to an account
 *
 *      currentUser and currentAccount should have valid instances when
 *      user is accessing this screen
 *
 *      similar implementation as regular login screen
 */

public class AddUserToExistingAccountMenu extends Menu {

    //0000000000000000000000000000000000000000000000000

    private UserService userService;
    private AccountService accountService;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public AddUserToExistingAccountMenu(BufferedReader consoleReader, Navigator navigator, UserService userService, AccountService accountService) {
        super("Add User To Existing Account", "/add_user_to_existing_account", consoleReader, navigator);
        this.userService = userService;
        this.accountService = accountService;

    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    @Override
    public void render() throws Exception {
        System.out.println("/----------------------------------\\");
        System.out.println("        Add User To Account");
        System.out.println("      Please enter additional");
        System.out.println("        users credentials:\n");

        System.out.print("Username: ");
        String username = consoleReader.readLine();

        System.out.print("Password: ");
        String password = consoleReader.readLine();

        System.out.println("\\----------------------------------/");

        //DEBUG OUTPUT
        /*
        System.out.println("Provided user credentials:");
        System.out.printf("Username: %s\n" +
                          "Password: %s\n",
                          username, password);
        */
        User tempCurrentUser = userService.getCurrentUser();
        userService.authenticate(username, password);

        if (userService.getCurrentUser() != null) {
            if (accountService.linkAccountToUser(accountService.getCurrentAccount().getAccountUUID(), userService.getCurrentUser().getUserUUID())) {
                System.out.printf("User: %s now has access to account: %s\n",
                        userService.getCurrentUser().getUsername(),
                        accountService.getCurrentAccount().getName());
            }
        } else {
            System.out.println("User authenticated but changes failed to commit to database!");
        }
        userService.setCurrentUser(tempCurrentUser);

    }

    //-------------------------------------------------

}
