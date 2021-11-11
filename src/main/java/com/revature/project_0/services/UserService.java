package com.revature.project_0.services;

import com.revature.project_0.daos.UserDAO;
import com.revature.project_0.exceptions.InvalidCredentialException;
import com.revature.project_0.exceptions.ResourcePersistenceException;
import com.revature.project_0.models.User;

public class UserService {

    private UserDAO userDAO;

    private User currentUser;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean register(User user) {
        if (!isUserValid(user)) {
            throw new InvalidCredentialException("Invalid user input.");
        }

        boolean nameAvailable = userDAO.findUserByUsername(user.getUsername()) == null;
        boolean emailAvailable = userDAO.findUserByEmail(user.getEmail()) == null;

        if (!nameAvailable || !emailAvailable) {
            String errMsg = "The values provided for the following fields are already taken by other users:";
            if (!nameAvailable) errMsg = errMsg + "\n\t- username";
            if (!emailAvailable) errMsg = errMsg + "\n\t- email";
            throw new ResourcePersistenceException(errMsg);
        }

        User userVerified = userDAO.save(user);

        if (userVerified == null) {
            throw new ResourcePersistenceException("The user could not be persisted to the database!");
        }

        return false;
    }

    public void authenticate(String username, String password) {
        //TODO: IMPLEMENT ME!

    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void endSession() {
        currentUser = null;
    }

    public boolean isActiveUser() {
        return currentUser != null;
    }

    private boolean isUserValid(User user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getEmail() == null || user.getEmail().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        return user.getPassword() != null && !user.getPassword().trim().equals("");
    }

}
