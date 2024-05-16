package com.userservice.userservice.controller;

import com.userservice.userservice.exception.*;
import com.userservice.userservice.model.Dishes;
import com.userservice.userservice.model.Restaurant;
import com.userservice.userservice.model.User;
import com.userservice.userservice.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v3")
public class UserController {
    private final IUserService iUserService;
@Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> saveCustomer(@RequestBody User user){
        try {
            User registeruser = iUserService.addUser(user);
            return new ResponseEntity<>(registeruser, HttpStatus.CREATED);
        }catch(UserAlreadyExistsException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }





    @PostMapping("/user/add-restaurant")
    public ResponseEntity<User> addRestaurantToFavorites(
            HttpServletRequest request,
            @RequestBody Restaurant restaurant) {

        try {
            String userId=request.getAttribute("emailId").toString();
            User updatedUser = iUserService.addRestaurantToFavorites(userId, restaurant);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (RestaurantAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/user/favget")
    public ResponseEntity<List<Restaurant>> getFavoriteRestaurants(HttpServletRequest request) {
        try {
            String userId = request.getAttribute("emailId").toString();
            List<Restaurant> favoriteRestaurants = iUserService.getFavoriteRestaurants(userId);
            return ResponseEntity.ok(favoriteRestaurants);
        } catch (FavoriteException e) {
            // Handle exception as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("/user/deletefavRestaurant/{restId}")
    public ResponseEntity<String> deleteFavoriteRestaurant(
            HttpServletRequest request,
            @PathVariable String restId) {
        String userId=request.getAttribute("emailId").toString();
        try {
            iUserService.deleteRestaurantFromfavv(userId, restId);
            return new ResponseEntity<>("Rest deleted successfully", HttpStatus.OK);
        } catch (FavoriteException e) {
            return new ResponseEntity<>("restaurantNotfound", HttpStatus.NOT_FOUND);
        }
    }
//    @PostMapping("/user/dishes/add")
//    public ResponseEntity<User> addDishToFavorites( HttpServletRequest request, @RequestBody Dishes dish) {
//        try {
//            String userId=request.getAttribute("emailId").toString();
//            User updatedUser = iUserService.addDishToFavourites(userId, dish);
//            return ResponseEntity.ok(updatedUser);
//        } catch (UserNotFoundException | DishAlreadyExistsException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }
@PostMapping("/user/add-dish")
public ResponseEntity<User> addDishToFavorites(
        HttpServletRequest request,
        @RequestBody Dishes dish) {

    try {
        String userId = request.getAttribute("emailId").toString();
        User updatedUser = iUserService.addDishToFavorites(userId, dish);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);

    } catch (UserNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    } catch (DishAlreadyExistsException e) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
    @GetMapping("/user/favget-dishes")
    public ResponseEntity<List<Dishes>> getFavoriteDishes(HttpServletRequest request) {
        try {
            String userId = request.getAttribute("emailId").toString();
            List<Dishes> favoriteDishes = iUserService.getFavoriteDishes(userId);
            return ResponseEntity.ok(favoriteDishes);
        } catch (FavoriteException e) {
            // Handle exception as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/user/deletefavDish/{dishId}")
    public ResponseEntity<String> deleteFavoriteDish(
            HttpServletRequest request,
            @PathVariable String dishId) {
        String userId = request.getAttribute("emailId").toString();
        try {
            iUserService.deleteDishFromFavorites(userId, dishId);
            return ResponseEntity.ok("Dish deleted successfully");
        } catch (FavoriteException e) {
            return ResponseEntity.notFound().build();
        }
    }




}

