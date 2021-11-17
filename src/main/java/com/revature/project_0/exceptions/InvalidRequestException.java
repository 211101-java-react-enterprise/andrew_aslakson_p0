package com.revature.project_0.exceptions;

/**
 *      RuntimeException with more informative name
 *      used when user performs an invalid request
 */

public class InvalidRequestException extends RuntimeException {

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public InvalidRequestException(String msg) {
        super(msg);
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

}
