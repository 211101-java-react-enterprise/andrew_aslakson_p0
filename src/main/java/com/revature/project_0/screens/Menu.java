package com.revature.project_0.screens;

import com.revature.project_0.util.Navigator;

import java.io.BufferedReader;

/**
 *      Superclass Menu defines many of the properties of all Menus in this package.
 *
 *      Menus must include:
 *          - a name
 *          - an address
 *          - a buffered reader for taking input
 *          - and a navigator to move from screen to screen
 *
 *      The render() method is overridden in subclasses to define the
 *      functionality of individual menus.
 *
 *      It is best to let menus fall out to the last looping structure rather than
 *      navigate to a previous page, this avoids adding more and more stack frames
 *      onto the stack.
 */

public abstract class Menu {

    //0000000000000000000000000000000000000000000000000

    protected String name;
    protected String address;
    protected BufferedReader consoleReader;
    protected Navigator navigator;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public Menu(String name, String address, BufferedReader consoleReader, Navigator navigator) {
        this.name = name;
        this.address = address;
        this.consoleReader = consoleReader;
        this.navigator = navigator;

    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    public final String getName() {return name;}

    //-------------------------------------------------

    public final String getAddress() {return address;}

    //-------------------------------------------------

    // This method is overridden in subclasses and contains the
    // functional elements of a menu
    public abstract void render() throws Exception;

    //-------------------------------------------------

}
