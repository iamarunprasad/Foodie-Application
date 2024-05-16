package com.restaurantservice.restaurantservice.service;

import com.restaurantservice.restaurantservice.exception.*;
import com.restaurantservice.restaurantservice.model.Dishes;
import com.restaurantservice.restaurantservice.model.Restaurant;
import com.restaurantservice.restaurantservice.model.Merchant;

import java.util.List;

public interface IRestaurantService {
    public Merchant registerMerchant(Merchant merchant) throws MerchantAlreadyExistsException;
//   public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistException;
////public Restaurant addRestaurant(String merchantEmailId, Restaurant restaurant) throws RestaurantAlreadyExistException, UserAlreadyExistsException ;
//    Restaurant saveDishesToRestaurant(Dishes dishes,String restId) throws RestaurantNotFoundException, DishAlreadyExistException;
//    boolean deleteRestaurant(String restId) throws RestaurantNotFoundException;
//    Restaurant deleteDishFromRestaurant(String userEmailId, String dishID) throws DishNotFoundException, RestaurantNotFoundException;
//    Restaurant updateRestaurant(String restId,Restaurant restaurant) throws RestaurantNotFoundException;
//
//     public Dishes updateDish(Dishes dish, String restId, String dishId) throws RestaurantNotFoundException, DishNotFoundException;
//   List<Restaurant> getAllRestaurants()throws RestaurantNotFoundException;
//
//List<Restaurant> getAllResataurantByEmailId(String emailId);
//
//
//    public List<Restaurant> getRestaurantsByEmailId(String emailId) throws MerchantNotFoundException, RestaurantNotFoundException ;
//   Restaurant getRestaurantByName(String name)throws RestaurantNotFoundException;
//    public Restaurant getoneRestrauntById(String restId) throws RestaurantNotFoundException;
////    Dishes getDishByDishId(String restId,String dishId)throws DishNotFoundException;
////    public Dishes getDishByRestIdAndDishId(String restId, String dishId) throws DishNotFoundException;
//       Dishes getDishByRestIdAndDishId(String restId,String dishId)throws DishNotFoundException,RestaurantNotFoundException;



    public Merchant addRestaurant(Restaurant restaurant, String emailId) throws MerchantNotFoundException, RestaurantAlreadyExistException;

    //    @Override
//    public Merchant addRestaurant(Restaurant restaurant, String emailId) throws MerchantNotFoundException, RestaurantAlreadyExistException {
//        Optional<Merchant> merchantOptional = iMerchantRepository.findByEmailId(emailId);
//        if (merchantOptional.isPresent()) {
//            Merchant merchant = merchantOptional.get();
//            List<Restaurant> restaurantList = merchant.getRestaurantList();
//            if (restaurantList == null) {
//                merchant.setRestaurantList(Arrays.asList(restaurant));
//            } else {
//                boolean flag = false;
//                for (Restaurant existingRestaurant : restaurantList) {
//                    if (existingRestaurant.getName() != null && existingRestaurant.getName().equals(restaurant.getName())) {
//
//                        flag = true;
//                        break;
//                    }
//                }
//                if (flag) {
//                    throw new RestaurantAlreadyExistException("Restaurant already exists");
//                } else {
//                    restaurantList.add(restaurant);
//                    merchant.setRestaurantList(restaurantList);
//                }
//            }
//            iMerchantRepository.save(merchant);
//            return merchant;
//        }
//        throw new MerchantNotFoundException("Merchant not found");
//    }
//

    public List<Restaurant> getAllResaturantByEmailId(String emailId) throws RestaurantNotFoundException, MerchantNotFoundException;

    public List<Restaurant> getAllRestaurants() throws RestaurantNotFoundException;
    public Merchant deleteRestaurant(String emailId, String restId) throws RestaurantNotFoundException, MerchantNotFoundException, RestaurantAlreadyExistException;

    public Restaurant updateRestaurant(String emailId, Restaurant restaurant) throws MerchantNotFoundException, RestaurantNotFoundException;

    public Merchant addDish(String emailId, String restId, Dishes dish)
            throws MerchantNotFoundException, RestaurantNotFoundException, DishAlreadyExistException;
    public Restaurant getoneRestrauntById(String restId) throws RestaurantNotFoundException;
    public Dishes updateDish(String emailId, String restId, String dishId, Dishes updatedDish)
            throws MerchantNotFoundException, RestaurantNotFoundException, DishNotFoundException;

    public Merchant deleteDish(String emailId, String restId, String dishId) throws MerchantNotFoundException, RestaurantNotFoundException, DishNotFoundException;


    public Restaurant getRestaurantByRestId(String restId);

    public Dishes addDishh(String emailId, String restId, Dishes dish)
            throws MerchantNotFoundException, RestaurantNotFoundException, DishAlreadyExistException;
    public Dishes getDishById(String emailId, String restId, String dishId) throws MerchantNotFoundException, RestaurantNotFoundException, DishNotFoundException;

    public Dishes getDishWithoutId(String restId, String dishId) throws RestaurantNotFoundException, DishNotFoundException;


}
