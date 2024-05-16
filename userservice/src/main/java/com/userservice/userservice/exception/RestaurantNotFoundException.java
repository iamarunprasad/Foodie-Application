package com.userservice.userservice.exception;

public class RestaurantNotFoundException extends Exception {
    public RestaurantNotFoundException(String message){
        super(message);
    }
}
