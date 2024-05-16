package com.authenticationservice.authenticationservice.exception;

public class UserAlreadyExistsException extends  Exception{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}