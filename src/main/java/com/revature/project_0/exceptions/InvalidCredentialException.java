package com.revature.project_0.exceptions;

public class InvalidCredentialException extends RuntimeException {
    InvalidCredentialException(String msg) {
        super(msg);
    }
}
