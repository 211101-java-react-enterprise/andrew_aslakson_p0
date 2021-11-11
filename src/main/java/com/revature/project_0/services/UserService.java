package com.revature.project_0.services;

import com.revature.project_0.daos.UserDAO;
import com.revature.project_0.models.User;

public class UserService {

    private UserDAO userDAO;

    private User currentUser;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    private boolean isUserValid(User user) {
        //TODO: Implement this
        return false;
    }

}
