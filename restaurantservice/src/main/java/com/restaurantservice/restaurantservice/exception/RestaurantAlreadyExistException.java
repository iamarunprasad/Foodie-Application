package com.restaurantservice.restaurantservice.exception;

public class RestaurantAlreadyExistException extends Exception {
    public RestaurantAlreadyExistException(String message) {
        super(message);
    }
}
