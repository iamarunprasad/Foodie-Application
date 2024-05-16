package com.userservice.userservice.exception;

public class RestaurantAlreadyExistsException extends Exception {
    public RestaurantAlreadyExistsException(String message) {
        super(message);
    }
}
