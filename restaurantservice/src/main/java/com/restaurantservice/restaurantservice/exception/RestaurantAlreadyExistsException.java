package com.restaurantservice.restaurantservice.exception;

public class RestaurantAlreadyExistsException extends Throwable {
    public RestaurantAlreadyExistsException(String message) {
        super(message);
    }
}
