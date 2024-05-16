package com.userservice.userservice.repository;

import com.userservice.userservice.model.Restaurant;
import com.userservice.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends MongoRepository<User,String> {
    @Query("{}")
    List<Restaurant> findAllRestaurants();
    @Query("{'emailId': ?0}")
    List<Restaurant> findFavoriteRestaurantsByEmailId(String emailId);

//    Optional<User> findByUserId(String userId);


    @Query("{'emailId': ?0}")
    List<Restaurant> findAddedRestaurantByEmailId(String emailId);






}
