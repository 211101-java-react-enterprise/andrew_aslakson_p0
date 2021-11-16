package com.revature.project_0.services;

import com.revature.project_0.daos.UserDAO;
import com.revature.project_0.exceptions.InvalidCredentialException;
import com.revature.project_0.exceptions.ResourcePersistenceException;
import com.revature.project_0.models.User;

/**
 *      UserService class holds methods and logic pertaining to
 *      user entered data verification
 *
 *      This class also holds an instance of the UserDAO class
 *      this class acts as an intermediary step for accessing
 *      data persistence classes.
 */

public class UserService {

    //0000000000000000000000000000000000000000000000000

    private UserDAO userDAO;
    private User currentUser;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    //-------------------------------------------------

    // Verifies user credentials and accesses userDAO to persist user in database
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

    //-------------------------------------------------

    //Used during login scenarios
    public void authenticate(String username, String password) {
        if (!username.trim().equals("") && username != null && !password.trim().equals("") && password != null) {
            currentUser = userDAO.findUserByUsernameAndPassword(username, password);
        }

    }

    //-------------------------------------------------

    public User getCurrentUser() {
        return currentUser;
    }

    //-------------------------------------------------

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    //-------------------------------------------------

    public void endSession() {
        currentUser = null;
    }

    //-------------------------------------------------

    public boolean isActiveUser() {
        return currentUser != null;
    }

    //-------------------------------------------------

    //Private method encapsulates necessary logic to determine if data is valid for database
    private boolean isUserValid(User user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getEmail() == null || user.getEmail().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        return user.getPassword() != null && !user.getPassword().trim().equals("");
    }

    //-------------------------------------------------

}
