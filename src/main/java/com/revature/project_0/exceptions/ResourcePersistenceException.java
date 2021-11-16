package com.revature.project_0.exceptions;

/**
 *      RuntimeException with more informative class name
 *      used when there is an issue persisting data
 */

public class ResourcePersistenceException extends RuntimeException {

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

    public ResourcePersistenceException(String msg) {
        super(msg);
    }

    //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

}
