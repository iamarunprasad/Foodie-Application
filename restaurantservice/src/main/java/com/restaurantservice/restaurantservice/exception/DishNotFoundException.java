package com.restaurantservice.restaurantservice.exception;

public class DishNotFoundException extends Exception {
    public DishNotFoundException(String message) {
        super(message);
    }
}
