package com.restaurantservice.restaurantservice.service;


import com.restaurantservice.restaurantservice.Repository.IMerchantRepository;
import com.restaurantservice.restaurantservice.exception.*;
import com.restaurantservice.restaurantservice.model.Dishes;
import com.restaurantservice.restaurantservice.model.Merchant;
import com.restaurantservice.restaurantservice.model.Restaurant;
import com.restaurantservice.restaurantservice.proxy.UserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
    public class RestaurantServiceImpl implements IRestaurantService {


    private final IMerchantRepository iMerchantRepository;

    private final UserProxy userProxy;

    @Autowired
    public RestaurantServiceImpl(IMerchantRepository iMerchantRepository, UserProxy userProxy) {
        this.iMerchantRepository = iMerchantRepository;
        this.userProxy = userProxy;
    }


    @Override
    public Merchant registerMerchant(Merchant merchant) throws MerchantAlreadyExistsException {
        {
            if (iMerchantRepository.findById(merchant.getEmailId()).isPresent()) {
                throw new MerchantAlreadyExistsException("User already exist");
            } else {

                Merchant inserted = iMerchantRepository.save(merchant);
                userProxy.saveUser(inserted);
                return inserted;
            }
        }
    }

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
@Override
public Merchant addRestaurant(Restaurant restaurant, String emailId) throws MerchantNotFoundException, RestaurantAlreadyExistException {
    Optional<Merchant> merchantOptional = iMerchantRepository.findByEmailId(emailId);
    if (merchantOptional.isPresent()) {
        Merchant merchant = merchantOptional.get();
        List<Restaurant> restaurantList = merchant.getRestaurantList();

        if (restaurantList == null) {
            merchant.setRestaurantList(new ArrayList<>(List.of(restaurant)));
        } else {
            boolean restaurantExists = restaurantList.stream()
                    .anyMatch(existingRestaurant -> existingRestaurant.getRestId().equals(restaurant.getRestId()));

            if (restaurantExists) {
                throw new RestaurantAlreadyExistException("Restaurant already exists");
            } else {
                // Check if the restaurant with the same restId exists before adding
                boolean alreadyExists = restaurantList.stream()
                        .anyMatch(existingRestaurant -> existingRestaurant.getRestId().equals(restaurant.getRestId()));

                if (alreadyExists) {
                    throw new RestaurantAlreadyExistException("Restaurant already exists");
                } else {
                    restaurantList.add(restaurant);
                    merchant.setRestaurantList(restaurantList);
                }
            }
        }
        iMerchantRepository.save(merchant);
        return merchant;
    } else {
        throw new MerchantNotFoundException("Merchant not found");
    }
}

    @Override
    public List<Restaurant> getAllResaturantByEmailId(String emailId) throws RestaurantNotFoundException, MerchantNotFoundException {
        Optional<Merchant> merchantOptional = iMerchantRepository.findByEmailId(emailId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            List<Restaurant> restaurantList = merchant.getRestaurantList();
            if (restaurantList == null) {
                throw new RestaurantNotFoundException("restaurant not found");
            } else {
                merchantOptional = iMerchantRepository.findByEmailId(emailId);
                if (merchantOptional.isPresent()) {
                    return restaurantList;
                }
            }
        }
        throw new MerchantNotFoundException("merchant not found");
    }

    @Override
    public List<Restaurant> getAllRestaurants() throws RestaurantNotFoundException {
        List<Restaurant> allRestaurants = new ArrayList<>();
        List<Merchant> allMerchants = iMerchantRepository.findAll();

        for (Merchant merchant : allMerchants) {
            List<Restaurant> merchantRestaurants = merchant.getRestaurantList();
            if (merchantRestaurants != null) {
                allRestaurants.addAll(merchantRestaurants);
            }
        }

        if (allRestaurants.isEmpty()) {
            throw new RestaurantNotFoundException("No restaurants found");
        } else {
            return allRestaurants;
        }
    }

    @Override
    public Merchant deleteRestaurant(String emailId, String restId) throws RestaurantNotFoundException, MerchantNotFoundException, RestaurantAlreadyExistException {
        // delete the user details specified
        Optional<Merchant> merchantOptional = iMerchantRepository.findByEmailId(emailId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            List<Restaurant> restaurantList = merchant.getRestaurantList();

            boolean flag = false;
            for (Restaurant existingrestaurant : restaurantList) {
                if (existingrestaurant.getRestId().equals(restId)) {
                    restaurantList.remove(existingrestaurant);

                    flag = true;
                    break;
                }
            }
            if (flag) {
                merchant.setRestaurantList(restaurantList);
                return iMerchantRepository.save(merchant);
            } else {
                throw new RestaurantNotFoundException("restaurant not found");
            }
        } else {
            throw new MerchantNotFoundException("merchant not found");
        }

    }


    @Override
    public Restaurant updateRestaurant(String emailId, Restaurant restaurant) throws MerchantNotFoundException, RestaurantNotFoundException {
        Optional<Merchant> merchantOptional = iMerchantRepository.findByEmailId(emailId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            List<Restaurant> restaurantList = merchant.getRestaurantList();
            boolean restaurantFound = false;
            for (Restaurant existingRestaurant : restaurantList) {
                if (existingRestaurant.getRestId().equals(restaurant.getRestId())) {
                    restaurantFound = true;
                    existingRestaurant.setName(restaurant.getName());
                    existingRestaurant.setLocation(restaurant.getLocation());
//                    existingRestaurant.setDishes(restaurant.getDishes());
                    break;
                }
            }

            if (restaurantFound) {
                merchant.setRestaurantList(restaurantList);
                iMerchantRepository.save(merchant);
                return restaurant;
            } else {
                throw new RestaurantNotFoundException("Restaurant not found in the merchants list");
            }
        } else {
            throw new MerchantNotFoundException("Merchant not found");
        }
    }


//    @Override
//    public Merchant addDish(String emailId, String restId, Dishes dish)
//            throws MerchantNotFoundException, RestaurantNotFoundException, DishAlreadyExistException {
//        Optional<Merchant> merchantOptional = iMerchantRepository.findByEmailId(emailId);
//        if (merchantOptional.isPresent()) {
//            Merchant merchant = merchantOptional.get();
//            List<Restaurant> restaurantList = merchant.getRestaurantList();
//            boolean restaurantFound = false;
//
//            for (Restaurant existingRestaurant : restaurantList) {
//                if (existingRestaurant.getRestId().equals(restId)) {
//                    restaurantFound = true;
//                    List<Dishes> dishesList = existingRestaurant.getDishList();
//                    if (dishesList == null) {
//                        existingRestaurant.setDishList(new ArrayList<>(List.of(dish)));
//                    } else {
//                        boolean dishFound = dishesList.stream().anyMatch(d -> d.getDishID().equals(dish.getDishID()));
//                        if (dishFound) {
//                            throw new DishAlreadyExistException("Dish already exists in the restaurant");
//                        } else {
//                            dishesList.add(dish);
//                            existingRestaurant.setDishList(dishesList);
//                        }
//                    }
//                    break;
//                }
//            }
//
//            if (restaurantFound) {
//                merchant.setRestaurantList(restaurantList);
//                return iMerchantRepository.save(merchant);
//            } else {
//                throw new RestaurantNotFoundException("Restaurant not found in the merchants list");
//            }
//        } else {
//            throw new MerchantNotFoundException("Merchant not found");
//        }
//
//
//    }
//@Override
//public Merchant addDish(String emailId, String restId, Dishes dish)
//        throws MerchantNotFoundException, RestaurantNotFoundException, DishAlreadyExistException {
//    Optional<Merchant> merchantOptional = iMerchantRepository.findByEmailId(emailId);
//    if (merchantOptional.isPresent()) {
//        Merchant merchant = merchantOptional.get();
//        List<Restaurant> restaurantList = merchant.getRestaurantList();
//        boolean restaurantFound = false;
//
//        for (Restaurant existingRestaurant : restaurantList) {
//            if (existingRestaurant.getRestId().equals(restId)) {
//                restaurantFound = true;
//                List<Dishes> dishesList = existingRestaurant.getDishList();
//                if (dishesList == null) {
//                    existingRestaurant.setDishList(new ArrayList<>(List.of(dish)));
//                } else {
//                    boolean dishFound = dishesList.stream().anyMatch(d -> d.getDishID().equals(dish.getDishID()));
//                    if (dishFound) {
//                        throw new DishAlreadyExistException("Dish already exists in the restaurant");
//                    } else {
//                        dishesList.add(dish);
//                        existingRestaurant.setDishList(dishesList);
//                    }
//                }
//                break;
//            }
//        }
//
//        if (restaurantFound) {
//            merchant.setRestaurantList(restaurantList);
//            return iMerchantRepository.save(merchant);
//        } else {
//            throw new RestaurantNotFoundException("Restaurant not found in the merchant's list");
//        }
//    } else {
//        throw new MerchantNotFoundException("Merchant not found");
//    }
//}
@Override
public Merchant addDish(String emailId, String restId, Dishes dish)
        throws MerchantNotFoundException, RestaurantNotFoundException, DishAlreadyExistException {
    Optional<Merchant> merchantOptional = iMerchantRepository.findByEmailId(emailId);
    if (merchantOptional.isPresent()) {
        Merchant merchant = merchantOptional.get();
        List<Restaurant> restaurantList = merchant.getRestaurantList();
        boolean restaurantFound = false;

        for (Restaurant existingRestaurant : restaurantList) {
            if (existingRestaurant.getRestId().equals(restId)) {
                restaurantFound = true;
                List<Dishes> dishesList = existingRestaurant.getDishList();
                if (dishesList == null) {
                    dishesList = new ArrayList<>();
                }

                // Check if the dish already exists in the restaurant's dish list
                boolean dishExists = dishesList.stream().anyMatch(d -> d.getDishID().equals(dish.getDishID()));

                if (dishExists) {
                    throw new DishAlreadyExistException("Dish already exists in the restaurant");
                } else {
                    dishesList.add(dish);
                    existingRestaurant.setDishList(dishesList);
                }
                break;
            }
        }

        if (restaurantFound) {
            merchant.setRestaurantList(restaurantList);
            return iMerchantRepository.save(merchant);
        } else {
            throw new RestaurantNotFoundException("Restaurant not found in the merchant's list");
        }
    } else {
        throw new MerchantNotFoundException("Merchant not found");
    }
}

    @Override
    public Restaurant getoneRestrauntById(String restId) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantOptional = iMerchantRepository.findRestaurantById(restId);
        if (restaurantOptional.isPresent()) {
            return restaurantOptional.get();
        } else {
            throw new RestaurantNotFoundException("Restaurant not found with the name: " + restId);
        }
    }



    @Override
    public Dishes updateDish(String emailId, String restId, String dishId, Dishes updatedDish)
            throws MerchantNotFoundException, RestaurantNotFoundException, DishNotFoundException {
        Optional<Merchant> merchantOptional = iMerchantRepository.findByEmailId(emailId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            Optional<Restaurant> restaurantOptional = merchant.getRestaurantList().stream()
                    .filter(restaurant -> restaurant.getRestId().equals(restId))
                    .findFirst();
            if (restaurantOptional.isPresent()) {
                Restaurant restaurant = restaurantOptional.get();
                Optional<Dishes> dishOptional = restaurant.getDishList().stream()
                        .filter(dish -> dish.getDishID().equals(dishId))
                        .findFirst();
                if (dishOptional.isPresent()) {
                    Dishes dishToUpdate = dishOptional.get();
                    // Update dish details
                    dishToUpdate.setDishname(updatedDish.getDishname());
                    dishToUpdate.setCategory(updatedDish.getCategory());
                    dishToUpdate.setRating(updatedDish.getRating());
                    dishToUpdate.setPrice(updatedDish.getPrice());
                    // Save changes
                    iMerchantRepository.save(merchant);
                    return dishToUpdate;
                } else {
                    // Dish with provided ID not found
                    throw new DishNotFoundException("Dish with ID " + dishId + " not found in restaurant " + restId);
                }
            } else {
                // Restaurant with provided ID not found
                throw new RestaurantNotFoundException("Restaurant with ID " + restId + " not found for merchant " + emailId);
            }
        } else {
            // Merchant not found
            throw new MerchantNotFoundException("Merchant with email " + emailId + " not found");
        }
    }


    @Override
    public Merchant deleteDish(String emailId, String restId, String dishId) throws MerchantNotFoundException, RestaurantNotFoundException, DishNotFoundException {
        Optional<Merchant> merchantOptional = iMerchantRepository.findByEmailId(emailId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            List<Restaurant> restaurantList = merchant.getRestaurantList();

            for (Restaurant restaurant : restaurantList) {
                if (restaurant.getRestId().equals(restId)) {
                    List<Dishes> dishesList = restaurant.getDishList();

                    if (dishesList != null) {
                        boolean dishFound = false;
                        for (Iterator<Dishes> iterator = dishesList.iterator(); iterator.hasNext(); ) {
                            Dishes dish = iterator.next();
                            if (dish.getDishID().equals(dishId)) {
                                iterator.remove();
                                dishFound = true;
                                break;
                            }
                        }
                        if (dishFound) {
                            restaurant.setDishList(dishesList);
                            iMerchantRepository.save(merchant);
                            return merchant;
                        } else {
                            throw new DishNotFoundException("Dish not found with ID: " + dishId);
                        }
                    } else {
                        throw new DishNotFoundException("No dishes found in the restaurant with ID: " + restId);
                    }
                }
            }
            throw new RestaurantNotFoundException("Restaurant not found with ID: " + restId);
        } else {
            throw new MerchantNotFoundException("Merchant not found for the given email ID: " + emailId);
        }
    }
    @Override
    public Restaurant getRestaurantByRestId(String restId) {
        List<Merchant> allMerchants = iMerchantRepository.findAll();

        for (Merchant merchant : allMerchants) {
            List<Restaurant> merchantRestaurants = merchant.getRestaurantList();
            if (merchantRestaurants != null) {
                for (Restaurant restaurant : merchantRestaurants) {
                    if (restaurant.getRestId().equals(restId)) {
                        String name = restaurant.getName();
                        String location = restaurant.getLocation();
                        List<Dishes> dishList = restaurant.getDishList();
                        return new Restaurant(restId, name, location, dishList);
                    }
                }
            }
        }

        // Return null if no matching restaurant is found
        return null;
    }


    @Override
    public Dishes addDishh(String emailId, String restId, Dishes dish)
            throws MerchantNotFoundException, RestaurantNotFoundException, DishAlreadyExistException {
        Optional<Merchant> merchantOptional = iMerchantRepository.findByEmailId(emailId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            List<Restaurant> restaurantList = merchant.getRestaurantList();
            boolean restaurantFound = false;

            for (Restaurant existingRestaurant : restaurantList) {
                if (existingRestaurant.getRestId().equals(restId)) {
                    restaurantFound = true;
                    List<Dishes> dishesList = existingRestaurant.getDishList();
                    if (dishesList == null) {
                        existingRestaurant.setDishList(new ArrayList<>(List.of(dish)));
                    } else {
                        boolean dishFound = dishesList.stream().anyMatch(d -> d.getDishID().equals(dish.getDishID()));
                        if (dishFound) {
                            throw new DishAlreadyExistException("Dish already exists in the restaurant");
                        } else {
                            dishesList.add(dish);
                            existingRestaurant.setDishList(dishesList);
                        }
                    }
                    break;
                }
            }

            if (restaurantFound) {
                iMerchantRepository.save(merchant);
                return dish; // Return the added dish
            } else {
                throw new RestaurantNotFoundException("Restaurant not found in the merchants list");
            }
        } else {
            throw new MerchantNotFoundException("Merchant not found");
        }
    }
    @Override
    public Dishes getDishById(String emailId, String restId, String dishId) throws MerchantNotFoundException, RestaurantNotFoundException, DishNotFoundException {
        Optional<Merchant> merchantOptional = iMerchantRepository.findByEmailId(emailId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            List<Restaurant> restaurantList = merchant.getRestaurantList();
            for (Restaurant restaurant : restaurantList) {
                if (restaurant.getRestId().equals(restId)) {
                    List<Dishes> dishesList = restaurant.getDishList();
                    if (dishesList != null) {
                        Optional<Dishes> dishOptional = dishesList.stream()
                                .filter(dish -> dish.getDishID().equals(dishId))
                                .findFirst();
                        if (dishOptional.isPresent()) {
                            return dishOptional.get();
                        } else {
                            throw new DishNotFoundException("Dish not found with ID: " + dishId);
                        }
                    } else {
                        throw new DishNotFoundException("No dishes found in the restaurant with ID: " + restId);
                    }
                }
            }
            throw new RestaurantNotFoundException("Restaurant not found with ID: " + restId);
        } else {
            throw new MerchantNotFoundException("Merchant not found for the given email ID: " + emailId);
        }
    }



    public Dishes getDishWithoutId(String restId, String dishId) throws RestaurantNotFoundException, DishNotFoundException {
        List<Merchant> allMerchants = iMerchantRepository.findAll();

        for (Merchant merchant : allMerchants) {
            List<Restaurant> restaurantList = merchant.getRestaurantList();
            for (Restaurant restaurant : restaurantList) {
                if (restaurant.getRestId().equals(restId)) {
                    List<Dishes> dishesList = restaurant.getDishList();
                    if (dishesList != null) {
                        Optional<Dishes> dishOptional = dishesList.stream()
                                .filter(dish -> dish.getDishID().equals(dishId))
                                .findFirst();
                        if (dishOptional.isPresent()) {
                            return dishOptional.get();
                        } else {
                            throw new DishNotFoundException("Dish not found with ID: " + dishId);
                        }
                    } else {
                        throw new DishNotFoundException("No dishes found in the restaurant with ID: " + restId);
                    }
                }
            }
        }

        throw new RestaurantNotFoundException("Restaurant not found with ID: " + restId);
    }
















}












































