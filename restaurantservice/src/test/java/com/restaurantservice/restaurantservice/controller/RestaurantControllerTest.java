package com.restaurantservice.restaurantservice.controller;
import com.restaurantservice.restaurantservice.exception.MerchantAlreadyExistsException;
import com.restaurantservice.restaurantservice.model.Dishes;
import com.restaurantservice.restaurantservice.model.Restaurant;
import com.restaurantservice.restaurantservice.service.RestaurantServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class RestaurantControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RestaurantServiceImpl restaurantService;
    private Restaurant restaurant;
    private Dishes testDish;

    @InjectMocks
    RestaurantController restaurantController;

//    @BeforeEach
//    void setUp() {
//        restaurant = new Restaurant("1", "Motel Way", "Chennai", new ArrayList<>());
//        testDish = new Dishes("1", "Coffee", "Category", 15.99f, 5);
//        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
//    }

    @Test
    public void testSaveCustomer_Success() throws MerchantAlreadyExistsException {
////        Merchant merchant = new Merchant(
////                "abc@gmail.com",
////                "abhi",
////                "Mysore",
////                "Mysore",
////                "9738416898",
////                new Restaurant(
////                        "cafe@gmail.com",
////                        "cafe",
////                        "bangalore",
////                        new Dishes(
////                                new Dishes("dish1", "chicken", "non veg", 400.0f, 7)
////
////                        )
////                )
////        );
////        when(restaurantService.registerMerchant(any(Merchant.class))).thenReturn(merchant);
////        ResponseEntity<?> responseEntity = restaurantController.merchantregister(merchant);
////        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
////        assertEquals(merchant, responseEntity.getBody());
////    }
//
//    @Test
//    public void testSaveCustomer_UserAlreadyExistsException() throws UserAlreadyExistsException {
//        Merchant merchant = new Merchant();
//        String errorMessage = "User already exists";
//        when(restaurantService.registerMerchant(any(Merchant.class))).thenThrow(new UserAlreadyExistsException(errorMessage));
//        ResponseEntity<?> responseEntity = restaurantController.merchantregister(merchant);
//        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
//        assertEquals(errorMessage, responseEntity.getBody());
//    }
//    @Test
//    public void testSaveRestaurant_Success() throws RestaurantAlreadyExistException {
//        Restaurant restaurant = new Restaurant(/* provide necessary details */);
//        when(restaurantService.addRestaurant(any(Restaurant.class))).thenReturn(restaurant);
//        ResponseEntity<?> responseEntity = restaurantController.saveRestaurant(restaurant);
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        assertEquals(restaurant, responseEntity.getBody());
//    }
//
//    @Test
//    public void testSaveRestaurant_RestaurantAlreadyExistException() throws RestaurantAlreadyExistException {
//        Restaurant restaurant = new Restaurant();
//        String errorMessage = "Restaurant already exists";
//        when(restaurantService.addRestaurant(any(Restaurant.class))).thenThrow(new RestaurantAlreadyExistException(errorMessage));
//        ResponseEntity<?> responseEntity = restaurantController.saveRestaurant(restaurant);
//        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
//        assertEquals(errorMessage, responseEntity.getBody());
//    }
//
//    @Test
//    public void testSaveDishes_DishAlreadyExistException() throws DishAlreadyExistException, RestaurantNotFoundException {
//        Dishes dishes = new Dishes();
//        String restId = "123";
//        String errorMessage = "Dish already exists";
//        when(restaurantService.saveDishesToRestaurant(any(Dishes.class), any(String.class))).thenThrow(new DishAlreadyExistException(errorMessage));
//        ResponseEntity<?> responseEntity = restaurantController.saveDishes(dishes, restId);
//        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
//        assertEquals(errorMessage, responseEntity.getBody());
//    }
//    @Test
//    public void testSaveDishes_RestaurantNotFoundException() throws DishAlreadyExistException, RestaurantNotFoundException {
//        Dishes dishes = new Dishes();
//        String restId = "invalid_id";
//        String errorMessage = "Restaurant not found";
//        when(restaurantService.saveDishesToRestaurant(any(Dishes.class), any(String.class))).thenThrow(new RestaurantNotFoundException(errorMessage));
//        ResponseEntity<?> responseEntity = restaurantController.saveDishes(dishes, restId);
//        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
//        assertEquals(errorMessage, responseEntity.getBody());
//    }

    }
}























