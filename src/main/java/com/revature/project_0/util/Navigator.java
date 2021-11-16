package com.revature.project_0.util;

import com.revature.project_0.screens.Menu;
import com.revature.project_0.util.collections.DoubleLinkedList;

/**
 * Navigator class holds references to all the different menus of the program
 *
 * The method navigateTo() is called when you wish to navigate to and display a different menu
 */

public class Navigator {
    //0000000000000000000000000000000000000000000000000

    // Container with references to menus
    private final DoubleLinkedList<Menu> menus;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //Constructor, just initializes collection
    public Navigator() {
        menus = new DoubleLinkedList<Menu>();

    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    //adds additional menus to the collection, best practice is to add all menus directly after intialization
    public void addMenu(Menu menu) {
        menus.add(menu);
    }

    //-------------------------------------------------

    //changes menu when necessary for user
    //also invokes menu render() method to display new menu to user
    public void navigateTo(String address) throws Exception {
        menus.moveToBottom();
        for (int i = 0; i < menus.size(); i++) {
            Menu tempMenu = menus.next();
            if (tempMenu.getAddress().equals(address)) {
                tempMenu.render();
            }
        }
    }

    //-------------------------------------------------

}
