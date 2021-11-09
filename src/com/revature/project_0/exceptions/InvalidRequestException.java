package com.revature.project_0.exceptions;

public class InvalidRequestException extends RuntimeException {
    InvalidRequestException(String msg) {
        super(msg);
    }
}
