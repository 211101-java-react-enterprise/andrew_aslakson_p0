package com.revature.project_0.exceptions;

/**
 *      RuntimeException with more descriptive name
 *
 *      used when user enters invalid credentials
 */

public class InvalidCredentialException extends RuntimeException {

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public InvalidCredentialException(String msg) {
        super(msg);
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

}
