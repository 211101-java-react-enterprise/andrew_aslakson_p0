package com.revature.project_0.util;

import com.revature.project_0.screens.*;
import com.revature.project_0.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppCondition {

    private static boolean running;
    private final Navigator navigator;
    private final BufferedReader reader;

    private final UserService userservice;

    public AppCondition() {
        running = true;
        navigator = new Navigator();
        reader = new BufferedReader(new InputStreamReader(System.in));

        userservice = new UserService();

        // TODO add MORE Menus here!
        navigator.addMenu(new WelcomeMenu(reader, navigator));
        navigator.addMenu(new MainMenu(reader, navigator));
        navigator.addMenu(new UserRegisterMenu(reader, navigator));
        navigator.addMenu(new LoginMenu(reader, navigator));
        navigator.addMenu(new UserMenu(reader, navigator));
        navigator.addMenu(new EndSessionMenu(reader, navigator));
        navigator.addMenu(new AccountViewerMenu(reader, navigator));

    }

    public void startApp() {
        try {
            while (running) {
                navigator.navigateTo("/welcome");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown() {
        running = false;
    }
}
