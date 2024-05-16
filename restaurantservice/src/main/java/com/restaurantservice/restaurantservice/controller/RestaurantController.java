package com.restaurantservice.restaurantservice.controller;

import com.restaurantservice.restaurantservice.exception.*;
import com.restaurantservice.restaurantservice.model.Dishes;
import com.restaurantservice.restaurantservice.model.Merchant;
import com.restaurantservice.restaurantservice.model.Restaurant;
import com.restaurantservice.restaurantservice.service.IRestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v2")

public class RestaurantController {
    private final IRestaurantService iRestaurantService;

    @Autowired
    public RestaurantController(IRestaurantService iRestaurantService) {
        this.iRestaurantService = iRestaurantService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> merchantregister(@RequestBody Merchant merchant) {
        try {
            Merchant registeruser = iRestaurantService.registerMerchant(merchant);
            return new ResponseEntity<>(registeruser, HttpStatus.CREATED);
        } catch (MerchantAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/restaurant/add")
    public ResponseEntity<?> saveRestaurant(@RequestBody Restaurant restaurant, HttpServletRequest request) {
        // add a track to a specific user, return 201 status if track is saved else 500 status
        ResponseEntity responseEntity;
        try {
            String emailid = request.getAttribute("emailId").toString();
            Merchant merchant = iRestaurantService.addRestaurant(restaurant, emailid);
            responseEntity = new ResponseEntity(merchant, HttpStatus.CREATED);
        } catch (MerchantNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (RestaurantAlreadyExistException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @GetMapping("/restaurant/restaurants")
    public ResponseEntity<?> getAllTracks(HttpServletRequest request) {
        try {
            String emailid = request.getAttribute("emailId").toString();
            List<Restaurant> tracks = iRestaurantService.getAllResaturantByEmailId(emailid);
            return new ResponseEntity<>(tracks, HttpStatus.CREATED);

        } catch (RestaurantNotFoundException | MerchantNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        try {
            List<Restaurant> allRestaurants = iRestaurantService.getAllRestaurants();
            return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/restaurant/{restId}")
    public ResponseEntity<?> deleteTrack(HttpServletRequest request, @PathVariable String restId) {
        try {
            String emailid = request.getAttribute("emailId").toString();
            iRestaurantService.deleteRestaurant(emailid, restId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (MerchantNotFoundException | RestaurantNotFoundException | RestaurantAlreadyExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/restaurant")
    public ResponseEntity<?> updateTrack(HttpServletRequest request, @RequestBody Restaurant restaurant) {
        try {
            String emailid = request.getAttribute("emailId").toString();
            if (emailid == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            // Assuming iTrackService.updateUserTrackWishListWithGivenTrack returns the updated user
            Restaurant updatedMerchant = iRestaurantService.updateRestaurant(emailid, restaurant);
            return new ResponseEntity<>(updatedMerchant, HttpStatus.OK);
        } catch (MerchantNotFoundException | RestaurantNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }



    @PostMapping("/restaurant/{restId}")
    public ResponseEntity<?> addDish(HttpServletRequest request,
            @PathVariable String restId,
            @RequestBody Dishes dish) {
        try {
            String emailid = request.getAttribute("emailId").toString();
            Dishes updatedMerchant = iRestaurantService.addDishh(emailid, restId, dish);
            return new ResponseEntity<>(updatedMerchant, HttpStatus.OK);
        } catch (MerchantNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DishAlreadyExistException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/restaurant/{restId}/dish/{dishId}")
    public ResponseEntity<Dishes> updateDish(
            HttpServletRequest request,
            @PathVariable String restId,
            @PathVariable String dishId,
            @RequestBody Dishes updatedDish) {
        try {
            // Retrieve emailId from request, assuming it's stored as an attribute
            String emailId = request.getAttribute("emailId").toString();

            Dishes updatedDishResult = iRestaurantService.updateDish(emailId, restId, dishId, updatedDish);
            return new ResponseEntity<>(updatedDishResult, HttpStatus.OK);
        } catch (MerchantNotFoundException | RestaurantNotFoundException | DishNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/restaurant/delete/{restId}/{dishId}")
    public ResponseEntity<?> deleteDish(HttpServletRequest request, @PathVariable String restId, @PathVariable String dishId) {
        try {
            String emailid = request.getAttribute("emailId").toString();
            Merchant updatedMerchant = iRestaurantService.deleteDish(emailid, restId, dishId);
            return new ResponseEntity<>(updatedMerchant, HttpStatus.OK);
        } catch (MerchantNotFoundException | RestaurantNotFoundException | DishNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }



//    @GetMapping("restaurants/{restId}")
//    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable String restId) {
//        try {
//            Restaurant restaurant = iRestaurantService.getoneRestrauntById(restId);
//            return new ResponseEntity<>(restaurant, HttpStatus.OK);
//        } catch (RestaurantNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }


//    @GetMapping("/restaurants/{restId}/dishes")
//    public ResponseEntity<List<Dishes>> getAllDishesByRestaurantId(@PathVariable String restId) {
//        try {
//            List<Dishes> dishes = iRestaurantService.getAllDishesFromRestaurant(restId);
//            return new ResponseEntity<>(dishes, HttpStatus.OK);
//        } catch (RestaurantNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/restaurants/{restId}")
    public ResponseEntity<?> getAllRestaurantsByRestId(@PathVariable String restId) {
        Restaurant restaurants = iRestaurantService.getRestaurantByRestId(restId);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }


    @GetMapping("/restaurant/{restId}/{dishId}")
    public ResponseEntity<?> getDishById(HttpServletRequest request,
                                         @PathVariable String restId,
                                         @PathVariable String dishId) {
        try {
            String emailid = request.getAttribute("emailId").toString();
            Dishes dish = iRestaurantService.getDishById(emailid, restId, dishId);
            return ResponseEntity.ok(dish);
        } catch (MerchantNotFoundException | RestaurantNotFoundException | DishNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }


    @GetMapping("/{restId}/{dishId}")
    public ResponseEntity<Object> getDishById(
            @PathVariable String restId,
            @PathVariable String dishId
    ) {
        try {
            Dishes dish = iRestaurantService.getDishWithoutId(restId, dishId);
            return new ResponseEntity<>(dish, HttpStatus.OK);
        } catch (RestaurantNotFoundException | DishNotFoundException e) {
            // Handle exceptions and return appropriate HTTP status codes
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }







}


