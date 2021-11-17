package com.revature.project_0.services;

import com.revature.project_0.daos.UserDAO;
import com.revature.project_0.exceptions.InvalidCredentialException;
import com.revature.project_0.exceptions.ResourcePersistenceException;
import com.revature.project_0.models.User;
import com.revature.project_0.util.logger.Logger;

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

    private Logger logger;

    //0000000000000000000000000000000000000000000000000

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
        logger = Logger.getLogger();
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    //-------------------------------------------------

    //-------------------------------------------------

    // Verifies user credentials and accesses userDAO to persist user in database
    public boolean register(User user) {
        if (!isUserValid(user)) {
            logger.log("User provided invalid credentials:\n" +
                            "Provided user credentials: \n" +
                            "First Name: " + user.getFirstName()+ "\n" +
                            "Last Name: " + user.getLastName() + "\n" +
                            "E-Mail: " + user.getEmail()+ "\n" +
                            "Username: " + user.getUsername()+ "\n" +
                            "password: " + user.getPassword()+ "\n");

            throw new InvalidCredentialException("Invalid user input.");

        }

        logger.log("user validated");

        boolean nameAvailable = userDAO.findUserByUsername(user.getUsername()) == null;
        boolean emailAvailable = userDAO.findUserByEmail(user.getEmail()) == null;

        if (!nameAvailable || !emailAvailable) {
            String errMsg = "The values provided for the following fields are already taken by other users:";
            if (!nameAvailable) errMsg = errMsg + "\n\t- username";
            if (!emailAvailable) errMsg = errMsg + "\n\t- email";

            logger.log(errMsg);

            throw new ResourcePersistenceException(errMsg);
        }

        User userVerified = userDAO.save(user);

        if (userVerified == null) {
            logger.log("User failed to persist to database!");

            throw new ResourcePersistenceException("The user could not be persisted to the database!");
        }

        logger.log("User persisted to database successfully!");
        return true;
    }

    //-------------------------------------------------

    //Used during login scenarios
    public void authenticate(String username, String password) {
        if (!username.trim().equals("") && username != null && !password.trim().equals("") && password != null) {
            currentUser = userDAO.findUserByUsernameAndPassword(username, password);
            if (currentUser != null) {
                logger.log("User Authenticated successfully");
            }
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

    //Package-Private method encapsulates necessary logic to determine if data is valid for database
    boolean isUserValid(User user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getEmail() == null || user.getEmail().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        return user.getPassword() != null && !user.getPassword().trim().equals("");
    }

    //-------------------------------------------------

}
