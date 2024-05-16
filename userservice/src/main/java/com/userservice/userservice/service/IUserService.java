package com.userservice.userservice.service;

import com.userservice.userservice.exception.*;
import com.userservice.userservice.model.Dishes;
import com.userservice.userservice.model.Restaurant;
import com.userservice.userservice.model.User;

import java.util.List;

public interface IUserService {

    public User addUser(User user) throws UserAlreadyExistsException, FavoriteException;




    public List<Restaurant> getFavoriteRestaurants(String userId) throws FavoriteException;

    public User addRestaurantToFavorites(String userId, Restaurant restaurant) throws UserNotFoundException, RestaurantAlreadyExistsException;


    public boolean deleteRestaurantFromfavv(String userId, String restaurantId) throws FavoriteException;



    public User addDishToFavorites(String userId, Dishes dish) throws UserNotFoundException, DishAlreadyExistsException;




    public List<Dishes> getFavoriteDishes(String userId) throws FavoriteException;
    public boolean deleteDishFromFavorites(String userId, String dishId) throws FavoriteException;
}
