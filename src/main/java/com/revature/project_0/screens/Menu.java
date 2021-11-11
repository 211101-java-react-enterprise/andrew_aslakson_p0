package com.revature.project_0.screens;

import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

public abstract class Menu {

    protected String name;
    protected String address;
    protected BufferedReader consoleReader;
    protected Navigator navigator;

    public Menu(String name, String address, BufferedReader consoleReader, Navigator navigator) {
        this.name = name;
        this.address = address;
        this.consoleReader = consoleReader;
        this.navigator = navigator;

    }

    public final String getName() {return name;}

    public final String getAddress() {return address;}

    public abstract void render() throws Exception;

}
