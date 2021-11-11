package com.revature.project_0.util;

import com.revature.project_0.screens.Menu;

public class Navigator {
    private final DoubleLinkedList<Menu> menus;

    public Navigator() {
        menus = new DoubleLinkedList<Menu>();

    }

    public void addMenu(Menu menu) {
        menus.add(menu);
    }

    public void navigateTo(String address) throws Exception {
        menus.moveToBottom();
        for (int i = 0; i < menus.size(); i++) {
            Menu tempMenu = menus.next();
            if (tempMenu.getAddress().equals(address)) {
                tempMenu.render();
            }
        }
    }
}
