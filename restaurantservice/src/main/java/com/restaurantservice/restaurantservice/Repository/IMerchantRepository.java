package com.restaurantservice.restaurantservice.Repository;

import com.restaurantservice.restaurantservice.model.Dishes;
import com.restaurantservice.restaurantservice.model.Merchant;
import com.restaurantservice.restaurantservice.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IMerchantRepository extends MongoRepository<Merchant, String> {
    Optional<Merchant> findByEmailId(String emailId);


//    @Query("{}")
//    List<Restaurant> findAllRestaurants();
//
//    @Query("{}")
//    List<Restaurant> findByRestId(String restId);

    @Query("{'restaurantList._id': ?0}")
    Optional<Restaurant> findRestaurantById(String restId);

//    @Query(value = "{}", fields = "{'restaurantList': 1}")
//    List<Merchant> findAllWithRestaurants();

//    List<Restaurant> findByEmailId(String emailId);


    @Query("{'restaurantId': ?0}")
    List<Dishes> findAllDishesByRestaurantId(String restaurantId);








//    Optional<Restaurant> findByName(String name);
//    Optional<Dishes> findByRestId(String restaurantId, String dishId);
//    //    Optional<Restaurant> findByEmailIdAndRestId(String emailId, Restaurant restId);
//    @Query("{'restId': ?0, 'dishes.dishID': ?1}")
//    Optional<Dishes> findDishByRestIdAndDishId(String restId, String dishId);

}
