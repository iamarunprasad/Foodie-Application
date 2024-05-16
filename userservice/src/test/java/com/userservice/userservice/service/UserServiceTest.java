package com.userservice.userservice.service;
import com.userservice.userservice.exception.FavoriteException;
import com.userservice.userservice.model.Dishes;
import com.userservice.userservice.model.Restaurant;
import com.userservice.userservice.model.User;
import com.userservice.userservice.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


//    @Test
//    void testAddUser_UserAlreadyExists() {
//        User user = new User("chai@gmail.com","chaithra","chai@123","bangalore","9048678999","User", new Restaurant("1","cafe","bangalore",new Dishes("1","chicken","bangalore",100.0f,4)));
//
//        user.setEmailID("test@example.com");
//
//        when(userRepository.findById(user.getUserEmailId())).thenReturn(Optional.of(user));
//
//        assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(user));
//    }

    @Test
    void testAddFavoriteRestaurant_Success() {
        User user = new User();
        user.setEmailID("test@example.com");
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Restaurant A");

        when(userRepository.findById(user.getEmailId())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        assertDoesNotThrow(() -> userService.addFavoriteRestaurant(user.getEmailId(), restaurant));
    }

    @Test
    void testAddFavoriteRestaurant_UserNotFound() {
        String userId = "nonexistent@example.com";
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Restaurant A");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(FavoriteException.class, () -> userService.addFavoriteRestaurant(userId, restaurant));
    }


    @Test
    void testDeleteRestaurantFromfav_UserNotFound() {
        String userId = "nonexistent@example.com";
        String restaurantName = "Restaurant A";

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(FavoriteException.class, () -> userService.deleteRestaurantFromfavv(userId, restaurantName));
    }
    @Test
    void testAddFavoriteDish_Success() {
        String userId = "test@example.com";
        Dishes dish = new Dishes();
        dish.setName("Dish A");

        User user = new User();
        user.setEmailID(userId);
        List<Dishes> dishesList = new ArrayList<>();
        user.setFavoriteDish(dishesList);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        assertDoesNotThrow(() -> userService.addFavoriteDish(userId, dish));
    }

    @Test
    void testAddFavoriteDish_UserNotFound() {
        String userId = "nonexistent@example.com";
        Dishes dish = new Dishes();
        dish.setName("Dish A");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(FavoriteException.class, () -> userService.addFavoriteDish(userId, dish));
    }

       @Test
    void testDeleteDishFromfav_UserNotFound() {
        String userId = "nonexistent@example.com";
        String dishName = "Dish A";

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(FavoriteException.class, () -> userService.deleteDishFromfav(userId, dishName));
    }

}

