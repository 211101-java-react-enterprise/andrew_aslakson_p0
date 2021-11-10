package com.revature.project_0.screens;

import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

public class AccountViewerMenu extends Menu {

    public AccountViewerMenu(BufferedReader consoleReader, Navigator navigator) {
        super("Account Viewer", "/account_viewer", consoleReader, navigator);
    }

    @Override
    public void render() throws Exception {
        System.out.println("/-----------------------------\\");
        System.out.println("           Accounts");

        //TODO: Pull accounts related to this user from database
    }
}
